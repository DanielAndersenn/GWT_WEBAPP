package com.grp13.webapp.server.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.grp13.webapp.server.dao.implementation.OpskriftImplementation;
import com.grp13.webapp.server.dao.implementation.OpskriftTrinImplementation;
import com.grp13.webapp.server.dao.implementation.UserImplementation;
import com.grp13.webapp.server.dao.interfaces.OpskriftTrinInterface;
import com.grp13.webapp.server.dao.interfaces.UserInterface;
import com.grp13.webapp.server.dao.interfaces.opskriftInterface;
import com.grp13.webapp.shared.AccessDeniedException;
import com.grp13.webapp.shared.DALException;
import com.grp13.webapp.shared.UserDTO;
import com.grp13.webapp.shared.opskriftDTO;
import com.grp13.webapp.shared.opskriftTrinDTO;

public class MSQLDBController implements IDBController {
	private static IDBController controller;
	private Connection conn;
	private Statement stm;
	private String url = "jdbc:mysql://80.71.140.75:3306/02324?autoReconnect=true&useSSL=false";
	private String user = "DTU";
	private String pass = "HejVen123";
	private UserInterface userDAO;
	private opskriftInterface opskriftDAO;
	private OpskriftTrinInterface opskriftTrinDAO;

	/**
	 * contructor.
	 */
	public MSQLDBController() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			this.conn = DriverManager.getConnection(url, user, pass);
			System.out.println("Connection was made!");
		} catch (Exception e) {
			System.out.println(e);
		}
		this.userDAO = new UserImplementation(conn);
		this.opskriftDAO = new OpskriftImplementation(conn);
		this.opskriftTrinDAO = new OpskriftTrinImplementation(conn);
	}

	public static  IDBController getInstance() {
		if (controller ==null){
			controller = new MSQLDBController();
		}
		return controller;
	}

	/**
	 * not yet implemented.
	 * @param name of the user to be returned from database.
	 * @return a user.
	 */
	@Override
	public UserDTO getUser(String name) throws Exception {
		
		return userDAO.getUser(name);
	}

	/**
	 * @return list of all users in the database.
	 */
	@Override
	public List<UserDTO> getUserList() throws Exception {
		return userDAO.getUserList();
	}

	/**
	 * @param a user to be added to the database.
	 */
	@Override
	public void createUser(UserDTO user) throws Exception {
		userDAO.createUser(user);
	}

	/**
	 * @param a user to be updated in the database.
	 */
	@Override
	public void updateUser(UserDTO user) throws Exception {
		userDAO.updateUser(user);
	}
	
	/**
	 * @param an integer that represents the id of the user in the database to be deleted.
	 */
	@Override
	public void deleteUser(int a) throws Exception {
		userDAO.deleteUser(a);
	}

	/**
	 * @param the userid (here, gotten from the webpage) to validate.
	 * @param the password (here, gotten from the webpage) to validate.
	 * @return boolean about whether the userid and password is true.
	 */
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

	public opskriftDTO getRecipe() throws DALException {
		return null;
	}

	/**
	 * @return all recipes from the database.
	 */
	@Override
	public List<opskriftDTO> getRecipeList() throws DALException {
		return opskriftDAO.getRecipeList();
	}

	/**
	 * @param a recipeDTO to be inserted into the database.
	 */
	@Override
	public void createRecipe(opskriftDTO opskrift) throws DALException {
		opskriftDAO.createRecipe(opskrift);
		
	}

	/**
	 * @param a recipeDTO that is to be updated in the database.
	 */
	@Override
	public void updateRecipe(opskriftDTO opskrift) throws DALException {
		opskriftDAO.updateRecipe(opskrift);
		
	}

	/**
	 * @param an integer that represents the id of the recipe in the database to be deleted.
	 */
	@Override
	public void deleteRecipe(int a) throws DALException {
		opskriftDAO.deleteRecipe(a);
		
	}

	/**
	 * @param an integer that represents the recipe of the list of steps to return.
	 * @return list of all steps in a recipe.
	 */
	@Override
	public List<opskriftTrinDTO> getRecipeStepList(int recipeID) throws DALException {
		return opskriftTrinDAO.getRecipeStepList(recipeID);
	}

	/**
	 * @param a recipestepDTO for a recipe to be added to the database.
	 */
	@Override
	public void createRecipeStep(opskriftTrinDTO opskriftTrin) throws DALException {
		opskriftTrinDAO.createRecipeStep(opskriftTrin);
		
	}

	/**
	 * @param a recipestepDTO to be updated in the database. not yet implemented.
	 */
	@Override
	public void updateRecipeStep(opskriftTrinDTO opskriftTrin) throws DALException {
		opskriftTrinDAO.updateRecipeStep(opskriftTrin);
		
	}

	/**
	 * @param an integer that represents a recipestep of a recipe to be deleted.
	 */
	@Override
	public void deleteRecipeStep(int a) throws DALException {
		opskriftTrinDAO.deleteRecipeStep(a);
		
	}
	



}
