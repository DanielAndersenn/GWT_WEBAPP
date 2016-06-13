package com.grp13.webapp.server.db;

import com.grp13.webapp.server.dao.interfaces.OpskriftTrinInterface;
import com.grp13.webapp.server.dao.interfaces.UserInterface;
import com.grp13.webapp.server.dao.interfaces.opskriftInterface;
import com.grp13.webapp.shared.AccessDeniedException;

public interface IDBController extends UserInterface, opskriftInterface, OpskriftTrinInterface {

	boolean validateUser(String userID, String password) throws AccessDeniedException;

}
