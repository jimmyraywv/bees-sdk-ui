package org.jimmyray.bees.data.model;

import java.text.MessageFormat;

public class BeesCreds extends BaseModel {

	private String account = ""; // from CloudBees account
	private String apiKey = ""; // from CloudBees account
	private String apiUrl = "";
	private String secret = ""; // from CloudBees account

	public BeesCreds() {

	}

	public BeesCreds(final String account, final String apiUrl,
			final String apiKey, final String secret) {
		this.account = account;
		this.apiUrl = apiUrl;
		this.apiKey = apiKey;
		this.secret = secret;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getApiUrl() {
		return apiUrl;
	}

	public void setApiUrl(final String apiUrl) {
		this.apiUrl = apiUrl;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(final String apiKey) {
		this.apiKey = apiKey;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(final String secret) {
		this.secret = secret;
	}

	@Override
	public boolean isComplete() {
		boolean status = false;

		if (this.account != null && this.apiKey != null && this.apiUrl != null
				&& this.secret != null) {
			status = true;
		}

		return status;
	}

	@Override
	public String toString() {
		return MessageFormat.format(
				"BeesCreds[Account={0}, API URL={1}, API Key={2}, Secret={3}]",
				this.getAccount(), this.getApiUrl(), this.getApiKey(),
				this.getSecret());
	}
}
