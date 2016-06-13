package com.grp13.webapp.shared;

import java.io.Serializable;

public class opskriftTrinDTO implements Serializable{
	
	private int opskriftID;
	private double maengde;
	private int tid;
	private int trin;
	private String ingrediens;
	private String enhed;
	
	private opskriftTrinDTO() {
		//GWT Compliance. Not needed.
	}
	
	public opskriftTrinDTO(int opskriftID, double maengde, int tid, int trin, String ingrediens, String enhed, String handling, int vaegt) {
		this.opskriftID = opskriftID;
		this.maengde = maengde;
		this.tid = tid;
		this.trin = trin;	
	}
	
	public int getOpskriftID() {
		return opskriftID;
	}
	public void setOpskriftID(int opskriftID) {
		this.opskriftID = opskriftID;
	}
	public int getMaengde() {
		return maengde;
	}
	public void setMaengde(int maengde) {
		this.maengde = maengde;
	}
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public int getTrin() {
		return trin;
	}
	public void setTrin(int trin) {
		this.trin = trin;
	}
	public String getBeskrivelse() {
		return beskrivelse;
	}
	public void setBeskrivelse(String beskrivelse) {
		this.beskrivelse = beskrivelse;
	}
	
	

}
