package com.xhSmart.mapper;

import java.util.List;

import com.xhSmart.model.Jurisdiction;
/**
 * @deprecated
 * @author lin
 *
 */
public interface JurisdictionMapper {
	void save(Jurisdiction object);
	boolean update(Jurisdiction object);
	boolean remove(int id);
	Jurisdiction findById(int id);
	List<Jurisdiction> findAll();
}
