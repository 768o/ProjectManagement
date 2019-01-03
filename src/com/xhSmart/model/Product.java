package com.xhSmart.model;

import java.sql.Timestamp;

public class Product {
	
		private int product_id;        //产品id
		private String product_name;   //产品名字
		private String product_type;    //产品类型
		private String product_depict;  //产品描述
		private String product_state;   //产品状态
		private String product_path;    //产品路径
		private int project_id;        //产品所在项目id
		private Project project;       //产品所在的项目
		private Timestamp product_submitTime;//产品提交的时间
		private int submit_user;          //产品提交的用户id
		private User user;                 //产品提交的用户
		private int demand_id;              //该产品的需求id
		private Demand demand;             //该产品的需求
		public int getProduct_id() {
			return product_id;
		}
		public void setProduct_id(int product_id) {
			this.product_id = product_id;
		}
		public String getProduct_name() {
			return product_name;
		}
		public void setProduct_name(String product_name) {
			this.product_name = product_name;
		}
		public String getProduct_type() {
			return product_type;
		}
		public void setProduct_type(String product_type) {
			this.product_type = product_type;
		}
		public String getProduct_depict() {
			return product_depict;
		}
		public void setProduct_depict(String product_depict) {
			this.product_depict = product_depict;
		}
		public String getProduct_state() {
			return product_state;
		}
		public void setProduct_state(String product_state) {
			this.product_state = product_state;
		}
		public String getProduct_path() {
			return product_path;
		}
		public void setProduct_path(String product_path) {
			this.product_path = product_path;
		}
		public int getProject_id() {
			return project_id;
		}
		public void setProject_id(int project_id) {
			this.project_id = project_id;
		}
		public Project getProject() {
			return project;
		}
		public void setProject(Project project) {
			this.project = project;
		}
		public Timestamp getProduct_submitTime() {
			return product_submitTime;
		}
		public void setProduct_submitTime(Timestamp product_submitTime) {
			this.product_submitTime = product_submitTime;
		}
		public int getSubmit_user() {
			return submit_user;
		}
		public void setSubmit_user(int submit_user) {
			this.submit_user = submit_user;
		}
		public User getUser() {
			return user;
		}
		public void setUser(User user) {
			this.user = user;
		}
		public int getDemand_id() {
			return demand_id;
		}
		public void setDemand_id(int demand_id) {
			this.demand_id = demand_id;
		}
		public Demand getDemand() {
			return demand;
		}
		public void setDemand(Demand demand) {
			this.demand = demand;
		}


}
                                                                                                                                                                            