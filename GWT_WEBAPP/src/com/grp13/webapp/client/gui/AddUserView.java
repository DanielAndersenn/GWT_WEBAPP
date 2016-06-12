package com.grp13.webapp.client.gui;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.grp13.webapp.client.model.UserDTO;
import com.grp13.webapp.client.service.WebAppServiceAsync;
import com.grp13.webapp.shared.DALException;

public class AddUserView extends Composite{

	private static AddUserView1UiBinder uiBinder = GWT.create(AddUserView1UiBinder.class);
	
	@UiField Button save;
	@UiField TextBox name;
	@UiField TextBox initials;
	@UiField TextBox password;
	@UiField TextBox role_ID;
	
	interface AddUserView1UiBinder extends UiBinder<Widget, AddUserView> {
		
	}
	
	WebAppServiceAsync service;
	
	public AddUserView(WebAppServiceAsync service) {
		initWidget(uiBinder.createAndBindUi(this));
		this.service = service;
	}
	
	@UiHandler("save")
	void onSaveClick(ClickEvent event) {
		
	//Create UserDTO object from fieldvalues
	UserDTO newUser = new UserDTO(1, name.getText(), initials.getText(), password.getText(), Integer.parseInt(role_ID.getText()));
	
	try {
		service.addUser(newUser, new addUserCallback());
	} catch (DALException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}
	
	private class addUserCallback implements AsyncCallback<Boolean> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Server fejl!" + caught.getMessage());
			
		}

		@Override
		public void onSuccess(Boolean result) {
			Window.alert("User added!!!!!!!!!!");
			
		}
		
	}
	
}
