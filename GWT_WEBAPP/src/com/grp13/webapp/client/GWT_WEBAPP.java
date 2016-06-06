package com.grp13.webapp.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;
import com.grp13.webapp.client.service.WebAppServiceClientImpl;

public class GWT_WEBAPP implements EntryPoint {

	public void onModuleLoad() 
	{
		WebAppServiceClientImpl clientImpl = new WebAppServiceClientImpl(GWT.getModuleBaseURL() + "WebAppService");
		RootPanel.get().add(clientImpl.getMainGUI());
	}

}