package com.grp13.webapp.server.dao.implementation;

import java.util.ArrayList;
import java.util.List;

import com.grp13.webapp.client.model.UserDTO;
import com.grp13.webapp.server.dao.interfaces.UserInterface;
import com.grp13.webapp.shared.AccessDeniedException;
import com.grp13.webapp.shared.DALException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserImplementation implements UserInterface{
	
	private Connection conn;
	private Statement stm;
	private List<UserDTO> uList;

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
		System.out.println("getUserList START");
		uList = new ArrayList<UserDTO>();
		String sql = "SELECT * FROM user";
		
		try {
			stm = conn.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				uList.add(new UserDTO(rs.getInt("user_ID"), rs.getString("navn"), rs.getString("ini"), rs.getString("password"), rs.getInt("rolle_id")));
			}
		} catch(SQLException sqlE) {
			System.out.println("SQL Exception occured: " + sqlE);
		}
		
		System.out.println("getUserList END");
		return uList;
	}
	

	@Override
	public boolean createUser(UserDTO user) throws DALException {
		String sql = "INSERT INTO user(navn, ini, password, rolle_id) VALUES ("
					+user.getName()+", "
					+user.getInitials()+", "
					+user.getPassword()+", "
					+user.getRoleID()+");";
		try{
			stm = conn.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			
		}catch(SQLException e){System.out.println("SQL Exception Occurred: "+e);}
		return false;			
	}

	@Override
	public boolean updateUser(UserDTO user) throws DALException {
		return false;
	}

}
