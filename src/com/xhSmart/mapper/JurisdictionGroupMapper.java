package com.xhSmart.mapper;

import java.util.List;

import com.xhSmart.model.JurisdictionGroup;
/**
 * @deprecated
 * @author lin
 *
 */
public interface JurisdictionGroupMapper {
	void save(JurisdictionGroup object);
	boolean update(JurisdictionGroup object);
	boolean remove(int id);
	JurisdictionGroup findById(int id);
	List<JurisdictionGroup> findAll();	
}
