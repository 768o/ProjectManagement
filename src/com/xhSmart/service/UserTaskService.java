package com.xhSmart.service;

import java.util.List;
import com.xhSmart.model.UserTask;
/**
 * 负责Task表的增删改查
 * @author lin
 *
 */
public interface UserTaskService {
	void save(UserTask object);
	boolean update(UserTask object);
	boolean remove(int id);
	UserTask findById(int id);
	List<UserTask> findAll();
}
