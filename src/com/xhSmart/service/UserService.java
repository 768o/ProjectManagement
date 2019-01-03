package com.xhSmart.service;

import java.util.List;

import com.xhSmart.model.User;

/** 
 *  负责User表的增删改查
 *  @author lin
 * 
 * */
public interface UserService {
	void save(User user);
	boolean update(User user);
	boolean remove(int id);
	User findByLoginName(String loginName);
	List<User> findByDepartment(int id);
	User findById(int id);
	List<User> findAll();
}
