package com.grp13.webapp.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface WebAppServiceClientInterface {

	void validateCredentials(String userID, String password);
	void getUserData(String userID);
	
}
