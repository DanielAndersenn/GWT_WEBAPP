package com.grp13.webapp.shared;

import java.io.Serializable;

public class DALException extends Exception implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	public DALException(String message) { 
		super(message); 
		System.out.println(message);	
		
	}    
	
	public DALException(Exception e) 
	{ 
		
		super(e); 
		
	}
	
	public DALException() {
		super();
		System.out.println("Ender vi her?");
	}
}
