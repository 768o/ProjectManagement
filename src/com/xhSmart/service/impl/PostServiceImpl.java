package com.xhSmart.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xhSmart.mapper.PostMapper;
import com.xhSmart.model.Post;
import com.xhSmart.service.PostService;



@Service
@Transactional  //此处不再进行创建SqlSession和提交事务，都已交由spring去管理了。
public class PostServiceImpl implements PostService {
	
	@Resource
	private PostMapper mapper;

	public boolean remove(int id) {
		
		return mapper.remove(id);
	}

	public List<Post> findAll() {
		List<Post> findAllList = mapper.findAll();
		return findAllList;
	}

	public Post findById(int id) {

		Post object = mapper.findById(id);
		
		return object;
	}

	public void save(Post object) {

		mapper.save(object);
	}

	public boolean update(Post object) {

		return mapper.update(object);
	}
	
	

}

