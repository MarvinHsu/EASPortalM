package com.hsuforum.easportalm.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsuforum.common.service.impl.BaseServiceImpl;
import com.hsuforum.easportalm.dao.GroupFunctionDao;
import com.hsuforum.easportalm.entity.GroupFunction;
import com.hsuforum.easportalm.service.GroupFunctionService;

@Service("groupFunctionService")
public class GroupFunctionServiceImpl
		extends BaseServiceImpl<GroupFunction, com.hsuforum.easportalm.entity.GroupFunctionPK, GroupFunctionDao>
		implements GroupFunctionService {


	private static final long serialVersionUID = -2007056238395614820L;

	@SuppressWarnings("unused")
	private final static Log LOG = LogFactory.getLog(GroupFunctionServiceImpl.class);

	@Autowired
	private GroupFunctionDao dao;

	
	/**
	 * @see com.hsuforum.common.service.impl.BaseServiceImpl#getDao()
	 */
	public GroupFunctionDao getDao() {
		return this.dao;
	}

	
	/**
	 * @see com.hsuforum.common.service.impl.BaseServiceImpl#setDao(com.hsuforum.common.dao.BaseDao)
	 */
	public void setDao(final GroupFunctionDao baseDao) {
		this.dao = baseDao;
	}

	
	/**
	 * @see com.hsuforum.easportalm.service.GroupFunctionService#findAllFetchRelation()
	 */
	public List<GroupFunction> findAllFetchRelation() {

		return this.getDao().findAllFetchRelation();
	}

}