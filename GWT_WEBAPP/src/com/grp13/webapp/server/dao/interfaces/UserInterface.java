package com.grp13.webapp.server.dao.interfaces;

import java.util.List;

import com.grp13.webapp.shared.DALException;
import com.grp13.webapp.shared.UserDTO;

public interface UserInterface {

	UserDTO getUser(String name) throws Exception;
	List<UserDTO> getUserList() throws Exception;
	void createUser(UserDTO user) throws Exception;
	void updateUser(UserDTO user) throws Exception;
	void deleteUser(int a) throws Exception;
	
}
