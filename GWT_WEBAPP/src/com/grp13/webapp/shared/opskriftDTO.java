package com.grp13.webapp.shared;

import java.io.Serializable;

public class opskriftDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	private int opskriftID;
	private int userID;
	private String navn;
	private String ingredienser;
	private String beskrivelse;
	
	private opskriftDTO() {
		//GWT Compliance. Not needed.
	}
	
	public opskriftDTO(int opskriftID, String navn, String ingredienser, String beskrivelse, int userID) {
		this.opskriftID = opskriftID;
		this.navn = navn;
		this.ingredienser = ingredienser;
		this.beskrivelse = beskrivelse;
		this.userID = userID;
	}
	
	public int getOpskriftID() {
		return opskriftID;
	}
	public void setOpskriftID(int opskriftID) {
		this.opskriftID = opskriftID;
	}
	public int getMedarbejderID() {
		return userID;
	}
	public void setMedarbejderID(int medarbejderID) {
		this.userID = medarbejderID;
	}
	public String getNavn() {
		return navn;
	}
	public void setNavn(String navn) {
		this.navn = navn;
	}
	public String getIngredienser() {
		return ingredienser;
	}
	public void setIngredienser(String ingredienser) {
		this.ingredienser = ingredienser;
	}
	public String getBeskrivelse() {
		return beskrivelse;
	}
	public void setBeskrivelse(String beskrivelse) {
		this.beskrivelse = beskrivelse;
	}
	
	
	
}
