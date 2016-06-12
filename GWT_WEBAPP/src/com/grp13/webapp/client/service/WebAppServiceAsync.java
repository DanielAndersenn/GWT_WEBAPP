package com.grp13.webapp.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.grp13.webapp.client.model.UserDTO;
import com.grp13.webapp.shared.DALException;

public interface WebAppServiceAsync {
	
	void validateCredentials(String userID, String password, AsyncCallback<Boolean> callback);
	void getUsers(AsyncCallback<List<UserDTO>> callback);
	void addUser(AsyncCallback<Boolean> callback) throws DALException;
	void editUser(AsyncCallback<Boolean> callback)throws DALException;
	void deleteUser(AsyncCallback<Boolean> callback)throws DALException;
	

}
