package com.xhSmart.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xhSmart.model.Product;
/**
 * 负责Product表的增删改查
 * @author lin
 *
 */
public interface ProductMapper {
	void save(Product object);
	boolean update(Product object);
	boolean remove(int id);
	Product findById(int id);
	List<Product> findAll();
	List<Product> findByProjectId(int id);
	List<Product> findAllByUser(int user_id);
	List<Product> findAllByUser(@Param(value="user_id")Integer user_id,@Param(value="product_name")String name,@Param(value="startPos")Integer startPos,@Param(value="pageSize")Integer pageSize);
	int getProductsCountByUser(@Param(value="user_id")Integer user_id,@Param(value="product_name")String name);
	int getProductsCountByAdmin(String name);
	List<Product> findAllByAdmin(@Param(value="product_name")String name,@Param(value="startPos")Integer startPos, @Param(value="pageSize")Integer pageSize);
}
