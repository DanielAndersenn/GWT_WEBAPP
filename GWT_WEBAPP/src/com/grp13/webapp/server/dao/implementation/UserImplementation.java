package com.grp13.webapp.server.dao.implementation;

import java.util.ArrayList;
import java.util.List;

import com.grp13.webapp.server.dao.interfaces.UserInterface;
import com.grp13.webapp.shared.AccessDeniedException;
import com.grp13.webapp.shared.DALException;
import com.grp13.webapp.shared.UserDTO;

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
	public void createUser(UserDTO user) throws DALException {
		System.out.println("createUser START");
		CallableStatement stmt = null;
		String cmd = "CALL InsertUser(?, ?, ?, ?, ?, ?, ?)";
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
			System.out.println("createUser END");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("createUser FAIL");
		}
		
	}

	@Override
	public void updateUser(UserDTO user) throws DALException {
		System.out.println("updateUser START");
		CallableStatement stmt = null;
		String cmd = "CALL UpdateUser(?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			stmt = conn.prepareCall(cmd);

			stmt.setInt(1, user.getUserID());
			stmt.setString(2, user.getName());
			stmt.setString(3, user.getInitials());
			stmt.setString(4, user.getPassword());
			stmt.setLong(5, user.getRoleID());
			stmt.registerOutParameter(6, java.sql.Types.INTEGER);
			stmt.registerOutParameter(7, java.sql.Types.VARCHAR);
			stmt.registerOutParameter(8, java.sql.Types.SMALLINT);

			stmt.executeUpdate();

			returnMsg = stmt.getInt(6);
			SQLMsg = stmt.getString(7);
			SQLErr = stmt.getInt(8);

			System.out.println("var returnMsg: " + returnMsg + " var SQLMsg: " + SQLMsg + " var SQLErr: " + SQLErr);
			System.out.println("updateUser END");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("updateUser FAIL");
		}	
	}

	@Override
	public void deleteUser(int a) throws Exception {
		System.out.println("deleteUser START");
		CallableStatement stmt = null;
		String cmd = "CALL DeleteUser(?, ?, ?, ?)";

		try {
			stmt = conn.prepareCall(cmd);

			stmt.setInt(1, a);
			stmt.registerOutParameter(2, java.sql.Types.INTEGER);
			stmt.registerOutParameter(3, java.sql.Types.VARCHAR);
			stmt.registerOutParameter(4, java.sql.Types.SMALLINT);

			stmt.executeUpdate();

			returnMsg = stmt.getInt(2);
			SQLMsg = stmt.getString(3);
			SQLErr = stmt.getInt(4);

			System.out.println("var returnMsg: " + returnMsg + " var SQLMsg: " + SQLMsg + " var SQLErr: " + SQLErr);
			System.out.println("deleteUser END");
		} catch (SQLException e) {
			System.out.println("deleteUser FAIL");
			throw new DALException("deleteUser fejlede !!!");
		}

		
		if(returnMsg != 1) throw new DALException(SQLMsg);
			
	}
}
