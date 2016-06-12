package com.grp13.webapp.server.dao.implementation;

import java.util.ArrayList;
import java.util.List;

import com.grp13.webapp.client.model.UserDTO;
import com.grp13.webapp.server.dao.interfaces.UserInterface;
import com.grp13.webapp.shared.AccessDeniedException;
import com.grp13.webapp.shared.DALException;

import java.sql.CallableStatement;
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
		CallableStatement stmt = null;
		String cmd = "CALL InsertUser(?, ?, ?, ?, ?, ?, ?)";
		
		try {
			stmt = conn.prepareCall(cmd);
			
			stmt.setInt(1, user.getUserID());
			stmt.setString(2, user.getName());
			stmt.setString(3, user.getInitials());
			stmt.setString(4, user.getPassword());
			stmt.setLong(5, user.getRoleID());
			stmt.registerOutParameter(5, java.sql.Types.INTEGER);
			stmt.registerOutParameter(6, java.sql.Types.VARCHAR);
			stmt.registerOutParameter(7, java.sql.Types.SMALLINT);
			
			stmt.executeUpdate();
			
			returnMsg = stmt.getInt(5);
			SQLMsg = stmt.getString(6);
			SQLErr = stmt.getInt(7);
			
			System.out.println("var returnMsg: " + returnMsg + " var SQLMsg: " + SQLMsg + " var SQLErr: " + SQLErr);
			
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;			
		}		
	}

	@Override
	public boolean updateUser(UserDTO user) throws DALException {
		CallableStatement stmt = null;
		String cmd = "CALL UpdateUser(?, ?, ?, ?, ?, ?, ?)";
		
		try {
			stmt = conn.prepareCall(cmd);
			
			stmt.setString(1, user.getName());
			stmt.setString(2, user.getInitials());
			stmt.setString(3, user.getPassword());
			stmt.setLong(4, user.getRoleID());
			stmt.registerOutParameter(5, java.sql.Types.INTEGER);
			stmt.registerOutParameter(6, java.sql.Types.VARCHAR);
			stmt.registerOutParameter(7, java.sql.Types.SMALLINT);
			
			stmt.executeUpdate();
			
			returnMsg = stmt.getInt(5);
			SQLMsg = stmt.getString(6);
			SQLErr = stmt.getInt(7);
			
			System.out.println("var returnMsg: " + returnMsg + " var SQLMsg: " + SQLMsg + " var SQLErr: " + SQLErr);
			
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;			
		}	
	}

}
