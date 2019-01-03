package com.xhSmart.model;

import java.util.ArrayList;

public class User {

	private int user_id;                         //用户id
	private String user_loginName;               //登陆账号
	private String user_password;                //用户密码
	private String user_name;                    //用户名
	private int post_id;                         //用户职务id
	private Post post;                           //用户职务
	private int department_id;                   //用户部门id
	private Department department;               //用户部门
	private ArrayList<Demand> listDemands;       //用户发布的需求
	private ArrayList<Product> listProducts;     //用户发布的产品
	private ArrayList<WaitRead> listWaitReads;   //用户未读信息
	private ArrayList<Allocation> listllocations;//用户的工作与权限分配
	private int user_isdelete;  //用户是否不存在
	private int isadmin;
	
	
	public int getIsadmin() {
		return isadmin;
	}
	public void setIsadmin(int isadmin) {
		this.isadmin = isadmin;
	}
	public String getUser_loginName() {
		return user_loginName;
	}
	public void setUser_loginName(String user_loginName) {
		this.user_loginName = user_loginName;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public Post getPost() {
		return post;
	}
	public void setPost(Post post) {
		this.post = post;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public ArrayList<Demand> getListDemands() {
		return listDemands;
	}
	public void setListDemands(ArrayList<Demand> listDemands) {
		this.listDemands = listDemands;
	}
	public ArrayList<Product> getListProducts() {
		return listProducts;
	}
	public void setListProducts(ArrayList<Product> listProducts) {
		this.listProducts = listProducts;
	}
	public ArrayList<WaitRead> getListWaitReads() {
		return listWaitReads;
	}
	public void setListWaitReads(ArrayList<WaitRead> listWaitReads) {
		this.listWaitReads = listWaitReads;
	}
	public ArrayList<Allocation> getListllocations() {
		return listllocations;
	}
	public void setListllocations(ArrayList<Allocation> listllocations) {
		this.listllocations = listllocations;
	}
	public int getPost_id() {
		return post_id;
	}
	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}
	public int getDepartment_id() {
		return department_id;
	}
	public void setDepartment_id(int department_id) {
		this.department_id = department_id;
	}
	public int getUser_isdelete() {
		return user_isdelete;
	}
	public void setUser_isdelete(int user_isdelete) {
		this.user_isdelete = user_isdelete;
	}
	

}
