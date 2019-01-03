package com.xhSmart.model;

import java.util.ArrayList;

public class JurisdictionGroup {
	
	private int jurisdictionGroup_id;                   //权限组id
	private String jurisdictionGroup_name;              //权限组名字
	private String jurisdictionGroup_depict;            //权限组描述
	private String jurisdiction;                        //权限组的权限id组
	private ArrayList<Jurisdiction> listJurisdictions;  //权限组的权限
	
	
	public String getJurisdiction() {
		return jurisdiction;
	}
	public void setJurisdiction(String jurisdiction) {
		this.jurisdiction = jurisdiction;
	}
	public int getJurisdictionGroup_id() {
		return jurisdictionGroup_id;
	}
	public void setJurisdictionGroup_id(int jurisdictionGroup_id) {
		this.jurisdictionGroup_id = jurisdictionGroup_id;
	}
	public String getJurisdictionGroup_name() {
		return jurisdictionGroup_name;
	}
	public void setJurisdictionGroup_name(String jurisdictionGroup_name) {
		this.jurisdictionGroup_name = jurisdictionGroup_name;
	}
	public String getJurisdictionGroup_depict() {
		return jurisdictionGroup_depict;
	}
	public void setJurisdictionGroup_depict(String jurisdictionGroup_depict) {
		this.jurisdictionGroup_depict = jurisdictionGroup_depict;
	}
	public ArrayList<Jurisdiction> getListJurisdictions() {
		return listJurisdictions;
	}
	public void setListJurisdictions(ArrayList<Jurisdiction> listJurisdictions) {
		this.listJurisdictions = listJurisdictions;
	}

}
