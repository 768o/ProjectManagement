package com.xhSmart.model;

import java.sql.Timestamp;
import java.util.ArrayList;

public class Demand {
	
	private int demand_id;        //需求id
	private String demand_name;   //需求名字
	private String demand_type;   //需求类型
	private String demand_depict; //需求描述
	private String demand_state;  //需求状态
	private String demand_path;   //需求路径
	private int demand_fatherid;  //需求父亲编号
	private int project_id;        //需求所在项目id
	private Project project;      //需求所在的项目
	private Timestamp demand_submitTime;   //需求提交的时间
	private int submit_user;          //需求提交的用户id
	private User user;            //需求提交的用户
	private ArrayList<Product> listProducts; //该需求的产品
	
	public int getDemand_id() {
		return demand_id;
	}
	public void setDemand_id(int demand_id) {
		this.demand_id = demand_id;
	}
	public String getDemand_type() {
		return demand_type;
	}
	public void setDemand_type(String demand_type) {
		this.demand_type = demand_type;
	}
	public String getDemand_depict() {
		return demand_depict;
	}
	public void setDemand_depict(String demand_depict) {
		this.demand_depict = demand_depict;
	}
	public String getDemand_state() {
		return demand_state;
	}
	public void setDemand_state(String demand_state) {
		this.demand_state = demand_state;
	}
	public String getDemand_path() {
		return demand_path;
	}
	public void setDemand_path(String demand_path) {
		this.demand_path = demand_path;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public ArrayList<Product> getListProducts() {
		return listProducts;
	}
	public void setListProducts(ArrayList<Product> listProducts) {
		this.listProducts = listProducts;
	}
	public String getDemand_name() {
		return demand_name;
	}
	public void setDemand_name(String demand_name) {
		this.demand_name = demand_name;
	}
	public int getDemand_fatherid() {
		return demand_fatherid;
	}
	public void setDemand_fatherid(int demand_fatherid) {
		this.demand_fatherid = demand_fatherid;
	}
	public int getProject_id() {
		return project_id;
	}
	public void setProject_id(int project_id) {
		this.project_id = project_id;
	}
	public Timestamp getDemand_submitTime() {
		return demand_submitTime;
	}
	public void setDemand_submitTime(Timestamp demand_submitTime) {
		this.demand_submitTime = demand_submitTime;
	}
	public int getSubmit_user() {
		return submit_user;
	}
	public void setSubmit_user(int submit_user) {
		this.submit_user = submit_user;
	}


}
