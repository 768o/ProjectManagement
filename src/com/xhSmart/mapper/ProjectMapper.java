package com.xhSmart.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xhSmart.model.Project;
/**
 * 负责Project表的增删改查
 * @author lin
 *
 */
public interface ProjectMapper {
	void save(Project object);
	boolean update(Project object);
	boolean remove(int id);
	Project findById(int id);
	Project findByName(String name);
	
	List<Project> findAllByNameToAdmin(@Param(value="project_name")String project_name, @Param(value="startPos")Integer startPos,@Param(value="pageSize")Integer pageSize);
	int getProjectsCountByNameToAdmin(@Param(value="project_name")String project_name);
	List<Project> findAllByNameToUser(@Param(value="user_id")Integer user_id,@Param(value="project_name")String project_name, @Param(value="startPos")Integer startPos,@Param(value="pageSize")Integer pageSize);
	int getProjectsCountByNameToUser(@Param(value="user_id")Integer user_id, @Param(value="project_name")String project_name);
	
	List<Project> findAll();
	List<Project> findAllByUser(int user_id);
	int getProjectsCountByUser(int user_id);
	List<Project> findAllByUser(@Param(value="user_id")Integer user_id,@Param(value="startPos")Integer startPos,@Param(value="pageSize")Integer pageSize);
	List<Project> findAllByAdmin(@Param(value="startPos")Integer startPos, @Param(value="pageSize")Integer pageSize);
	int getProjectsCountByAdmin();
}
