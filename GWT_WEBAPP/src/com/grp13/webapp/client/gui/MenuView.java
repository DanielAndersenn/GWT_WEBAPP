package com.grp13.webapp.client.gui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Widget;
import com.grp13.webapp.client.service.WebAppServiceClientImpl;


public class MenuView extends Composite{

	private static MenuView1UiBinder uiBinder = GWT.create(MenuView1UiBinder.class);
	
	@UiField Hyperlink user_administration;
	@UiField Hyperlink recipe_list;
	@UiField Hyperlink add_recipe;
	
	WebAppServiceClientImpl main;
	interface MenuView1UiBinder extends UiBinder<Widget, MenuView> {
	}
	
	public MenuView(WebAppServiceClientImpl main) {
		this.main = main;
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	@UiHandler("user_administration")
	void onShowClick(ClickEvent event) {
		main.showUsers();
	}

	
	@UiHandler("recipe_list")
	void onShowRecipesClick(ClickEvent event) {
		main.showRecipes();
	}
	

	@UiHandler("add_recipe")
	void onShowStepsClick(ClickEvent event) {
		main.showSteps();
	}
	
}
