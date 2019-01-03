package com.xhSmart.model;

import java.util.ArrayList;

public class Department {
	
	private int department_id;           //部门id
	private String department_name;      //部门名称
	private String department_depict;    //部门描述
	private ArrayList<User> listUsers;   //该部门下的用户
	
	public int getDepartment_id() {
		return department_id;
	}
	public void setDepartment_id(int department_id) {
		this.department_id = department_id;
	}
	public String getDepartment_name() {
		return department_name;
	}
	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}
	public String getDepartment_depict() {
		return department_depict;
	}
	public void setDepartment_depict(String department_depict) {
		this.department_depict = department_depict;
	}
	public ArrayList<User> getListUsers() {
		return listUsers;
	}
	public void setListUsers(ArrayList<User> listUsers) {
		this.listUsers = listUsers;
	}

}
