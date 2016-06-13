package com.grp13.webapp.server.dao.interfaces;

import java.util.List;

import com.grp13.webapp.shared.DALException;
import com.grp13.webapp.shared.opskriftTrinDTO;

public interface OpskriftTrinInterface {

	List<opskriftTrinDTO> getRecipeStepList(int recipeID) throws DALException;
	void createRecipeStep(opskriftTrinDTO opskriftTrin) throws DALException;
	void updateRecipeStep(opskriftTrinDTO opskriftTrin) throws DALException;
	void deleteRecipeStep(int a) throws DALException;
	
}
