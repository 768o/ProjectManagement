package com.xhSmart.mapper;

import java.util.List;

import com.xhSmart.model.Post;
/**
 * 负责post表的增删改查
 * @author lin
 *
 */
public interface PostMapper {
	void save(Post object);
	boolean update(Post object);
	boolean remove(int id);
	Post findById(int id);
	List<Post> findAll();
}
