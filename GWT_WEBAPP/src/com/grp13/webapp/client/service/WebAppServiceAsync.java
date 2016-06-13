package com.grp13.webapp.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.TextBox;
import com.grp13.webapp.shared.DALException;
import com.grp13.webapp.shared.UserDTO;
import com.grp13.webapp.shared.opskriftDTO;
import com.grp13.webapp.shared.opskriftTrinDTO;

public interface WebAppServiceAsync {
	
	void validateCredentials(String userID, String password, AsyncCallback<Boolean> callback);
	void getUsers(AsyncCallback<List<UserDTO>> callback);
	void addUser(UserDTO newUser, AsyncCallback<Void> callback);
	void deleteUser(int user_ID, AsyncCallback<Void> callback);
	void getRecipeList(AsyncCallback<List<opskriftDTO>> callback);
	void addRecipe(opskriftDTO newRecipe, AsyncCallback<Void> callback);
	void deleteRecipe(int recipe_ID, AsyncCallback<Void> callback);
	void getRecipeStepList(int recipeID, AsyncCallback<List<opskriftTrinDTO>> callback);
	void addRecipeStep(opskriftTrinDTO newStep, AsyncCallback<Void> callback);
	

}
