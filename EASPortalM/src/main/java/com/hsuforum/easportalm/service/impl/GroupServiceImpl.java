package com.hsuforum.easportalm.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsuforum.common.service.impl.BaseServiceImpl;
import com.hsuforum.easportalm.dao.GroupDao;
import com.hsuforum.easportalm.entity.Group;
import com.hsuforum.easportalm.service.GroupService;

/**
 * Group Service Implement
 * 
 * @author Administrator
 *
 */
@Service("groupService")
public class GroupServiceImpl extends BaseServiceImpl<Group, String, GroupDao> implements GroupService {


	private static final long serialVersionUID = 9004674617130521599L;

	@SuppressWarnings("unused")
	private final static Log LOG = LogFactory.getLog(GroupServiceImpl.class);

	@Autowired
	private GroupDao dao;

	/**
	 * @see com.hsuforum.common.service.impl.BaseServiceImpl#getDao()
	 */
	public GroupDao getDao() {
		return dao;
	}

	/**
	 * @see com.hsuforum.common.service.impl.BaseServiceImpl#setDao(com.hsuforum.common.dao.BaseDao)
	 */
	public void setDao(GroupDao dao) {
		this.dao = dao;
	}

	
	/**
	 * @see com.hsuforum.easportalm.service.GroupService#findAllFetchRelation()
	 */
	public List<Group> findAllFetchRelation() {

		return this.getDao().findAllFetchRelation();
	}

	/**
	 * @see com.hsuforum.easportalm.service.GroupService#findByPKFetchFuntions(java.lang.Long)
	 */
	public Group findByPKFetchFunctions(String pk) {

		return this.getDao().findByPKFetchFunctions(pk);

	}

	/**
	 * @see com.hsuforum.easportalm.service.GroupService#findByPKFetchUsers(java.lang.String)
	 */
	public Group findByPKFetchUsers(String pk) {
		return this.getDao().findByPKFetchUsers(pk);
	}
}
