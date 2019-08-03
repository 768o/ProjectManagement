package com.xhSmart.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xhSmart.mapper.AllocationMapper;
import com.xhSmart.mapper.ProjectMapper;
import com.xhSmart.mapper.UserMapper;
import com.xhSmart.mapper.UserTaskMapper;
import com.xhSmart.model.Allocation;
import com.xhSmart.service.AllocationService;

/**
 * 
 * @author lin
 *
 */
@Service
@Transactional  //此处不再进行创建SqlSession和提交事务，都已交由spring去管理了。
public class AllocationServiceImpl implements AllocationService {
	
	@Resource
	private AllocationMapper mapper;
	@Resource
	private UserMapper userMapper;
	@Resource
	private ProjectMapper projectMapper;
	@Resource
	private UserTaskMapper userTaskMapper;

	public boolean remove(int Uid,int Pid,String name) {
		
		return mapper.remove(Uid,Pid,name);
	}

	public Allocation findById(int project_id, int user_id,String name) {

		Allocation object = mapper.findById(project_id,user_id,name);
		System.out.println("Allocation"+object+","+project_id+","+user_id+","+name);
		System.out.println("Allocation"+object.getName());
		object.setUser(userMapper.findById(user_id));
		return object;
	}

	public void save(Allocation object) {

		mapper.save(object);
	}

	public boolean update(Allocation object) {

		return mapper.update(object);
	}

	public List<Allocation> findAllByPro(int id) {
		// TODO Auto-generated method stub
		List<Allocation> findAllList1 = mapper.findAllByPro(id);
		List<Allocation> findAllList2 = new ArrayList<Allocation>();
		for (Allocation allocation : findAllList1) {
			allocation.setUser(userMapper.findById(allocation.getUser_id()));
			allocation.setProject(projectMapper.findById(allocation.getProject_id()));
			allocation.setTask(userTaskMapper.findById(allocation.getUser_task()));
			findAllList2.add(allocation);
		}
		return findAllList2;
	}
	
	public List<Allocation> findByUserId(int user_id){
		List<Allocation> findAllList1 = mapper.findAllByUserId(user_id);
		List<Allocation> findAllList2 = new ArrayList<Allocation>();
		for (Allocation allocation : findAllList1) {
			allocation.setUser(userMapper.findById(allocation.getUser_id()));
			allocation.setProject(projectMapper.findById(allocation.getProject_id()));
			allocation.setTask(userTaskMapper.findById(allocation.getUser_task()));
			findAllList2.add(allocation);
		}
		return findAllList2;
		
	}
	

}

