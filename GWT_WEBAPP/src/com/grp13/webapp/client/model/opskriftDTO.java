package com.grp13.webapp.client.model;

public class opskriftDTO {

	private int opskriftID;
	private int medarbejderID;
	private String navn;
	private String ingredienser;
	private String beskrivelse;
	
	private opskriftDTO() {
		//GWT Compliance. Not needed.
	}
	
	public opskriftDTO(int opskriftID, int medarbejderID, String navn, String ingredienser, String beskrivelse) {
		this.opskriftID = opskriftID;
		this.medarbejderID = medarbejderID;
		this.navn = navn;
		this.ingredienser = ingredienser;
		this.beskrivelse = beskrivelse;
	}
	
	public int getOpskriftID() {
		return opskriftID;
	}
	public void setOpskriftID(int opskriftID) {
		this.opskriftID = opskriftID;
	}
	public int getMedarbejderID() {
		return medarbejderID;
	}
	public void setMedarbejderID(int medarbejderID) {
		this.medarbejderID = medarbejderID;
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