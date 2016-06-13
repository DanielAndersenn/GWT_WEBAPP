package com.grp13.webapp.server.dao.interfaces;

import java.util.List;

import com.grp13.webapp.shared.DALException;
import com.grp13.webapp.shared.opskriftDTO;

public interface opskriftInterface {
	
	List<opskriftDTO> getRecipeList() throws DALException;
	void createRecipe(opskriftDTO opskrift) throws DALException;
	void updateRecipe(opskriftDTO opskrift) throws DALException;
	void deleteRecipe(int a) throws DALException;
	
	

}
