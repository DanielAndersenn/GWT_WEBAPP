package com.grp13.webapp.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("WebAppService")
public interface WebAppService extends RemoteService {
	
	String sayHello(String name);
	int addTwoNumbers(int num1, int num2);

}
