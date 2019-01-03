package com.xhSmart.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xhSmart.mapper.JurisdictionMapper;
import com.xhSmart.model.Jurisdiction;
import com.xhSmart.service.JurisdictionService;



@Service
@Transactional  //此处不再进行创建SqlSession和提交事务，都已交由spring去管理了。
public class JurisdictionServiceImpl implements JurisdictionService {
	
	@Resource
	private JurisdictionMapper mapper;

	public boolean remove(int id) {
		
		return mapper.remove(id);
	}

	public List<Jurisdiction> findAll() {
		List<Jurisdiction> findAllList = mapper.findAll();
		return findAllList;
	}

	public Jurisdiction findById(int id) {

		Jurisdiction user = mapper.findById(id);
		
		return user;
	}

	public void save(Jurisdiction user) {

		mapper.save(user);
	}

	public boolean update(Jurisdiction user) {

		return mapper.update(user);
	}
	
	

}

