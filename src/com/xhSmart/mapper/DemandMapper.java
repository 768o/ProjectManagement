package com.xhSmart.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xhSmart.model.Demand;
/**
 * 负责Demand表的增删改查
 * @author lin
 *
 */
public interface DemandMapper {
	void save(Demand object);
	boolean update(Demand object);
	boolean remove(int id);
	Demand findById(int id);
	List<Demand> findByFatherId(int fatherid);
	List<Demand> findAll();
	List<Demand> findByProjectId(int project_id);
	List<Demand> findAllByUser(@Param(value="user_id")Integer user_id,@Param(value="demand_name")String demand_name, @Param(value="startPos")Integer startPos,@Param(value="pageSize")Integer pageSize);
	int getDemandsCountByUser(@Param(value="user_id")Integer user_id,@Param(value="demand_name")String demand_name);
	int getDemandsCountByAdmin(@Param(value="demand_name")String demand_name);
	List<Demand> findAllByAdmin(@Param(value="demand_name")String demand_name, @Param(value="startPos")Integer startPos, @Param(value="pageSize")Integer pageSize);
}
