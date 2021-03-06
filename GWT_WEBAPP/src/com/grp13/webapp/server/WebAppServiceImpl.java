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
import com.grp13.webapp.shared.opskriftDTO;
import com.grp13.webapp.shared.opskriftTrinDTO;

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
	public List<UserDTO> getUsers() throws Exception{
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
	public void addUser(UserDTO newUser) throws Exception {
		try{
			dbcontroller.createUser(newUser);
		}catch(DALException e){e.printStackTrace();}
	}

	@Override
	public void deleteUser(int user_ID) throws Exception {
		try{
			dbcontroller.deleteUser(user_ID);
		}catch(DALException e){
			
		throw new DALException(e.getMessage());
		
		}
	}


	@Override
	public List<opskriftDTO> getRecipeList() throws Exception {
		List<opskriftDTO> rList = null;
		try {
			rList = dbcontroller.getRecipeList();
		}catch(DALException e) {
			throw new DALException(e.getMessage());
		}
		return rList;
	}


	@Override
	public void addRecipe(opskriftDTO newRecipe) throws Exception {
		try{
			dbcontroller.createRecipe(newRecipe);
		}catch(DALException e){e.printStackTrace();}
	}


	@Override
	public void deleteRecipe(int id) throws Exception {
		try{
			dbcontroller.deleteUser(id);
		}catch(DALException e){
			
		throw new DALException(e.getMessage());
		
		}
		
	}


	@Override
	public List<opskriftTrinDTO> getRecipeStepList(int recipeID) throws Exception {
		List<opskriftTrinDTO> rsList = null;
		try {
			rsList = dbcontroller.getRecipeStepList(recipeID);
		}catch(DALException e) {
			throw new DALException(e.getMessage());
		}
		return rsList;
	}


	@Override
	public void addRecipeStep(opskriftTrinDTO newStep) throws Exception {
		try{
			dbcontroller.createRecipeStep(newStep);;
		}catch(DALException e){e.printStackTrace();}	
	}

	
}
