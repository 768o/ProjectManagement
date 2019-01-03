package com.xhSmart.mapper;

import java.util.List;

import com.xhSmart.model.User;
/**
 * 负责user表的增删改查
 * @author lin
 *
 */
public interface UserMapper {

	void save(User user);
	boolean update(User user);
	boolean remove(int id);
	User findById(int id);
	List<User> findByDepartment(int id);
	User findByLoginName(String loginName);
	List<User> findAll();
}
