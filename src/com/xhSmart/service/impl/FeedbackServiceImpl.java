package com.xhSmart.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xhSmart.mapper.FeedbackMapper;
import com.xhSmart.model.Feedback;
import com.xhSmart.service.FeedbackService;



@Service
@Transactional  //此处不再进行创建SqlSession和提交事务，都已交由spring去管理了。
public class FeedbackServiceImpl implements FeedbackService {
	
	@Resource
	private FeedbackMapper mapper;

	public boolean remove(int id) {
		
		return mapper.remove(id);
	}

	public List<Feedback> findAll() {
		List<Feedback> findAllList = mapper.findAll();
		return findAllList;
	}

	public Feedback findById(int id) {

		Feedback object = mapper.findById(id);
		
		return object;
	}

	public void save(Feedback object) {

		mapper.save(object);
	}

	public boolean update(Feedback object) {

		return mapper.update(object);
	}

	public List<Feedback> findByProjectId(int id) {
		// TODO Auto-generated method stub
		
		return mapper.findByProjectId(id);
	}
	
	

}

