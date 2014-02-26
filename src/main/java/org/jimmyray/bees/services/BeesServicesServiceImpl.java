package org.jimmyray.bees.services;

import org.jimmyray.bees.data.model.BeesClientFactory;
import org.jimmyray.bees.data.model.BeesCreds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cloudbees.api.ServiceListResponse;
import com.cloudbees.api.ServiceSubscriptionListResponse;

public class BeesServicesServiceImpl extends BaseService implements
		BeesServicesService {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(BeesServicesServiceImpl.class.getSimpleName());

	public ServiceListResponse getServiceList(BeesCreds creds) throws Exception {
		this.creds = creds;
		this.client = BeesClientFactory.getBeesClient(this.creds);

		this.client.setVerbose(true);

		return client.serviceList(this.creds.getAccount());
	}

	public ServiceSubscriptionListResponse getServiceSubscriptionList (
			BeesCreds creds) throws Exception {
		this.creds = creds;
		this.client = BeesClientFactory.getBeesClient(this.creds);

		this.client.setVerbose(true);

		return client.serviceSubscriptionList(this.creds.getAccount());
	}
}
