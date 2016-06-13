package com.grp13.webapp.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.grp13.webapp.shared.DALException;
import com.grp13.webapp.shared.UserDTO;
import com.grp13.webapp.shared.opskriftDTO;

public interface WebAppServiceAsync {
	
	void validateCredentials(String userID, String password, AsyncCallback<Boolean> callback);
	void getUsers(AsyncCallback<List<UserDTO>> callback);
	void addUser(UserDTO newUser, AsyncCallback<Void> callback);
	void deleteUser(int user_ID, AsyncCallback<Void> callback);
	void getRecipeList(AsyncCallback<List<opskriftDTO>> callback);
	void addRecipe(opskriftDTO newRecipe, AsyncCallback<Void> callback);
	

}
