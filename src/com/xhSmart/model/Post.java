package com.xhSmart.model;

import java.util.ArrayList;

public class Post {
	
	private int post_id;                //职务id
	private String post_name;           //职务名称
	private String post_depict;         //职务描述
	private ArrayList<User> listUsers;  //拥有该职务的用户
	
	public int getPost_id() {
		return post_id;
	}
	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}
	public String getPost_name() {
		return post_name;
	}
	public void setPost_name(String post_name) {
		this.post_name = post_name;
	}
	public String getPost_depict() {
		return post_depict;
	}
	public void setPost_depict(String post_depict) {
		this.post_depict = post_depict;
	}
	public ArrayList<User> getListUsers() {
		return listUsers;
	}
	public void setListUsers(ArrayList<User> listUsers) {
		this.listUsers = listUsers;
	}
	
}
