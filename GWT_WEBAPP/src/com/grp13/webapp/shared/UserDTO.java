package com.grp13.webapp.shared;

import java.io.Serializable;

public class UserDTO implements Serializable{

	private int userID;
	private int roleID;
	private String name;
	private String initials;
	private String password;
	
	private UserDTO() {
		//GWT Compliance. No use
	}
	
	public UserDTO(int userID, String name, String initials, String password, int roleID) {
		this.userID = userID;
		this.name = name;
		this.initials = initials;
		this.password = password;
		this.roleID = roleID;
	}
	
	
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public int getRoleID() {
		return roleID;
	}
	public void setRoleID(int roleID) {
		this.roleID = roleID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getInitials() {
		return initials;
	}
	public void setInitials(String initials) {
		this.initials = initials;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserDTO [userID=" + userID + ", roleID=" + roleID + ", name=" + name + ", initials=" + initials
				+ ", password=" + password + "]";
	}
	
}
