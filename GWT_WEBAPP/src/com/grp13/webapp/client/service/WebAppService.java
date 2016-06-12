package com.grp13.webapp.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.grp13.webapp.client.model.UserDTO;
import com.grp13.webapp.shared.AccessDeniedException;
import com.grp13.webapp.shared.DALException;

@RemoteServiceRelativePath("WebAppService")
public interface WebAppService extends RemoteService {
	
	boolean validateCredentials(String userID, String password) throws AccessDeniedException;
	List<UserDTO> getUsers();
	boolean addUser(UserDTO newUser) throws DALException;
	boolean editUser(UserDTO editedUser) throws DALException;
	boolean deleteUser(int id) throws DALException;

}
