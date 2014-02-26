package org.jimmyray.bees.data.model;

import org.jimmyray.bees.exceptions.BeesSdkException;
import org.jimmyray.bees.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cloudbees.api.BeesClient;

public class BeesClientFactory {
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(BeesClientFactory.class.getSimpleName());

	public static BeesClient getBeesClient(BeesCreds creds)
			throws BeesSdkException {
		
		LOGGER.debug(creds.toString());
		
		if (creds == null || !creds.isComplete()) {
			LOGGER.error(Constants.INCOMPLETE_CREDS_EXCEPTION);
			throw new BeesSdkException(Constants.INCOMPLETE_CREDS_EXCEPTION);
		}

		return new BeesClient(creds.getApiUrl(), creds.getApiKey(),
				creds.getSecret(),"json","1.0");
	}
}
