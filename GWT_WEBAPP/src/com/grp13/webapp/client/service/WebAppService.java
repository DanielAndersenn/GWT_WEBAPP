package com.grp13.webapp.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.grp13.webapp.shared.AccessDeniedException;
import com.grp13.webapp.shared.DALException;
import com.grp13.webapp.shared.UserDTO;
import com.grp13.webapp.shared.opskriftDTO;

@RemoteServiceRelativePath("WebAppService")
public interface WebAppService extends RemoteService {
	
	boolean validateCredentials(String userID, String password) throws AccessDeniedException;
	List<UserDTO> getUsers() throws Exception;
	void addUser(UserDTO newUser) throws Exception;
	void deleteUser(int id) throws Exception;
	List<opskriftDTO> getRecipeList() throws Exception;
	void addRecipe(opskriftDTO newRecipe) throws Exception;
}