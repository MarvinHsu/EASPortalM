package com.hsuforum.easportalm.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsuforum.common.service.impl.BaseServiceImpl;
import com.hsuforum.easportalm.dao.FunctionItemDao;
import com.hsuforum.easportalm.entity.FunctionItem;
import com.hsuforum.easportalm.service.FunctionItemService;

@Service("functionItemService")
public class FunctionItemServiceImpl extends BaseServiceImpl<FunctionItem, java.lang.String, FunctionItemDao>
		implements FunctionItemService {


	private static final long serialVersionUID = -3230057611181714682L;

	@SuppressWarnings("unused")
	private final static Log LOG = LogFactory.getLog(FunctionItemServiceImpl.class);

	@Autowired
	private FunctionItemDao dao;

	
	/**
	 * @see com.hsuforum.common.service.impl.BaseServiceImpl#getDao()
	 */
	public FunctionItemDao getDao() {
		return this.dao;
	}

	
	/**
	 * @see com.hsuforum.common.service.impl.BaseServiceImpl#setDao(com.hsuforum.common.dao.BaseDao)
	 */
	public void setDao(final FunctionItemDao baseDao) {
		this.dao = baseDao;
	}

	
	/**
	 * @see com.hsuforum.easportalm.service.FunctionItemService#findAllFetchRelation()
	 */
	public List<FunctionItem> findAllFetchRelation() {

		return this.getDao().findAllFetchRelation();
	}

}