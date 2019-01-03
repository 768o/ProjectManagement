package com.xhSmart.service;

import java.util.List;

import com.xhSmart.model.Post;
/**
 * 负责Post表的增删改查
 * @author lin
 *
 */
public interface PostService {
	void save(Post object);
	boolean update(Post object);
	boolean remove(int id);
	Post findById(int id);
	List<Post> findAll();
}

