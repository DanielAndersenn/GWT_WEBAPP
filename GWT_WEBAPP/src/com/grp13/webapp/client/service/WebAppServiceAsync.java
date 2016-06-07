package com.grp13.webapp.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.grp13.webapp.client.model.UserDTO;

public interface WebAppServiceAsync {
	
	void validateCredentials(String userID, String password, AsyncCallback callback);
	void getUserData(String userID, AsyncCallback callback);

}
