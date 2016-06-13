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
import com.grp13.webapp.client.service.WebAppServiceAsync;
import com.grp13.webapp.shared.UserDTO;
import com.grp13.webapp.shared.opskriftDTO;

public class RecipeAdministrationView extends Composite{

	private static RecipeAdministrationView1UiBinder uiBinder = GWT.create(RecipeAdministrationView1UiBinder.class);
	
	WebAppServiceAsync service;
	
	//Flextable containing userlist
	@UiField FlexTable recipes;
	
	//Add recipe panel
	@UiField HorizontalPanel addPanel;
	@UiField TextBox recipeID;
	@UiField TextBox name;
	@UiField TextBox ingredients;
	@UiField TextBox description;
	@UiField TextBox userID;
	@UiField Button addRecipe;
	
	//Delete recipe panel
	@UiField Button deleteRecipe;
	@UiField TextBox idToDelete;
	@UiField Label deleteLabel;
	
	
	
	interface RecipeAdministrationView1UiBinder extends UiBinder<Widget, RecipeAdministrationView> {
	}
	
	public RecipeAdministrationView(WebAppServiceAsync service) {
		this.service = service;
		initWidget(uiBinder.createAndBindUi(this));
		
		
		//Format flex table
		recipes.getFlexCellFormatter().setWidth(0, 0, "50px");
		recipes.getFlexCellFormatter().setWidth(0, 1, "200px");
		recipes.getFlexCellFormatter().setWidth(0, 2, "400px");
		recipes.getFlexCellFormatter().setWidth(0, 3, "400px");
		recipes.getFlexCellFormatter().setWidth(0, 4, "50px");

		recipes.getRowFormatter().addStyleName(0,"FlexTable-Header");

		//Set headers in flextable
		recipes.setText(0, 0, "Id");
		recipes.setText(0, 1, "Navn");
		recipes.setText(0, 2, "Ingredienser");
		recipes.setText(0, 3, "Beskrivelse");
		recipes.setText(0, 4, "User ID");
		
		//Format add recipe panel
		recipeID.setReadOnly(true);
		
		//Populate flex table
		service.getRecipeList(new getRecipeListCallback());
		
		
	}
	
	@UiHandler("addRecipe")
	void onAddClick(ClickEvent event) {
	
	//Create opskriftDTO object from field values
	opskriftDTO newRecipe = new opskriftDTO(1, name.getText(), ingredients.getText(), description.getText(), Integer.parseInt(userID.getText()));
		service.addRecipe(newRecipe, new addRecipeCallback());

	}
	
	@UiHandler("deleteRecipe")
	void onDeleteClick(ClickEvent event) {
	
		service.deleteRecipe(Integer.parseInt(idToDelete.getText()), new deleteRecipeCallback());

	}
	
	private class getRecipeListCallback implements AsyncCallback<List<opskriftDTO>> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Server fejl!" + caught.getMessage());
			
		}

		@Override
		public void onSuccess(List<opskriftDTO> result) {
			for (int i=0; i < result.size(); i++) {
				recipes.setText(i+1, 0, "" + result.get(i).getOpskriftID());
				recipes.setText(i+1, 1, result.get(i).getNavn());
				recipes.setText(i+1, 2, "" + result.get(i).getIngredienser());
				recipes.setText(i+1, 3, "" + result.get(i).getBeskrivelse());
				recipes.setText(i+1, 4, "" + result.get(i).getMedarbejderID());
				
			}
			
		}
		
	}
	
	private class addRecipeCallback implements AsyncCallback<Void> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Server fejl!" + caught.getMessage());
			
		}

		@Override
		public void onSuccess(Void result) {
			Window.alert("Recipe added!");
			recipeID.setValue("");
			name.setValue("");
			description.setValue("");
			ingredients.setValue("");
			userID.setValue("");
			recipes.insertRow(recipes.getRowCount()-1);
			reloadFlexTable();
		}
		
	}
	
	private class deleteRecipeCallback implements AsyncCallback<Void> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Recipe not deleted. Error msg: " + caught.getMessage());
			
		}

		@Override
		public void onSuccess(Void result) {
			Window.alert("Recipe with ID: " + idToDelete.getText() + " has been deleted.");
			recipes.removeRow(recipes.getRowCount()-1);
			reloadFlexTable();
			
		}
		
	}
	
	public void reloadFlexTable() {
		service.getRecipeList(new getRecipeListCallback());
	}
	
	
}
