package org.jimmyray.bees.services;

import org.jimmyray.bees.data.model.BeesClientFactory;
import org.jimmyray.bees.data.model.BeesCreds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.cloudbees.api.ApplicationListResponse;

@Component
public class BeesAppServiceImpl extends BaseService implements BeesAppService {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(BeesAppServiceImpl.class.getSimpleName());

	public ApplicationListResponse getAppList(BeesCreds creds) throws Exception {
		this.creds = creds;
		this.client = BeesClientFactory.getBeesClient(this.creds);

		this.client.setVerbose(true);

		return client.applicationList();
	}
}
