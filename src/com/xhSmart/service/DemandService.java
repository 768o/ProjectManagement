package com.xhSmart.service;

import java.util.List;

import com.xhSmart.model.Demand;
import com.xhSmart.model.User;
/**
 * 负责Demand表的增删改查
 * @author lin
 *
 */
public interface DemandService {
	void save(Demand object,User user);
	boolean update(Demand object);
	boolean remove(int id);
	Demand findById(int id);
	List<Demand> findByFatherId(int fatherid);
	List<Demand> findAll();
	List<Demand> findByProjectId(int project_id);
	List<Demand> findAllByUser(int user_id, String denamd_name, int startPos,int pageSize);
	int getDemandsCountByUser(int user_id, String demand_name);
	int getDemandsCountByAdmin(String demand_name);
	List<Demand> findAllByAdmin(String demand_name, int startPos, int pageSize);
}
