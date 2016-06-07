package com.grp13.webapp.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.grp13.webapp.client.service.WebAppServiceClientImpl;

public class MainGUI extends Composite {

	private VerticalPanel vPanel = new VerticalPanel();	
	
	private TextBox txt1;
	private Label resultLbl;
	private PasswordTextBox passwordBox;
	
	private WebAppServiceClientImpl serviceImpl;
	
	public MainGUI(WebAppServiceClientImpl serviceImpl) {
		initWidget(this.vPanel);
		this.serviceImpl = serviceImpl;
			
		Label userID = new Label("User ID: ");
		Label password = new Label("Password: ");
		this.vPanel.setWidth("100%");
		this.vPanel.setHeight("100%");
		this.vPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		this.vPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		this.vPanel.setSpacing(2);
		
		this.vPanel.add(userID);
		
		this.txt1 = new TextBox();
		txt1.setName("User ID");
		txt1.setTitle("USER_ID");
		this.vPanel.add(txt1);
		
		this.vPanel.add(password);
		
		passwordBox = new PasswordTextBox();
		this.vPanel.add(passwordBox);
		
		Button login = new Button("Log in");
		login.addClickHandler(new LoginClickHandler());
		this.vPanel.add(login);
		
		this.resultLbl = new Label("Login status: ");
		this.vPanel.add(resultLbl);
				
	}
	
	public void setLoginStatus(String greeting) {
		this.resultLbl.setText(greeting);
	}
	
	
	private class LoginClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			String userID = txt1.getText();
			String password = passwordBox.getText();
			serviceImpl.validateCredentials(userID, password);
			
		}
		
	}
	
	
}
