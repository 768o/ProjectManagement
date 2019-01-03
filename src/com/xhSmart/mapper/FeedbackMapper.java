package com.xhSmart.mapper;

import java.util.List;

import com.xhSmart.model.Feedback;
/**
 * 负责Feedback表的增删改查
 * @author lin
 *
 */
public interface FeedbackMapper {
	void save(Feedback object);
	boolean update(Feedback object);
	boolean remove(int id);
	Feedback findById(int id);
	List<Feedback> findAll();
	List<Feedback> findByProjectId(int id);
}
