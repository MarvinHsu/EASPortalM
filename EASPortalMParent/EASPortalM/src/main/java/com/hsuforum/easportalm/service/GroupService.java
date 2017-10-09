package com.hsuforum.easportalm.service;

import java.util.List;

import com.hsuforum.common.service.BaseService;
import com.hsuforum.easportalm.entity.Group;

/**
 * Group Service Interface
 *
 *
 */
public interface GroupService extends BaseService<Group, String> {

	
	List<Group> findAllFetchRelation();

	
	Group findByPKFetchFunctions(String pk);

	
	Group findByPKFetchUsers(String pk);

}
