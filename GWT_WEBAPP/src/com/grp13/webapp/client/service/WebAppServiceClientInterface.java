package com.grp13.webapp.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface WebAppServiceClientInterface {

	void sayHello(String name);
	void addTwoNumbers(int num1, int num2);
	
}
