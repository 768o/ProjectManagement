package com.xhSmart.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xhSmart.mapper.DepartmentMapper;
import com.xhSmart.mapper.PostMapper;
import com.xhSmart.mapper.UserMapper;
import com.xhSmart.model.User;
import com.xhSmart.service.UserService;



@Service
@Transactional  //此处不再进行创建SqlSession和提交事务，都已交由spring去管理了。
public class UserServiceImpl implements UserService {
	
	@Resource
	private UserMapper mapper;
	@Resource
	private DepartmentMapper department;
	@Resource
	private PostMapper post;

	public boolean remove(int id) {
		
		return mapper.remove(id);
	}

	public List<User> findAll() {
		List<User> findAllList = mapper.findAll();
		List<User> findAllListReturn = new ArrayList<User>();
		if(!findAllList.isEmpty()){
			for(int i=0;i<findAllList.size();i++){
				User user = findAllList.get(i);
				user.setDepartment(department.findById(findAllList.get(i).getDepartment_id()));
				user.setPost(post.findById(findAllList.get(i).getPost_id()));
				findAllListReturn.add(user);
			}
			
		}
		return findAllListReturn;
	}

	public User findById(int id) {

		User user = mapper.findById(id);
		user.setDepartment(department.findById(user.getDepartment_id()));
		user.setPost(post.findById(user.getPost_id()));
		return user;
	}
	public User findByLoginName(String loginName) {

		User user = mapper.findByLoginName(loginName);
		user.setDepartment(department.findById(user.getDepartment_id()));
		user.setPost(post.findById(user.getPost_id()));
		return user;
	}

	public void save(User user) {

		mapper.save(user);
	}

	public boolean update(User user) {

		return mapper.update(user);
	}

	public List<User> findByDepartment(int id) {
		// TODO Auto-generated method stub
		
		return mapper.findByDepartment(id);
	}
	
	

}
