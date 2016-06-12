package com.grp13.webapp.client.gui;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.grp13.webapp.client.service.WebAppServiceAsync;

public class ContentView extends Composite{
	
	private WebAppServiceAsync service;
	VerticalPanel contents;
	MenuView menu;
	ContentView contentView;
	
	public ContentView(WebAppServiceAsync service, MenuView menu, ContentView contentView) {
		this.service = service;
		this.contentView = contentView;
		contents = new VerticalPanel();
		this.menu = menu;
		initWidget(this.contents);
	}
	
	//Views to display in contents
	
	public void openLoginView() {
		contents.clear();
		LoginView loginView = new LoginView(service, menu, contentView);
		contents.add(loginView);
	}

	public void openShowUsersView() {
		contents.clear();
		BrowseUsersView buView = new BrowseUsersView(service);
		contents.add(buView);
		
	}
	
	public void openAddUserView() {
		contents.clear();
		AddUserView auView = new AddUserView(service);
		contents.add(auView);
	}

}
