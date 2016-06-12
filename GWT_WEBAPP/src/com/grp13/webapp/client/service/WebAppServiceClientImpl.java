package com.grp13.webapp.client.service;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.grp13.webapp.client.gui.ContentView;
import com.grp13.webapp.client.gui.LoginView;
import com.grp13.webapp.client.gui.MenuView;

public class WebAppServiceClientImpl {
	
	private WebAppServiceAsync service;
	ContentView contents;
	
	public WebAppServiceClientImpl(String url) {
		this.service = GWT.create(WebAppService.class);
		ServiceDefTarget endpoint = (ServiceDefTarget) this.service;
		endpoint.setServiceEntryPoint(url);
		
		MenuView menu = new MenuView(this);
		menu.setVisible(false);
		
		contents = new ContentView(this.service, menu, contents);		
		
		RootPanel.get("menu").add(menu);
		RootPanel.get("content").add(contents);
		
		
	}
	
	public void run() {
		contents.openLoginView();
	}


	public void showUsers() {
		contents.openUserAdministrationView();
		
	}


	public void editUser() {
		// TODO Auto-generated method stub
		
	}


	public void deleteUser() {
		// TODO Auto-generated method stub
		
	}


	public void showRecipes() {
		// TODO Auto-generated method stub
		
	}


	public void addRecipe() {
		// TODO Auto-generated method stub
		
	}


	public void deleteRecipe() {
		// TODO Auto-generated method stub
		
	}


	public void showSteps() {
		// TODO Auto-generated method stub
		
	}


	public Widget getContentView() {
		// TODO Auto-generated method stub
		return null;
	}

}
