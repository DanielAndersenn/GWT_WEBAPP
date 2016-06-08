package com.grp13.webapp.shared;

public class AccessDeniedException extends DALException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AccessDeniedException() {
		super("ACCESS DENIED!");
		System.out.println("Ender vi her?");
		// TODO Auto-generated constructor stub
	}

}