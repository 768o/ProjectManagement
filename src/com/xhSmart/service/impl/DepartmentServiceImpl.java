package com.xhSmart.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xhSmart.mapper.DepartmentMapper;
import com.xhSmart.model.Department;
import com.xhSmart.service.DepartmentService;



@Service
@Transactional  //此处不再进行创建SqlSession和提交事务，都已交由spring去管理了。
public class DepartmentServiceImpl implements DepartmentService {
	
	@Resource
	private DepartmentMapper mapper;

	public boolean remove(int id) {
		
		return mapper.remove(id);
	}

	public List<Department> findAll() {
		List<Department> findAllList = mapper.findAll();
		return findAllList;
	}

	public Department findById(int id) {

		Department user = mapper.findById(id);
		
		return user;
	}

	public void save(Department user) {

		mapper.save(user);
	}

	public boolean update(Department user) {

		return mapper.update(user);
	}
	
	

}
