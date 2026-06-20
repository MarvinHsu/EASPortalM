package com.hsuforum.easportalm.service;

import java.util.List;

import com.hsuforum.common.service.BaseService;
import com.hsuforum.easportalm.entity.FunctionItem;

public interface FunctionItemService extends BaseService<FunctionItem, java.lang.String> {

	
	List<FunctionItem> findAllFetchRelation();

}
