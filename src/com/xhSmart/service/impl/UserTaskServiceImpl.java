package com.xhSmart.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xhSmart.mapper.UserTaskMapper;
import com.xhSmart.model.UserTask;
import com.xhSmart.service.UserTaskService;



@Service
@Transactional  //此处不再进行创建SqlSession和提交事务，都已交由spring去管理了。
public class UserTaskServiceImpl implements UserTaskService {
	
	@Resource
	private UserTaskMapper mapper;

	public boolean remove(int id) {
		
		return mapper.remove(id);
	}

	public List<UserTask> findAll() {
		List<UserTask> findAllList = mapper.findAll();
		return findAllList;
	}

	public UserTask findById(int id) {

		UserTask object = mapper.findById(id);
		
		return object;
	}

	public void save(UserTask object) {

		mapper.save(object);
	}

	public boolean update(UserTask object) {

		return mapper.update(object);
	}
	
	

}

