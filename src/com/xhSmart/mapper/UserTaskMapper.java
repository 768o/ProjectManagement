package com.xhSmart.mapper;

import java.util.List;
import com.xhSmart.model.UserTask;
/**
 * 负责task表的增删改查
 * @author lin
 *
 */
public interface UserTaskMapper {
	void save(UserTask object);
	boolean update(UserTask object);
	boolean remove(int id);
	UserTask findById(int id);
	List<UserTask> findAll();
}
