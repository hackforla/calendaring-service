package com.hfla.service.calendar.legacy.models;

public class ConnectedCredentials {

	private String accessToken;
	private String nylasId;

	public ConnectedCredentials(String nylasId) {
		this.nylasId = nylasId;
	}

	public String getAccessToken() {
		return accessToken;
	}
}
