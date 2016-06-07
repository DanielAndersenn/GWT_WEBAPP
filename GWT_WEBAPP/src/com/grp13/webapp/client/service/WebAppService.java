package com.grp13.webapp.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.grp13.webapp.client.model.UserDTO;

@RemoteServiceRelativePath("WebAppService")
public interface WebAppService extends RemoteService {
	
	String validateCredentials(String userID, String password);
	UserDTO getUserData(String userID);

}
