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
import com.grp13.webapp.server.db.IDBController;
import com.grp13.webapp.server.db.MSQLDBController;
import com.grp13.webapp.shared.AccessDeniedException;

public class WebAppServiceImpl extends RemoteServiceServlet implements WebAppService{

	private IDBController dbcontroller;
	public WebAppServiceImpl() {
	this.dbcontroller = new MSQLDBController();
	}

	@Override
	public UserDTO getUserData(String userID) {
		UserDTO user1 = new UserDTO(10, "Fuckboii 2k16", "FB2K16", "hejven123", 2);
		
		return user1;
	}

	@Override
	public boolean validateCredentials(String userID, String password) throws AccessDeniedException {
		boolean validated;
		validated = dbcontroller.validateUser(userID, password);
		return validated;
	}

	
}
