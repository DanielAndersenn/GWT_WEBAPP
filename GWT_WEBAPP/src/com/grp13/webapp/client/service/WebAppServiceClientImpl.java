package com.grp13.webapp.client.service;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.grp13.webapp.client.gui.MainGUI;
import com.grp13.webapp.client.model.UserDTO;

public class WebAppServiceClientImpl implements WebAppServiceClientInterface{
	
	private WebAppServiceAsync service;
	private MainGUI maingui;
	
	public WebAppServiceClientImpl(String url) {
		System.out.println(url);
		this.service = GWT.create(WebAppService.class);
		ServiceDefTarget endpoint = (ServiceDefTarget) this.service;
		endpoint.setServiceEntryPoint(url);
		
		this.maingui = new MainGUI(this);
	}

	
	public MainGUI getMainGUI() {
		return this.maingui;
	}
	
	private class DefaultCallback implements AsyncCallback {

		@Override
		public void onFailure(Throwable caught) {
			
			maingui.setLoginStatus(caught.getMessage());
			
		}

		@Override
		public void onSuccess(Object result) {
			
			boolean validated = (boolean) result;
			
			if(validated==true) {
				maingui.setLoginStatus("ACCESS GRANTED!");
			}
					
			
		}
		
	}

	@Override
	public void validateCredentials(String userID, String password) {
		this.service.validateCredentials(userID, password, new DefaultCallback());
		
	}


	@Override
	public void getUserData(String userID) {
		this.service.getUserData(userID, new DefaultCallback());
		
	}

}
