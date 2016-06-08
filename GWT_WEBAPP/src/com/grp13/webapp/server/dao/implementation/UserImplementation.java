package com.grp13.webapp.server.dao.implementation;

import java.util.List;

import com.grp13.webapp.client.model.UserDTO;
import com.grp13.webapp.server.dao.interfaces.UserInterface;
import com.grp13.webapp.shared.DALException;

import java.sql.Connection;

public class UserImplementation implements UserInterface{
	
	Connection conn = null;
	int returnMsg = 0;
	String SQLMsg = "";
	int SQLErr = 0;
	
	public UserImplementation(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public UserDTO getUser(String name) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserDTO> getUserList() throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createUser(UserDTO user) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateUser(UserDTO user) throws DALException {
		// TODO Auto-generated method stub
		
	}

}
