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
	
	@UiField Hyperlink show_users;
	@UiField Hyperlink add_user;
	@UiField Hyperlink edit_user;
	@UiField Hyperlink delete_user;
	@UiField Hyperlink show_recipes;
	@UiField Hyperlink add_recipe;
	@UiField Hyperlink delete_recipe;
	@UiField Hyperlink show_steps;
	
	
	WebAppServiceClientImpl main;
	interface MenuView1UiBinder extends UiBinder<Widget, MenuView> {
	}
	
	public MenuView(WebAppServiceClientImpl main) {
		this.main = main;
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	@UiHandler("show_users")
	void onShowClick(ClickEvent event) {
		main.showUsers();
	}
	@UiHandler("add_user")
	void onAddClick(ClickEvent event) {
		main.addUser();
	}
	@UiHandler("edit_user")
	void onEditClick(ClickEvent event) {
		main.editUser();
	}
	@UiHandler("delete_user")
	void onDeleteClick(ClickEvent event) {
		main.deleteUser();
	}
	
	@UiHandler("show_recipes")
	void onShowRecipesClick(ClickEvent event) {
		main.showRecipes();
	}
	
	@UiHandler("add_recipe")
	void onAddRecipeClick(ClickEvent event) {
		main.addRecipe();
	}
	
	@UiHandler("delete_recipe")
	void onDeleteRecipeClick(ClickEvent event) {
		main.deleteRecipe();
	}
	
	@UiHandler("show_steps")
	void onShowStepsClick(ClickEvent event) {
		main.showSteps();
	}
	
}
