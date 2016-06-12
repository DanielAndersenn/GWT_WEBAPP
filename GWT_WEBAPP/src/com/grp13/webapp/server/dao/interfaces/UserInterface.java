package com.grp13.webapp.server.dao.interfaces;

import java.util.List;

import com.grp13.webapp.shared.DALException;
import com.grp13.webapp.shared.UserDTO;

public interface UserInterface {

	UserDTO getUser(String name) throws DALException;
	List<UserDTO> getUserList() throws DALException;
	void createUser(UserDTO user) throws DALException;
	void updateUser(UserDTO user) throws DALException;
	void deleteUser(int a) throws DALException;
	
}
