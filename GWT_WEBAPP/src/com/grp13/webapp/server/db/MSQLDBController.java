package com.grp13.webapp.server.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.grp13.webapp.client.model.UserDTO;
import com.grp13.webapp.client.model.opskriftDTO;
import com.grp13.webapp.server.dao.implementation.UserImplementation;
import com.grp13.webapp.server.dao.interfaces.UserInterface;
import com.grp13.webapp.shared.AccessDeniedException;
import com.grp13.webapp.shared.DALException;

public class MSQLDBController implements IDBController {
	private static IDBController controller;
	private Connection conn;
	private Statement stm;
	private String url = "jdbc:mysql://80.71.140.75:3306/02324?autoReconnect=true&useSSL=false";
	private String user = "DTU";
	private String pass = "HejVen123";
	private UserInterface userDAO;

	public MSQLDBController() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			this.conn = DriverManager.getConnection(url, user, pass);
			System.out.println("Connection was made!");
		} catch (Exception e) {
			System.out.println(e);
		}
		this.userDAO = new UserImplementation(conn);
	}

	public static  IDBController getInstance() {
		if (controller ==null){
			controller = new MSQLDBController();
		}
		return controller;
	}

	@Override
	public UserDTO getUser(String name) throws DALException {
		
		return userDAO.getUser(name);
	}

	@Override
	public List<UserDTO> getUserList() throws DALException {
		return userDAO.getUserList();
	}

	@Override
	public boolean createUser(UserDTO user) throws DALException {
		return userDAO.createUser(user);
	}

	@Override
	public boolean updateUser(UserDTO user) throws DALException {
		return false;

	}

	@Override
	public boolean validateUser(String userID, String password) throws AccessDeniedException{
		System.out.println("validateUser START");
		boolean userValid = false;
		String realPass = "";
		String sql = "SELECT * FROM user WHERE user_ID =" + userID;
		
		try{
			stm = conn.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			if(rs.next()) {
			realPass = rs.getString("password");
			
			}
			
			System.out.println("Value of entered password: " + password + " Value of realPass: " + realPass);
			
		} catch(SQLException sqlE) {
			System.out.println("SQL Exception occured: " + sqlE);
		}
		
		if(password.equals(realPass)) userValid = true;
		
		if (!userValid) throw new AccessDeniedException();
		
		System.out.println("validateUser END");
		return userValid;
	}

	@Override
	public opskriftDTO getRecipe() throws DALException {
		// TODO Auto-generated method stub
		return null;
	}



}
