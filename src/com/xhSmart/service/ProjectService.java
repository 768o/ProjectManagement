package com.xhSmart.service;

import java.util.List;

import com.xhSmart.model.Project;
/**
 * 负责Project表的增删改查
 * @author lin
 *
 */
public interface ProjectService {
	void save(Project object);
	boolean update(Project object);
	boolean remove(int id);
	Project findById(int id);
	Project findByName(String name);
	//List<Project> findAllByName(String name);
	List<Project> findAll();
	List<Project> findAllByUser(int user_id);
	int getProjectsCountByUser(int user_id);
	List<Project> findAllByUser(int user_id, int startPos, int pageSize);
	List<Project> findAllByAdmin(int startPos, int pageSize);
	int getProjectsCountByAdmin();
	
	List<Project> findAllByNameToAdmin(String name, int startPos, int pageSize);
	List<Project> findAllByNameToUser(int user_id,String name, int startPos, int pageSize);
	int getProjectsCountByNameToAdmin(String name);
	int getProjectsCountByNameToUser(int user_id, String name);
}
