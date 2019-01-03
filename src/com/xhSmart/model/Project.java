package com.xhSmart.model;

import java.sql.Timestamp;
import java.util.ArrayList;

public class Project {
	
	private int project_id;                     //项目id
	private String project_name;                //项目名字
	private String project_depict;              //项目描述  
	private Timestamp project_createTime;       //项目创建时间
	private String project_state;               //项目状态 
	private String project_createReason;        //项目创建原因
	private Timestamp project_endTime;             //项目结束时间
	private int demand_id;                      //需求id
	private int user_id;
	private User user;
	private ArrayList<Product> listProducts;    //项目的产品
	private ArrayList<Demand> listDemands;      //项目的需求
	private ArrayList<Allocation> listAllocations;//项目的分配情况
	
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getProject_id() {
		return project_id;
	}
	public void setProject_id(int project_id) {
		this.project_id = project_id;
	}
	public String getProject_name() {
		return project_name;
	}
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	public String getProject_depict() {
		return project_depict;
	}
	public void setProject_depict(String project_depict) {
		this.project_depict = project_depict;
	}
	public Timestamp getProject_createTime() {
		return project_createTime;
	}
	public void setProject_createTime(Timestamp project_createTime) {
		this.project_createTime = project_createTime;
	}
	public String getProject_state() {
		return project_state;
	}
	public void setProject_state(String project_state) {
		this.project_state = project_state;
	}
	public String getProject_createReason() {
		return project_createReason;
	}
	public void setProject_createReasonString(String project_createReason) {
		this.project_createReason = project_createReason;
	}
	public Timestamp getProject_endTime() {
		return project_endTime;
	}
	public void setProject_endTime(Timestamp endTime) {
		this.project_endTime = endTime;
	}
	public ArrayList<Product> getListProducts() {
		return listProducts;
	}
	public void setListProducts(ArrayList<Product> listProducts) {
		this.listProducts = listProducts;
	}
	public ArrayList<Demand> getListDemands() {
		return listDemands;
	}
	public void setListDemands(ArrayList<Demand> listDemands) {
		this.listDemands = listDemands;
	}
	public ArrayList<Allocation> getListAllocations() {
		return listAllocations;
	}
	public void setListAllocations(ArrayList<Allocation> listAllocations) {
		this.listAllocations = listAllocations;
	}
	public void setProject_createReason(String project_createReason) {
		this.project_createReason = project_createReason;
	}
	public int getDemand_id() {
		return demand_id;
	}
	public void setDemand_id(int demand_id) {
		this.demand_id = demand_id;
	}
	

}
