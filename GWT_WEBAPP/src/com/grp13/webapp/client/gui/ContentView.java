package com.grp13.webapp.client.gui;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.grp13.webapp.client.service.WebAppServiceAsync;

public class ContentView extends Composite{
	
	private WebAppServiceAsync service;
	VerticalPanel contents;
	
	public ContentView(WebAppServiceAsync service) {
		this.service = service;
		contents = new VerticalPanel();
		initWidget(this.contents);
	}
	
	//Views to display in contents
	
	public void openLoginView() {
		contents.clear();
		LoginView loginView = new LoginView(service);
		contents.add(loginView);
	}

}
