package com.grp13.webapp.client.service;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.grp13.webapp.client.gui.MainGUI;

public class WebAppServiceClientImpl implements WebAppServiceClientInterface{
	
	private WebAppServiceAsync service;
	private MainGUI maingui;
	
	public WebAppServiceClientImpl(String url) {
		System.out.println(url);
		this.service = GWT.create(WebAppService.class);
		ServiceDefTarget endpoint = (ServiceDefTarget) this.service;
		endpoint.setServiceEntryPoint(url);
		
		this.maingui = new MainGUI(this);
	}
	
	@Override
	public void sayHello(String name) {
		this.service.sayHello(name, new DefaultCallback());
		
	}

	@Override
	public void addTwoNumbers(int num1, int num2) {
		this.service.addTwoNumbers(num1, num2, new DefaultCallback());
		
	}
	
	public MainGUI getMainGUI() {
		return this.maingui;
	}
	
	private class DefaultCallback implements AsyncCallback {

		@Override
		public void onFailure(Throwable caught) {
			System.out.println("An error has occured");
			
		}

		@Override
		public void onSuccess(Object result) {
			
			System.out.println("Response received");
			
			if(result instanceof String) {
				String greeting = (String) result;
				maingui.updateLabel(greeting);
			} else if(result instanceof Integer) {
				int sum = (Integer) result;
				maingui.updateSumLabel(sum);
			}
			
			
		}
		
	}

}
