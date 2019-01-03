package com.xhSmart.model;

import java.sql.Timestamp;

public class Feedback {
		
	private int feedback_id;
	private String feedback_name;
	private Timestamp feedback_time;
	private String feedback_depict;
	private int user_id;
	private User user;
	private int project_id;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getFeedback_id() {
		return feedback_id;
	}
	public void setFeedback_id(int feedback_id) {
		this.feedback_id = feedback_id;
	}
	public String getFeedback_name() {
		return feedback_name;
	}
	public void setFeedback_name(String feedback_name) {
		this.feedback_name = feedback_name;
	}
	public Timestamp getFeedback_time() {
		return feedback_time;
	}
	public void setFeedback_time(Timestamp feedback_time) {
		this.feedback_time = feedback_time;
	}
	public String getFeedback_depict() {
		return feedback_depict;
	}
	public void setFeedback_depict(String feedback_depict) {
		this.feedback_depict = feedback_depict;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getProject_id() {
		return project_id;
	}
	public void setProject_id(int project_id) {
		this.project_id = project_id;
	}
	
}
