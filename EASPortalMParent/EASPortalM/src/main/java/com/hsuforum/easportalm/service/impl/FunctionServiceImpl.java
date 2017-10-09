package com.hsuforum.easportalm.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsuforum.common.service.impl.BaseServiceImpl;
import com.hsuforum.easportalm.dao.FunctionDao;
import com.hsuforum.easportalm.entity.Function;
import com.hsuforum.easportalm.service.FunctionService;

@Service("functionService")
public class FunctionServiceImpl extends BaseServiceImpl<Function, java.lang.String, FunctionDao>
		implements FunctionService {


	private static final long serialVersionUID = -2220166170087352823L;

	@SuppressWarnings("unused")
	private final static Log LOG = LogFactory.getLog(FunctionServiceImpl.class);

	@Autowired
	private FunctionDao dao;

	
	/**
	 * @see com.hsuforum.common.service.impl.BaseServiceImpl#getDao()
	 */
	public FunctionDao getDao() {
		return this.dao;
	}

	
	/**
	 * @see com.hsuforum.common.service.impl.BaseServiceImpl#setDao(com.hsuforum.common.dao.BaseDao)
	 */
	public void setDao(final FunctionDao baseDao) {
		this.dao = baseDao;
	}

	
	/**
	 * @see com.hsuforum.easportalm.service.FunctionService#findAllFetchRelation()
	 */
	public List<Function> findAllFetchRelation() {

		return this.getDao().findAllFetchRelation();
	}


	/**
	 * @see com.hsuforum.easportalm.service.FunctionService#findBySystemIdFetchRelation(java.lang.String)
	 */
	@Override
	public List<Function> findBySystemIdFetchRelation(String systemId) {
		
		return this.getDao().findBySystemIdFetchRelation(systemId);
	}

}