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
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.grp13.webapp.client.gui.RecipeAdministrationView.RecipeAdministrationView1UiBinder;
import com.grp13.webapp.client.service.WebAppServiceAsync;
import com.grp13.webapp.shared.opskriftDTO;
import com.grp13.webapp.shared.opskriftTrinDTO;

public class RecipeStepView extends Composite{

private static RecipeStepView1UiBinder uiBinder = GWT.create(RecipeStepView1UiBinder.class);
	
	WebAppServiceAsync service;
	
	//Flextable containing recipe steps
	@UiField FlexTable recipeSteps;
	
	//Add recipestep panel
	@UiField HorizontalPanel addPanel;
	@UiField TextBox recipeID;
	@UiField TextBox maengde;
	@UiField TextBox tid;
	@UiField TextBox trin;
	@UiField TextBox ingrediens;
	@UiField TextBox enhed;
	@UiField TextBox handling;
	@UiField CheckBox vejes;
	@UiField Button addRecipeStep;
	
	//Search for recipe steps panel
	@UiField Label searchLabel;
	@UiField TextBox recipeStepSearch;
	@UiField Button searchRecipeSteps;
	
	interface RecipeStepView1UiBinder extends UiBinder<Widget, RecipeStepView> {
	}
	
	public RecipeStepView(WebAppServiceAsync service) {
		this.service = service;
		initWidget(uiBinder.createAndBindUi(this));
		
		
		//Format flex table
		recipeSteps.getFlexCellFormatter().setWidth(0, 0, "50px");
		recipeSteps.getFlexCellFormatter().setWidth(0, 1, "75px");
		recipeSteps.getFlexCellFormatter().setWidth(0, 2, "50px");
		recipeSteps.getFlexCellFormatter().setWidth(0, 3, "50px");
		recipeSteps.getFlexCellFormatter().setWidth(0, 4, "100px");
		recipeSteps.getFlexCellFormatter().setWidth(0, 5, "50px");
		recipeSteps.getFlexCellFormatter().setWidth(0, 6, "600px");
		recipeSteps.getFlexCellFormatter().setWidth(0, 7, "50px");

		recipeSteps.getRowFormatter().addStyleName(0,"FlexTable-Header");

		//Set headers in flextable
		recipeSteps.setText(0, 0, "ID");
		recipeSteps.setText(0, 1, "Maengde");
		recipeSteps.setText(0, 2, "Tid");
		recipeSteps.setText(0, 3, "Trin");
		recipeSteps.setText(0, 4, "Ingrediens");
		recipeSteps.setText(0, 5, "Enhed");
		recipeSteps.setText(0, 6, "Handling");
		recipeSteps.setText(0, 7, "vejes");
		
		//Format add recipeStep panel
		recipeID.setReadOnly(true);
		
		//Populate flex table
		//service.getRecipeList(new getRecipeListCallback());
		
		
	}
	
	@UiHandler("searchRecipeSteps")
	void onSearchClick(ClickEvent event) {
	int idToSearch;
	idToSearch = Integer.parseInt(recipeStepSearch.getText());
	
		service.getRecipeStepList(idToSearch, new getRecipeStepListCallback());
		recipeID.setText(recipeStepSearch.getText());

	}
	
	@UiHandler("addRecipeStep")
	void onAddClick(ClickEvent event) {
	
	//Create opskriftTrinDTO object from field values
	opskriftTrinDTO newStep = new opskriftTrinDTO(Integer.valueOf(recipeStepSearch.getText()), 
												  Double.valueOf(maengde.getText()),
												  Integer.valueOf(tid.getText()),
												  Integer.valueOf(trin.getText()),
												  ingrediens.getText(),
												  enhed.getText(),
												  handling.getText(),
												  vejes.getValue());
	
	service.addRecipeStep(newStep, new addRecipeStepCallback());
	

	}
	
	
	
	private class getRecipeStepListCallback implements AsyncCallback<List<opskriftTrinDTO>> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Recipe not deleted. Error msg: " + caught.getMessage());
			
		}

		@Override
		public void onSuccess(List<opskriftTrinDTO> result) {
			for (int i=0; i < result.size(); i++) {
				recipeSteps.setText(i+1, 0, "" + result.get(i).getOpskriftID());
				recipeSteps.setText(i+1, 1, String.valueOf(result.get(i).getMaengde()));
				recipeSteps.setText(i+1, 2, "" + result.get(i).getTid());
				recipeSteps.setText(i+1, 3, "" + result.get(i).getTrin());
				recipeSteps.setText(i+1, 4, "" + result.get(i).getIngrediens());
				recipeSteps.setText(i+1, 5, "" + result.get(i).getEnhed());
				recipeSteps.setText(i+1, 6, "" + result.get(i).getHandling());
				recipeSteps.setText(i+1, 7, "" + result.get(i).isVaegt());
				
			}
			
		}
		
	}
	
	private class addRecipeStepCallback implements AsyncCallback<Void> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Opskrift trin ikke tilføjet" + caught.getMessage());
			
		}

		@Override
		public void onSuccess(Void result) {
			Window.alert("Recipe step added!");
			recipeID.setValue("");
			maengde.setValue("");
			tid.setValue("");
			trin.setValue("");
			ingrediens.setValue("");
			enhed.setValue("");
			handling.setValue("");
			vejes.setChecked(false);
			recipeSteps.insertRow(recipeSteps.getRowCount()-1);
			reloadFlexTable();
		}
		
	}
	
	
	public void reloadFlexTable() {
		
		int idToSearch;
		idToSearch = Integer.parseInt(recipeStepSearch.getText());
		
		service.getRecipeStepList(idToSearch, new getRecipeStepListCallback());
	}
	
	
	
	
	
}