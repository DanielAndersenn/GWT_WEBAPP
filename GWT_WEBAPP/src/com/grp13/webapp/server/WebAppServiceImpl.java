package com.grp13.webapp.server;

import java.util.List;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.grp13.webapp.client.model.UserDTO;
import com.grp13.webapp.client.service.WebAppService;
import com.grp13.webapp.server.db.IDBController;
import com.grp13.webapp.server.db.MSQLDBController;
import com.grp13.webapp.shared.AccessDeniedException;
import com.grp13.webapp.shared.DALException;

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
	public boolean addUser(UserDTO newUser) throws DALException {
		try{
			dbcontroller.createUser(newUser);
			return true;
		}catch(DALException e){e.printStackTrace();}
		return false;
	}


	@Override
	public boolean editUser(UserDTO editedUser) throws DALException {
		try{
			dbcontroller.updateUser(editedUser);
			return true;
		}catch(DALException e){e.printStackTrace();}
		return false;
	}


	@Override
	public boolean deleteUser(int user_ID) throws DALException {
		// TODO Auto-generated method stub
		return false;
	}

	
}
