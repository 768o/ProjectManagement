package com.xhSmart.mapper;

import java.util.List;

import com.xhSmart.model.Allocation;
/**
 * 负责Allocation表的增删改查
 * @author lin
 *
 */
public interface AllocationMapper {
	void save(Allocation object);
	boolean update(Allocation object);
	//boolean remove(int id);
	Allocation findById(int project_id,int user_id,String Name);
	List<Allocation> findAll();
	List<Allocation> findAllByPro(int id);
	boolean remove(int uid, int pid,String name);
	List<Allocation> findAllByUserId(int user_id);
}
