package com.grp13.webapp.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.grp13.webapp.client.service.WebAppServiceAsync;

public class LoginView extends Composite {

	private VerticalPanel vPanel = new VerticalPanel();	
	MenuView menu;
	ContentView contents;
	
	private TextBox txt1;
	private Label resultLbl;
	private PasswordTextBox passwordBox;
	
	private WebAppServiceAsync service;
	
	public LoginView(WebAppServiceAsync service, MenuView menu, ContentView contents) {
		initWidget(this.vPanel);
		this.contents = contents;
		this.service = service;
		this.menu = menu;
			
		Label userID = new Label("User ID: ");
		Label password = new Label("Password: ");
		this.vPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		this.vPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		
		this.vPanel.add(userID);
		
		this.txt1 = new TextBox();
		txt1.setName("User ID");
		txt1.setTitle("USER_ID");
		txt1.setHeight("23px");
		this.vPanel.add(txt1);
		
		this.vPanel.add(password);
		
		passwordBox = new PasswordTextBox();
		this.vPanel.add(passwordBox);
		
		Button login = new Button("Log in");
		login.addClickHandler(new loginClickHandler());
		this.vPanel.add(login);
		
		this.resultLbl = new Label("Login status: ");
		this.vPanel.add(resultLbl);
				
	}
	
	public void setLoginStatus(String loginMessage) {
		
		this.resultLbl.setText(loginMessage);
	}
	
	
	
	private class loginClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			String userID = txt1.getText();
			String password = passwordBox.getText();
			
			service.validateCredentials(userID, password, new loginCallback());
			
		}
		
	}
	
	private class loginCallback implements AsyncCallback<Boolean> {

		@Override
		public void onFailure(Throwable caught) {
			menu.setVisible(false);
			setLoginStatus("ACCESS DENIED!");
			
		}

		@Override
		public void onSuccess(Boolean result) {
			menu.setVisible(true);
			setLoginStatus("ACCESS GRANTED!");
			contents.openShowUsersView();
			
		}
		
	}
	
	
	
	
}
