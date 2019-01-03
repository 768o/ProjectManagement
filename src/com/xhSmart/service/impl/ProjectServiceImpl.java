package com.xhSmart.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xhSmart.mapper.ProjectMapper;
import com.xhSmart.model.Project;
import com.xhSmart.service.ProjectService;



@Service
@Transactional  //此处不再进行创建SqlSession和提交事务，都已交由spring去管理了。
public class ProjectServiceImpl implements ProjectService {
	
	@Resource
	private ProjectMapper mapper;

	public boolean remove(int id) {
		
		return mapper.remove(id);
	}

	public List<Project> findAll() {
		List<Project> findAllList = mapper.findAll();
		return findAllList;
	}

	public Project findById(int id) {

		Project object = mapper.findById(id);
		
		return object;
	}
	public Project findByName(String name) {

		Project object = mapper.findByName(name);
		
		return object;
	}

	public void save(Project object) {

		mapper.save(object);
	}

	public boolean update(Project object) {

		return mapper.update(object);
	}

	public List<Project> findAllByUser(int user_id) {
		// TODO Auto-generated method stub
		return mapper.findAllByUser(user_id);
	}

	public int getProjectsCountByUser(int user_id) {
		// TODO Auto-generated method stub
		return mapper.getProjectsCountByUser(user_id);
	}

	public List<Project> findAllByUser(int user_id, int startPos, int pageSize) {
		// TODO Auto-generated method stub
		return mapper.findAllByUser(user_id,startPos,pageSize);
	}

	public List<Project> findAllByAdmin(int startPos, int pageSize) {
		// TODO Auto-generated method stub
		return mapper.findAllByAdmin(startPos,pageSize);
	}

	public int getProjectsCountByAdmin() {
		// TODO Auto-generated method stub
		return mapper.getProjectsCountByAdmin();
	}


	public List<Project> findAllByNameToAdmin(String name, int startPos,
			int pageSize) {
		// TODO Auto-generated method stub
		return mapper.findAllByNameToAdmin(name, startPos, pageSize);
	}

	public List<Project> findAllByNameToUser(int user_id, String name,
			int startPos, int pageSize) {
		// TODO Auto-generated method stub
		return mapper.findAllByNameToUser(user_id, name, startPos, pageSize);
	}

	public int getProjectsCountByNameToAdmin(String name) {
		// TODO Auto-generated method stub
		return mapper.getProjectsCountByNameToAdmin(name);
	}

	public int getProjectsCountByNameToUser(int user_id, String name) {
		// TODO Auto-generated method stub
		return mapper.getProjectsCountByNameToUser(user_id, name);
	}

	
	
	

}

