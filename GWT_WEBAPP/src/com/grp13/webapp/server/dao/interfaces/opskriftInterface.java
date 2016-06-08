package com.grp13.webapp.server.dao.interfaces;

import com.grp13.webapp.client.model.opskriftDTO;
import com.grp13.webapp.shared.DALException;

public interface opskriftInterface {
	
	opskriftDTO getRecipe() throws DALException;
	

}
