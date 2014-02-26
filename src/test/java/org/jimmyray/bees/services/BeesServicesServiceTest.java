package org.jimmyray.bees.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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

import com.cloudbees.api.ServiceInfo;
import com.cloudbees.api.ServiceListResponse;
import com.cloudbees.api.ServiceSubscriptionInfo;
import com.cloudbees.api.ServiceSubscriptionListResponse;

public class BeesServicesServiceTest {
	private static Logger LOGGER = LoggerFactory
			.getLogger(BeesServicesServiceTest.class);

	private BeesServicesService beesServicesService;
	private BeesCreds creds;

	@Before
	public void setup() {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(
				SpringConfig.class);
		this.beesServicesService = (BeesServicesService) ctx
				.getBean(Constants.BEES_SERVICES_SERVICE_BEAN);

		this.creds = new BeesCreds(Properties.getString(Constants.CREDS_BUNDLE,
				Constants.BEES_ACCOUNT), Constants.BEES_API_URL,
				Properties.getString(Constants.CREDS_BUNDLE,
						Constants.BEES_API_KEY), Properties.getString(
						Constants.CREDS_BUNDLE, Constants.BEES_SECRET));
	}

	/*
	@Test
	public void testGetServicesInfo() throws Exception {
		assertNotNull("BeesServicesService was null.", this.beesServicesService);
		assertNotNull("BeesCreds was null.", this.creds);
		assertTrue("BeesCreds was incomplete.", this.creds.isComplete());

		LOGGER.debug(creds.toString());

		ServiceListResponse response = this.beesServicesService
				.getServiceList(this.creds);

		List<ServiceInfo> services = response.getServices();

		for (ServiceInfo service : services) {
			Map<String, Object> properties = BeanUtils.describe(service);
			System.out.println(properties);
		}
	}*/

	@Test
	public void testGetServicesSubscriptionList() throws Exception {
		assertNotNull("BeesServicesService was null.", this.beesServicesService);
		assertNotNull("BeesCreds was null.", this.creds);
		assertTrue("BeesCreds was incomplete.", this.creds.isComplete());

		LOGGER.debug(creds.toString());

		ServiceSubscriptionListResponse response = this.beesServicesService
				.getServiceSubscriptionList(this.creds);

		List<ServiceSubscriptionInfo> services = response.getSubscriptions();

		for (ServiceSubscriptionInfo service : services) {
			Map<String, Object> properties = BeanUtils.describe(service);
			System.out.println(properties);
		}
	}
}
