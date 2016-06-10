package com.grp13.webapp.client.service;

public interface WebAppServiceClientInterface {

	void validateCredentials(String userID, String password);
	void getUserData(String userID);
	
}
