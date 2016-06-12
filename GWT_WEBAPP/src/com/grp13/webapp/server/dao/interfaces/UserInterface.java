package com.grp13.webapp.server.dao.interfaces;

import java.util.List;

import com.grp13.webapp.client.model.UserDTO;
import com.grp13.webapp.shared.DALException;

public interface UserInterface {

	UserDTO getUser(String name) throws DALException;
	List<UserDTO> getUserList() throws DALException;
	boolean createUser(UserDTO user) throws DALException;
	boolean updateUser(UserDTO user) throws DALException;
	
}
