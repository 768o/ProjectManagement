package com.xhSmart.model;

public class UserTask {
	
	private int task_id;       //任务的id
	private String task_name;  //任务的名字
	private String task_depict;//任务的描述
	
	public int getTask_id() {
		return task_id;
	}
	public void setTask_id(int task_id) {
		this.task_id = task_id;
	}
	public String getTask_name() {
		return task_name;
	}
	public void setTask_name(String task_name) {
		this.task_name = task_name;
	}
	public String getTask_depict() {
		return task_depict;
	}
	public void setTask_depict(String task_depict) {
		this.task_depict = task_depict;
	}
	

}
