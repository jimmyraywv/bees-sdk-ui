package org.jimmyray.bees.spring.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation that indicates per-type bean implementation, used in factory
 * pattern usage of SpringBeanFactory
 * 
 * @author jimmyray
 * @version 1.0
 * 
 * @see gov.va.wss.framework.config.SpringBeanFactory
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface BeanName {

	/**
	 * Returns the name.
	 */
	String name() default "";

	/**
	 * Returns the id.
	 */
	String id() default "";

}
