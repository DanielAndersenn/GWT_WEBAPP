package com.grp13.webapp.client.gui;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.grp13.webapp.client.model.UserDTO;
import com.grp13.webapp.client.service.WebAppServiceAsync;
import com.grp13.webapp.shared.DALException;


public class BrowseUsersView extends Composite{

	private static BrowseUsersView1UiBinder uiBinder = GWT.create(BrowseUsersView1UiBinder.class);
	
	//Flextable containing userlist
	@UiField FlexTable t;
	
	//Add user panel
	@UiField HorizontalPanel addPanel;
	@UiField TextBox userID;
	@UiField TextBox name;
	@UiField TextBox initials;
	@UiField TextBox roleID;
	@UiField PasswordTextBox password;
	@UiField Button addUser;
	
	//Delete user panel
	@UiField Button deleteUser;
	@UiField TextBox idToDelete;
	@UiField Label deleteLabel;
	
	WebAppServiceAsync service;
	
	interface BrowseUsersView1UiBinder extends UiBinder<Widget, BrowseUsersView> {
	}
	
	public BrowseUsersView(WebAppServiceAsync service) {
		
		this.service = service;
		initWidget(uiBinder.createAndBindUi(this));
		
		//Format flex table
		t.getFlexCellFormatter().setWidth(0, 0, "50px");
		t.getFlexCellFormatter().setWidth(0, 1, "200px");
		t.getFlexCellFormatter().setWidth(0, 2, "100px");
		t.getFlexCellFormatter().setWidth(0, 3, "50px");

		t.getRowFormatter().addStyleName(0,"FlexTable-Header");

		//Set headers in flextable
		t.setText(0, 0, "Id");
		t.setText(0, 1, "Navn");
		t.setText(0, 2, "Initialer");
		t.setText(0, 3, "Rolle ID");
		
		//Format add user panel
		userID.setReadOnly(true);
		password.setValue("Password");
		
		//Call to populate flextable on load
		service.getUsers(new getUsersCallback());			
		
	}
	
	@UiHandler("deleteUser")
	void onDeleteClick(ClickEvent event) {
	
	try {
		service.deleteUser(Integer.parseInt(idToDelete.getText()), new deleteUserCallback());
	} catch (DALException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}
	
	@UiHandler("addUser")
	void onAddClick(ClickEvent event) {
	
	//Create UserDTO object from fieldvalues
	UserDTO newUser = new UserDTO(1, name.getText(), initials.getText(), password.getText(), Integer.parseInt(roleID.getText()));
	
	try {
		service.addUser(newUser, new addUserCallback());
	} catch (DALException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}
	
	private class getUsersCallback implements AsyncCallback<List<UserDTO>> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Server fejl!" + caught.getMessage());
			
		}

		@Override
		public void onSuccess(List<UserDTO> result) {
			for (int i=0; i < result.size(); i++) {
				t.setText(i+1, 0, "" + result.get(i).getUserID());
				t.setText(i+1, 1, result.get(i).getName());
				t.setText(i+1, 2, "" + result.get(i).getInitials());
				t.setText(i+1, 3, "" + result.get(i).getRoleID());	
			}
			
		}
		
	}
	
	private class deleteUserCallback implements AsyncCallback {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("User not deleted. Error msg: " + caught.getMessage());
			
		}

		@Override
		public void onSuccess(Object result) {
			
			Window.alert("User with ID: " + idToDelete.getText() + " has been deleted.");
			t.removeRow(t.getRowCount()-1);
			reloadFlexTable();
			
		}
		
	}
	
	private class addUserCallback implements AsyncCallback {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("User not added. Error msg: " + caught.getMessage());
			
		}

		@Override
		public void onSuccess(Object result) {
			Window.alert("User added!");
			
		}
		
	}
	
	public void reloadFlexTable() {
		service.getUsers(new getUsersCallback());
	}
	
	
	
}
