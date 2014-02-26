package org.jimmyray.bees.util.spring;

import java.lang.annotation.Annotation;

import org.jimmyray.bees.spring.config.BeanName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.Lifecycle;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Utility bean to retrieve other spring beans from non spring aware classes. Note that once one of the instances of
 * this been is initialized, all the other ones are too as the spring context is stored at the class level. Use with
 * care because of that feature, if reinitialized with a different context unexpected behavior may happen...
 * 
 * @see BeanName
 */
// JSHRADER - Sonar is throwing a checkstyle AbstractClassName error that this class
// must be declared abstract. This class has been used in numerous projects
// over several years and reviewed by various tech leads; we cannot determine
// the reason for this apparent erroneous failure or the need or ability
// to make this class abstract.
// CHECKSTYLE:OFF
@Component
public final class SpringBeanFactory implements ApplicationContextAware, Lifecycle {
	// CHECKSTYLE:ON

	/** The log. */
	private static final Logger LOGGER = LoggerFactory.getLogger(SpringBeanFactory.class);

	/** The Constant STATIC_CONTEXT_WAS_NULL. */
	private static final String STATIC_CONTEXT_WAS_NULL = "SpringBeanFactory.staticContext was null.";

	/** The static context. */
	private static ApplicationContext staticContext;

	/** The Constant ANNOTATION. */
	private static final Class<? extends Annotation> ANNOTATION = BeanName.class;

	/**
	 * Set the context through one instance, invoked automatically when the instance is created by instantiation by a
	 * Spring application context.
	 * 
	 * @param context the new context to be used to retrieve beans for all instances of this class.
	 */
	//@Override
	public void setApplicationContext(final ApplicationContext context) {
		setStaticContext(context);
	}

	/**
	 * Sets the static context.
	 * 
	 * @param context the new static context
	 */
	private static void setStaticContext(final ApplicationContext context) {
		synchronized (SpringBeanFactory.class) {
			staticContext = context;
		}
	}

	/**
	 * Retrieve a bean by its name.
	 * 
	 * @param beanName the bean name
	 * @return the object registered in the spring application context with the specified name.
	 */
	public static Object getBean(final String beanName) {
		Object obj = null;
		if (staticContext != null && beanName != null) {
			obj = staticContext.getBean(beanName);
		}
		return obj;
	}

	/**
	 * Get bean by annotated type.
	 * 
	 * @param typ the typ
	 * @return the bean
	 */
	public static Object getBean(final Class<?> typ) {
		if (staticContext != null) {
			try {
				return getBean(processAnnotation(typ));
			} catch (final IllegalArgumentException iae) {
				LOGGER.error(iae.getLocalizedMessage());
			} catch (final BeansException be) {
				LOGGER.error(be.getLocalizedMessage());
			} catch (final ClassNotFoundException cnfe) {
				LOGGER.error(cnfe.getLocalizedMessage());
			}
		} else {
			LOGGER.debug(STATIC_CONTEXT_WAS_NULL);
		}

		return null;
	}

	/**
	 * Removes the bean.
	 * 
	 * @param beanName the bean name
	 */
	public static void removeBean(final String beanName) {
		if (staticContext != null) {
			final BeanDefinitionRegistry beanFactory =
					(BeanDefinitionRegistry) SpringBeanFactory.getContext().getAutowireCapableBeanFactory();
			try {
				beanFactory.removeBeanDefinition(beanName);
			} catch (final NoSuchBeanDefinitionException e) {
				LOGGER.warn("Bean for beanName not found:" + beanName);
			}
		} else {
			LOGGER.warn(STATIC_CONTEXT_WAS_NULL);
		}
	}

	/**
	 * Initializes the static context explicitly by looking up all the xml files in the /context package on the
	 * classpath. In case of use outside the web container (EJB) we need to handle manually pulling the context.
	 */
	public static void initContext() {
		synchronized (SpringBeanFactory.class) {
			if (staticContext == null) {
				SpringBeanFactory.initContext("classpath*:/context/main.xml");
			}
		}
	}

	/**
	 * Gets the context.
	 * 
	 * @return the context
	 */
	public static ApplicationContext getContext() {
		return SpringBeanFactory.staticContext;
	}

	/**
	 * Initializes the static context explicitly by looking up all the xml files in the /context package on the
	 * classpath. In case of use outside the web container (EJB) we need to handle manually pulling the context.
	 * 
	 * Overloaded method, no null context check
	 * 
	 * @param contextPath the context path
	 */
	public static void initContext(final String contextPath) {
		synchronized (SpringBeanFactory.class) {
			if (staticContext == null) {
				setStaticContext(new ClassPathXmlApplicationContext(contextPath));
			}
		}
	}

	/**
	 * Destroy bean.
	 * 
	 * @param beanName the bean name
	 * @param object the bean instance
	 */
	public static void destroyBean(final String beanName, final Object object) {
		if (staticContext.containsBean(beanName)) {
			((ConfigurableApplicationContext) staticContext).getBeanFactory().destroyBean(beanName, object);
		}
	}

	/**
	 * Get bean name from annotated class
	 * 
	 * @param clazz the clazz
	 * @return the string
	 * @throws ClassNotFoundException the class not found exception
	 */
	private static String processAnnotation(final Class<?> clazz) throws ClassNotFoundException {

		BeanName beanName = null;
		String retrn = null;

		if (clazz.isAnnotationPresent(ANNOTATION)) {
			beanName = (BeanName) clazz.getAnnotation(ANNOTATION);
			if (null == beanName) {
				throw new IllegalArgumentException(ANNOTATION.getName() + " annotation was not found on class "
						+ clazz.getName() + ".");
			}
			retrn = beanName.id();
		}

		return retrn;
	}

	//@Override
	public void start() {
	}

	//@Override
	public void stop() {
		synchronized (SpringBeanFactory.class) {
			staticContext = null;
		}
	}

	//@Override
	public boolean isRunning() {
		return staticContext != null;
	}

}
