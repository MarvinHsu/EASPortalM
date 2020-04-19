package com.hsuforum.easportalm.dao;

import java.util.List;
import java.util.Map;

import com.hsuforum.common.dao.BaseDao;
import com.hsuforum.easportalm.entity.User;

/**
 * User Data Access Object Interface
 * 
 * @author Administrator
 *
 */
public interface UserDao extends BaseDao<User, String> {

	
	List<User> findAllFetchRelation();

	
	User doLogin(final String account);

	
	List<User> findByCriteriaMapFetchRelation(final Map<String, ? extends Object> criteriaMap);

	
	User findByAccountOtherEmail(final String account, final String email);

	
	User findByAccount(final String account);

	User findForSentActivate(final String email);

}
