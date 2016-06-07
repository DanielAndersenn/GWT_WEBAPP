package com.grp13.webapp.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.grp13.webapp.client.model.UserDTO;
import com.grp13.webapp.client.service.WebAppService;
import com.grp13.webapp.server.dao.implementation.UserImplementation;

public class WebAppServiceImpl extends RemoteServiceServlet implements WebAppService{

	private Connection conn = null;
	private static Statement stm;
	
	private String url = "jdbc:mysql://80.71.140.75:3306/02324?autoReconnect=true&useSSL=false";
	private String user = "DTU";
	private String pass = "HejVen123";
	public WebAppServiceImpl() {
	try {
	Class.forName("com.mysql.jdbc.Driver").newInstance();
	conn = DriverManager.getConnection(url, user, pass);
	stm = conn.createStatement();
	System.out.println("Connection was made!");
	} catch (Exception e) {
		System.out.println(e);
		}
	}
	
	UserImplementation usr = new UserImplementation(conn);
	
	@Override
	public String validateCredentials(String userID, String password) {
		String toReturn = "";
		String sql = "SELECT * FROM medarbejder WHERE medarbejder_ID =" + userID;
		UserDTO user = null;
		
		try{
			
			System.out.println("Er vi her?????????????????");
			
			System.out.println("Er vi her2222?????????????????");
			ResultSet rs = stm.executeQuery(sql);
			if(rs.next()) {
			user = new UserDTO(rs.getInt("medarbejder_ID"), 
							   rs.getString("navn"), 
							   rs.getString("ini"), 
							   rs.getString("password"), 
							   rs.getInt("rolle_id"));
			
			}
			
			System.out.println(user.toString());
			
		} catch(SQLException sqlE) {
			System.out.println("SQL Exception occured: " + sqlE);
		}
		
		toReturn = user.toString();
		/* Old nonDB implementation
		System.out.println("Value of userID: " + userID);
		System.out.println("Value of password: " + password);
		String toReturn = "";
		if(userID.equals("1") && password.equals("hejven123")) {
			toReturn = "Login status: Credentials verified";
		} else {
			toReturn = "Login status: Something went wrong";
		}
			
		return toReturn;
		*/
		
		return toReturn;
		
	}

	@Override
	public UserDTO getUserData(String userID) {
		UserDTO user1 = new UserDTO(10, "Fuckboii 2k16", "FB2K16", "hejven123", 2);
		
		return user1;
	}

	
}
