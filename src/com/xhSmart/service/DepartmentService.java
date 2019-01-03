package com.xhSmart.service;

import java.util.List;

import com.xhSmart.model.Department;
/**
 * 负责Department表的增删改查
 * @author lin
 *
 */
public interface DepartmentService {
	void save(Department dept);
	boolean update(Department dept);
	boolean remove(int id);
	Department findById(int id);
	List<Department> findAll();
}