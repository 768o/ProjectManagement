package com.xhSmart.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xhSmart.mapper.DemandMapper;
import com.xhSmart.mapper.FeedbackMapper;
import com.xhSmart.mapper.ProductMapper;
import com.xhSmart.mapper.ProjectMapper;
import com.xhSmart.mapper.UserMapper;
import com.xhSmart.model.Feedback;
import com.xhSmart.model.Product;
import com.xhSmart.model.User;
import com.xhSmart.service.ProductService;
@Service
@Transactional
public class ProductServiceImpl implements ProductService{
	@Resource
	private ProductMapper mapper;
	@Resource
	private UserMapper userMapper;
	@Resource
	private DemandMapper demandMapper;
	@Resource
	private ProjectMapper projectMapper;
	@Resource
	private FeedbackMapper feedbackMapper;


	public boolean remove(int id) {
		
		return mapper.remove(id);
	}

	public List<Product> findAll() {
		List<Product> findAllList = mapper.findAll();
		return findAllList;
	}

	public Product findById(int id) {

		Product object = mapper.findById(id);
		
		return object;
	}

	public void save(Product object, User user) {

		Feedback feedback = new Feedback();
		feedback.setUser_id(user.getUser_id());
		feedback.setProject_id(object.getProject_id());
		feedback.setFeedback_name("产品");
		feedback.setFeedback_depict(user.getUser_name()+" 添加了产品 "+object.getProduct_name());
		feedbackMapper.save(feedback);
		object.setProduct_state("开发中");
		mapper.save(object);
	}

	public boolean update(Product object) {
		
		return mapper.update(object);
	}

	public List<Product> findByProjectId(int id) {
		// TODO Auto-generated method stub
		return mapper.findByProjectId(id);
	}

	public List<Product> findAllByUser(int user_id) {
		// TODO Auto-generated method stub
		return mapper.findAllByUser(user_id);
	}

	public int getProductsCountByUser(int user_id, String name) {
		// TODO Auto-generated method stub
		return mapper.getProductsCountByUser(user_id, name);
	}

	public List<Product> findAllByUser(int user_id, String name, int startPos, int pageSize) {
		// TODO Auto-generated method stub
		return mapper.findAllByUser(user_id,name,startPos,pageSize);
	}

	public int getProductsCountByAdmin(String name) {
		// TODO Auto-generated method stub
		return mapper.getProductsCountByAdmin(name);
	}

	public List<Product> findAllByAdmin(String name, int startPos, int pageSize) {
		// TODO Auto-generated method stub
		List<Product> findAllold = mapper.findAllByAdmin(name,startPos,pageSize);
		List<Product> findAll = new ArrayList<Product>();
		for (Product product : findAllold) {
			product.setDemand(demandMapper.findById(product.getDemand_id()));
			product.setProject(projectMapper.findById(product.getProject_id()));
			product.setUser(userMapper.findById(product.getSubmit_user()));
			findAll.add(product);
			product = null;
		}
		return findAll;
	}
}
