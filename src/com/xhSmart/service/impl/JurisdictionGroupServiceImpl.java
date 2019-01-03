package com.xhSmart.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xhSmart.mapper.JurisdictionGroupMapper;
import com.xhSmart.model.JurisdictionGroup;
import com.xhSmart.service.JurisdictionGroupService;



@Service
@Transactional  //此处不再进行创建SqlSession和提交事务，都已交由spring去管理了。
public class JurisdictionGroupServiceImpl implements JurisdictionGroupService {
	
	@Resource
	private JurisdictionGroupMapper mapper;

	public boolean remove(int id) {
		
		return mapper.remove(id);
	}

	public List<JurisdictionGroup> findAll() {
		List<JurisdictionGroup> findAllList = mapper.findAll();
		return findAllList;
	}

	public JurisdictionGroup findById(int id) {

		JurisdictionGroup object = mapper.findById(id);
		
		return object;
	}

	public void save(JurisdictionGroup object) {

		mapper.save(object);
	}

	public boolean update(JurisdictionGroup object) {

		return mapper.update(object);
	}
	
	

}

