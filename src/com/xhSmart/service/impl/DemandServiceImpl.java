package com.xhSmart.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xhSmart.mapper.DemandMapper;
import com.xhSmart.mapper.FeedbackMapper;
import com.xhSmart.mapper.ProjectMapper;
import com.xhSmart.mapper.UserMapper;
import com.xhSmart.model.Demand;
import com.xhSmart.model.Feedback;
import com.xhSmart.model.User;
import com.xhSmart.service.DemandService;

@Service
@Transactional  //此处不再进行创建SqlSession和提交事务，都已交由spring去管理了。
public class DemandServiceImpl implements DemandService {
	
	@Resource
	private DemandMapper mapper;
	@Resource
	private UserMapper userMapper;
	@Resource
	private ProjectMapper projectMapper;
	@Resource
	private FeedbackMapper feedbackMapper;

	public boolean remove(int id) {
		// TODO Auto-generated method stub
		return mapper.remove(id);
	}

	public List<Demand> findAll() {
		// TODO Auto-generated method stub
		return mapper.findAll();
	}

	public Demand findById(int id) {
		// TODO Auto-generated method stub
		return mapper.findById(id);
	}
	public List<Demand> findByFatherId(int id) {
		// TODO Auto-generated method stub
		return mapper.findByFatherId(id);
	}

	public void save(Demand object,User user) {
		// TODO Auto-generated method stub
		
		if((object.getProject_id()!=0)){
			object.setDemand_type("变更");
			object.setProject_id(object.getProject_id());
			object.setDemand_state(" ");
			object.setSubmit_user(user.getUser_id());
			Feedback feedback = new Feedback();
			feedback.setUser_id(user.getUser_id());
			feedback.setProject_id(object.getProject_id());
			feedback.setFeedback_name(object.getDemand_name());
			feedback.setFeedback_depict(user.getUser_name()+" 添加了需求 "+object.getDemand_name());	
			feedbackMapper.save(feedback);
		}else{
		object.setDemand_type("初始");
		object.setDemand_state("等待立项");
		object.setProject_id(-1);//-1可以理解为未立项的需求
		//object.setDemand_fatherid((int)System.currentTimeMillis());//有风险，临时使用
		object.setSubmit_user(user.getUser_id());

		}
		mapper.save(object);
	}

	public boolean update(Demand object) {
		// TODO Auto-generated method stub
		return mapper.update(object);
	}

	public List<Demand> findByProjectId(int project_id) {
		// TODO Auto-generated method stub
		return mapper.findByProjectId(project_id);
	}

	public List<Demand> findAllByUser(int user_id,String demand_name,int startPos,int pageSize) {
		// TODO Auto-generated method stub
		List<Demand> findAll = mapper.findAllByUser(user_id,demand_name,startPos,pageSize);
		List<Demand> findAllList = new ArrayList<Demand>();
		for (Demand demand : findAll) {
			demand.setProject(projectMapper.findById(demand.getProject_id()));
			demand.setUser(userMapper.findById(demand.getSubmit_user()));
			findAllList.add(demand);
			demand = null;
		}
		return findAllList;
	}

	public int getDemandsCountByUser(int user_id,String demand_name) {
		// TODO Auto-generated method stub
		return mapper.getDemandsCountByUser(user_id,demand_name);
	}

	public int getDemandsCountByAdmin(String demand_name) {
		// TODO Auto-generated method stub
		return mapper.getDemandsCountByAdmin(demand_name);
	}

	public List<Demand> findAllByAdmin(String demand_name,int startPos, int pageSize) {
		// TODO Auto-generated method stub
		List<Demand> findAll = mapper.findAllByAdmin(demand_name,startPos, pageSize);
		List<Demand> findAllList = new ArrayList<Demand>();
		for (Demand demand : findAll) {
			demand.setProject(projectMapper.findById(demand.getProject_id()));
			demand.setUser(userMapper.findById(demand.getSubmit_user()));
			findAllList.add(demand);
			demand = null;
		}
		return findAllList;
	}
	
	

}

