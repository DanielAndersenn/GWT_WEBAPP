package com.grp13.webapp.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.grp13.webapp.client.model.UserDTO;
import com.grp13.webapp.shared.DALException;

public interface WebAppServiceAsync {
	
	void validateCredentials(String userID, String password, AsyncCallback<Boolean> callback);
	void getUsers(AsyncCallback<List<UserDTO>> callback);
	void addUser(UserDTO newUser, AsyncCallback<Boolean> callback) throws DALException;
	void editUser(UserDTO editedUser, AsyncCallback<Boolean> callback)throws DALException;
	void deleteUser(int user_ID, AsyncCallback<Boolean> callback)throws DALException;
	

}
