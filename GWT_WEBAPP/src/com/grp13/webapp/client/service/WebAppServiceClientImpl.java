package com.grp13.webapp.client.service;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.grp13.webapp.client.gui.LoginView;

public class WebAppServiceClientImpl implements WebAppServiceClientInterface{
	
	private WebAppServiceAsync service;
	private LoginView maingui;
	
	public WebAppServiceClientImpl(String url) {
		this.service = GWT.create(WebAppService.class);
		ServiceDefTarget endpoint = (ServiceDefTarget) this.service;
		endpoint.setServiceEntryPoint(url);
		
		MenuView m = new MenuView(this);
		RootPanel.get("nav").add(m);
		
		this.maingui = new LoginView(this);
	}

	
	public LoginView getLoginView() {
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
