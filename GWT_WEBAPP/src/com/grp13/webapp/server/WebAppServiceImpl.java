package com.grp13.webapp.server;

import java.util.List;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.grp13.webapp.client.service.WebAppService;
import com.grp13.webapp.server.db.IDBController;
import com.grp13.webapp.server.db.MSQLDBController;
import com.grp13.webapp.shared.AccessDeniedException;
import com.grp13.webapp.shared.DALException;
import com.grp13.webapp.shared.UserDTO;

public class WebAppServiceImpl extends RemoteServiceServlet implements WebAppService{

	private static final long serialVersionUID = 1L;
	
	private IDBController dbcontroller;
	
	public WebAppServiceImpl() {

	this.dbcontroller = new MSQLDBController();
	
	}
	
	
	@Override
	public boolean validateCredentials(String userID, String password) throws AccessDeniedException {
		boolean validated;
		validated = dbcontroller.validateUser(userID, password);
		return validated;
	}


	@Override
	public List<UserDTO> getUsers() {
		List<UserDTO> uList = null;
		try {
			uList = dbcontroller.getUserList();
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return uList;
	}


	@Override
	public void addUser(UserDTO newUser) throws DALException {
		try{
			dbcontroller.createUser(newUser);
		}catch(DALException e){e.printStackTrace();}
	}


	@Override
	public void editUser(UserDTO editedUser) throws DALException {
		try{
			dbcontroller.updateUser(editedUser);
		}catch(DALException e){e.printStackTrace();}
	}


	@Override
	public void deleteUser(int user_ID) throws DALException {
		try{
			dbcontroller.deleteUser(user_ID);
		}catch(DALException e){e.printStackTrace();}
	}

	
}
