package com.xhSmart.mapper;

import java.util.List;
import com.xhSmart.model.Department;
/**
 * 负责Department表的增删改查
 * @author lin
 *
 */
public interface DepartmentMapper {
	void save(Department dept);
	boolean update(Department dept);
	boolean remove(int id);
	Department findById(int id);
	List<Department> findAll();
}
