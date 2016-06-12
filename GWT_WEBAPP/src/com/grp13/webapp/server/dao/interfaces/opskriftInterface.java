package com.grp13.webapp.server.dao.interfaces;

import com.grp13.webapp.shared.DALException;
import com.grp13.webapp.shared.opskriftDTO;

public interface opskriftInterface {
	
	opskriftDTO getRecipe() throws DALException;
	

}
