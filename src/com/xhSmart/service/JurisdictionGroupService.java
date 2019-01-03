package com.xhSmart.service;

import java.util.List;

import com.xhSmart.model.JurisdictionGroup;
/**
 * @deprecated
 * @author lin
 *
 */
public interface JurisdictionGroupService {
	void save(JurisdictionGroup object);
	boolean update(JurisdictionGroup object);
	boolean remove(int id);
	JurisdictionGroup findById(int id);
	List<JurisdictionGroup> findAll();
}
