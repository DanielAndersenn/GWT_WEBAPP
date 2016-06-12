package com.grp13.webapp.shared;

public class DALException extends Exception
{
	private static final long serialVersionUID = -5490114628089339322L;
	public DALException(String message) { super(message); }    
	public DALException(Exception e) { super(e); }
	public DALException() {
		System.out.println("Ender vi her?");
	}
}
