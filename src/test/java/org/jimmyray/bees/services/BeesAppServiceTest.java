package org.jimmyray.bees.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.jimmyray.bees.data.model.BeesCreds;
import org.jimmyray.bees.spring.config.SpringConfig;
import org.jimmyray.bees.util.Constants;
import org.jimmyray.bees.util.Properties;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.cloudbees.api.ApplicationInfo;
import com.cloudbees.api.ApplicationListResponse;

public class BeesAppServiceTest {
	private static Logger LOGGER = LoggerFactory
			.getLogger(BeesAppServiceTest.class);

	private BeesAppService beesAppService;
	private BeesCreds creds;

	@Before
	public void setup() {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(
				SpringConfig.class);
		this.beesAppService = (BeesAppService) ctx
				.getBean(Constants.BEES_APP_SERVICE_BEAN);

		this.creds = new BeesCreds(Properties.getString(Constants.CREDS_BUNDLE,
				Constants.BEES_ACCOUNT), Constants.BEES_API_URL,
				Properties.getString(Constants.CREDS_BUNDLE,
						Constants.BEES_API_KEY), Properties.getString(
						Constants.CREDS_BUNDLE, Constants.BEES_SECRET));
	}

	@Test
	public void testGetAppInfo() throws Exception {
		assertNotNull("BeesAppService was null.", this.beesAppService);
		assertNotNull("BeesCreds was null.", this.creds);
		assertTrue("BeesCreds was incomplete.", this.creds.isComplete());

		LOGGER.debug(creds.toString());

		ApplicationListResponse response = this.beesAppService
				.getAppList(this.creds);
		List<ApplicationInfo> apps = response.getApplications();

		for (ApplicationInfo app : apps) {
			Map<String, Object> properties = BeanUtils.describe(app);
			System.out.println(properties);
		}
	}
}
