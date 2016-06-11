package com.grp13.webapp.client.gui;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Widget;
import com.grp13.webapp.client.model.UserDTO;
import com.grp13.webapp.client.service.WebAppServiceAsync;


public class BrowseUsersView extends Composite{

	private static BrowseUsersView1UiBinder uiBinder = GWT.create(BrowseUsersView1UiBinder.class);
	
	@UiField FlexTable t;
	
	interface BrowseUsersView1UiBinder extends UiBinder<Widget, BrowseUsersView> {
	}
	
	public BrowseUsersView(WebAppServiceAsync service) {
		
		initWidget(uiBinder.createAndBindUi(this));

		t.getFlexCellFormatter().setWidth(0, 0, "50px");
		t.getFlexCellFormatter().setWidth(0, 1, "200px");
		t.getFlexCellFormatter().setWidth(0, 2, "100px");
		t.getFlexCellFormatter().setWidth(0, 3, "50px");

		t.getRowFormatter().addStyleName(0,"FlexTable-Header");

		// set headers in flextable
		t.setText(0, 0, "Id");
		t.setText(0, 1, "Navn");
		t.setText(0, 2, "Initialer");
		t.setText(0, 3, "Rolle ID");
		
		service.getUsers(new getUsersCallback());
				
				
		
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
	
	
	
}
