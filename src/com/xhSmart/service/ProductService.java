package com.xhSmart.service;

import java.util.List;

import com.xhSmart.model.Product;
import com.xhSmart.model.User;
/**
 * 负责Product表的增删改查
 * @author lin
 *
 */
public interface ProductService {
	void save(Product object, User user);
	boolean update(Product object);
	boolean remove(int id);
	Product findById(int id);
	List<Product> findAll();
	List<Product> findByProjectId(int id);
	List<Product> findAllByUser(int user_id);
	
	
	int getProductsCountByUser(int user_id, String name);
	List<Product> findAllByUser(int user_id, String name, int startPos, int pageSize);
	int getProductsCountByAdmin(String name);
	List<Product> findAllByAdmin(String name, int startPos, int pageSize);
}
