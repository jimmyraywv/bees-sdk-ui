package org.jimmyray.bees.services;

import org.jimmyray.bees.data.model.BeesCreds;

import com.cloudbees.api.BeesClient;

public abstract class BaseService {
	BeesClient client;
	BeesCreds creds;

	// Getters and Setters
	public BeesClient getClient() throws Exception {
		return this.client;
	}

	public void setClient(BeesClient client) {
		this.client = client;
	}

	public BeesCreds getCreds() {
		return creds;
	}

	public void setCreds(BeesCreds creds) {
		this.creds = creds;
	}
}
