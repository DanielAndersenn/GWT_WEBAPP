package com.grp13.webapp.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface WebAppServiceAsync {
	
	void sayHello(String name, AsyncCallback callback);
	void addTwoNumbers(int num1, int num2, AsyncCallback callback);

}
