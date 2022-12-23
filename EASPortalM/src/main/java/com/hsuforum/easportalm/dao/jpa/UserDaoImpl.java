package com.hsuforum.easportalm.dao.jpa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hsuforum.common.dao.jpa.BaseDaoImpl;
import com.hsuforum.easportalm.dao.UserDao;
import com.hsuforum.easportalm.entity.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

/**
 * User Data Access Object Implement
 *
 *
 */
@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User, String> implements UserDao {

	private static final long serialVersionUID = 8797453973795019949L;
	@PersistenceContext(name = "default")
	private EntityManager entityManager;

	/**
	 * @see com.hsuforum.common.dao.jpa.BaseDaoImpl#getEntityManager()
	 */
	@Override
	public EntityManager getEntityManager() {
		return this.entityManager;
	}

	/**
	 * @see com.hsuforum.common.dao.jpa.BaseDaoImpl#setEntityManager(jakarta.persistence.EntityManager)
	 */
	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	
	/**
	 * @see com.hsuforum.easportalm.dao.UserDao#findAllFetchRelation()
	 */
	@Override
	public List<User> findAllFetchRelation() {
		StringBuffer queryString = new StringBuffer();
		queryString.append("SELECT DISTINCT entity FROM User entity ");
		queryString.append("LEFT JOIN FETCH entity.groups ");
		queryString.append("ORDER BY entity.id	");

		List<User> list = this.find(queryString);

		return list;
	}

	/**
	 * @see com.hsuforum.common.dao.jpa.BaseDaoImpl#findByCriteriaMap(java.util.Map,
	 *      java.util.Map, java.util.Map)
	 */
	@Override
	public List<User> findByCriteriaMap(final Map<String, ? extends Object> criteriaMap,
			final Map<String, String> operMap, final Map<String, String> sortMap) {
		StringBuffer queryString = new StringBuffer();
		queryString.append("SELECT DISTINCT entity FROM " + this.getPersistClass().getSimpleName()).append(" entity ");
		queryString.append("LEFT JOIN FETCH entity.groups ");
		Map<String, Object> newCriteriaMap = new HashMap<String, Object>();

		boolean isTruncateWhereQueryString = false;
		boolean isTruncateSortQueryString = false;
		for (String criteriaKey : criteriaMap.keySet()) {

			if (null == criteriaMap.get(criteriaKey)) {
				continue;
			}
			if (criteriaMap.get(criteriaKey).toString().compareTo("") != 0) {
				if (!isTruncateWhereQueryString) {
					queryString.append(" WHERE ");
				}
				if (operMap != null && operMap.size() > 0) {
					if (operMap.get(criteriaKey.toString()) != null
							&& operMap.get(criteriaKey.toString()).equals("eq")) {
						queryString.append("entity.").append(criteriaKey.toString()).append(" = :")
								.append(criteriaKey.toString().replace(".", "_"));
					} else if (operMap.get(criteriaKey.toString()) != null
							&& operMap.get(criteriaKey.toString()).equals("ge")) {
						queryString.append("entity.").append(criteriaKey.toString()).append(" >= :")
								.append(criteriaKey.toString().replace(".", "_"));
					} else if (operMap.get(criteriaKey.toString()) != null
							&& operMap.get(criteriaKey.toString()).equals("gt")) {
						queryString.append("entity.").append(criteriaKey.toString()).append(" > :")
								.append(criteriaKey.toString().replace(".", "_"));
					} else if (operMap.get(criteriaKey.toString()) != null
							&& operMap.get(criteriaKey.toString()).equals("le")) {
						queryString.append("entity.").append(criteriaKey.toString()).append(" <= :")
								.append(criteriaKey.toString().replace(".", "_"));
					} else if (operMap.get(criteriaKey.toString()) != null
							&& operMap.get(criteriaKey.toString()).equals("lt")) {
						queryString.append("entity.").append(criteriaKey.toString()).append(" < :")
								.append(criteriaKey.toString().replace(".", "_"));
					} else if (operMap.get(criteriaKey.toString()) != null
							&& operMap.get(criteriaKey.toString()).equals("ne")) {
						queryString.append("entity.").append(criteriaKey.toString()).append(" >< :")
								.append(criteriaKey.toString().replace(".", "_"));
					} else if (operMap.get(criteriaKey.toString()) != null
							&& operMap.get(criteriaKey.toString()).equals("like")) {
						queryString.append("entity.").append(criteriaKey.toString()).append(" like :")
								.append(criteriaKey.toString().replace(".", "_"));
					} else {
						queryString.append("entity.").append(criteriaKey.toString()).append(" = :")
								.append(criteriaKey.toString().replace(".", "_"));
					}
				} else {
					queryString.append("entity.").append(criteriaKey.toString()).append(" = :")
							.append(criteriaKey.toString().replace(".", "_"));
				}
				queryString.append(" AND ");

				newCriteriaMap.put(criteriaKey.replace(".", "_"), criteriaMap.get(criteriaKey));
				isTruncateWhereQueryString = true;
			}
		}

		if (isTruncateWhereQueryString) {
			int lastIndex = queryString.lastIndexOf("AND");
			queryString.delete(lastIndex, queryString.length());
		}

		if (sortMap != null) {
			for (String sortKey : sortMap.keySet()) {

				if (null == sortMap.get(sortKey)) {
					continue;
				}
				if (sortMap.get(sortKey).toString().compareTo("") != 0) {
					if (!isTruncateSortQueryString) {
						queryString.append(" ORDER BY ");

					}
					queryString.append("entity.").append(sortKey.toString()).append(" ").append(sortMap.get(sortKey));
					queryString.append(" , ");

					isTruncateSortQueryString = true;
				}
			}

			if (isTruncateSortQueryString) {
				int lastIndex = queryString.lastIndexOf(",");
				queryString.delete(lastIndex, queryString.length());
			}
		}
		return this.findByNamedParams(queryString, newCriteriaMap);
	}

	/**
	 * @see com.hsuforum.easportalm.dao.UserDao#doLogin(java.lang.String)
	 */
	@Override
	public User doLogin(final String account) {

		StringBuffer queryString = new StringBuffer();
		queryString.append("SELECT DISTINCT user ");
		queryString.append("FROM User user ");
		queryString.append("    LEFT JOIN FETCH user.groups gup ");
		queryString.append("    LEFT JOIN FETCH gup.groupFunctions groupFunction ");
		queryString.append("    LEFT JOIN FETCH groupFunction.functionItem functionItem ");
		queryString.append("    LEFT JOIN FETCH groupFunction.function fun ");
		queryString.append("    LEFT JOIN FETCH fun.module ");
		queryString.append("WHERE ");
		queryString.append("    user.account = :account ");

		Map<String, String> params = new HashMap<String, String>();
		params.put("account", account);
		List<User> userList = (List<User>) this.findByNamedParams(queryString, params);

		User user = null;
		if ((userList != null) && (userList.size() > 0)) {

			user = userList.get(0);
		}

		return user;
	}

	/**
	 * @see com.hsuforum.easportalm.dao.UserDao#findByCriteriaMapFetchRelation(java.util.Map)
	 */
	@Override
	public List<User> findByCriteriaMapFetchRelation(Map<String, ? extends Object> criteriaMap) {
		StringBuffer queryString = new StringBuffer();
		queryString.append("SELECT DISTINCT user FROM User user ");
		queryString.append("    LEFT JOIN FETCH user.groups gup ");
		queryString.append("    LEFT JOIN FETCH gup.groupFunctions groupFunction ");
		queryString.append("    LEFT JOIN FETCH groupFunction.function fun ");

		Map<String, Object> newCriteriaMap = new HashMap<String, Object>();

		
		boolean isTruncateQueryString = false;
		for (String criteriaKey : criteriaMap.keySet()) {
		
			if (criteriaMap.get(criteriaKey).toString().compareTo("") != 0) {
				if (!isTruncateQueryString) {
					queryString.append("WHERE ");
					;
				}
				queryString.append("user." + criteriaKey.toString() + " = :" + criteriaKey.toString());
				queryString.append(" AND ");

				newCriteriaMap.put(criteriaKey, criteriaMap.get(criteriaKey));
				isTruncateQueryString = true;
			}
		}
		
		if (isTruncateQueryString) {
			int lastIndex = queryString.lastIndexOf("AND");
			queryString.delete(lastIndex, queryString.length());
		}
		queryString.append(" ORDER BY user.id ");
		return this.findByNamedParams(queryString, newCriteriaMap);
	}

	/**
	 * @see com.hsuforum.easportalm.dao.UserDao#findByAccountOtherEmail(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public User findByAccountOtherEmail(final String account, final String email) {
		StringBuffer queryString = new StringBuffer();
		queryString.append("SELECT DISTINCT user ");
		queryString.append("FROM User user ");
		queryString.append("WHERE ");
		queryString.append("    user.account <> :account AND     ");
		queryString.append("    user.email = :email ");

		Map<String, String> params = new HashMap<String, String>();
		params.put("account", account);
		params.put("email", email);

		List<User> userList = this.findByNamedParams(queryString, params);

		if ((userList != null) && (userList.size() > 0)) {

			return userList.get(0);
		} else {
			return null;
		}
	}

	/**
	 * @see com.hsuforum.easportalm.dao.UserDao#findByAccount(java.lang.String)
	 */
	@Override
	public User findByAccount(final String account) {
		Map<String, String> criteriaMap = new HashMap<String, String>();
		criteriaMap.put("account", account);
		User user = this.findByCriteriaMapReturnUnique(criteriaMap);
		return user;

	}

	/**
	 * @see com.hsuforum.easportalm.dao.UserDao#findForSentActivate(java.lang.String)
	 */
	@Override
	public User findForSentActivate(final String email) {

		StringBuffer queryString = new StringBuffer();
		queryString.append("SELECT DISTINCT user ");
		queryString.append("FROM User user ");
		queryString.append("WHERE ");
		queryString.append("    user.email = :email AND     ");
		queryString.append("    user.userId is NULL  ");

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("email", email);

		Query query = this.getEntityManager().createQuery(queryString.toString(), User.class);
		if (params != null) {
			for (String param : params.keySet()) {
				query.setParameter(param, params.get(param));

			}
		}
		Object entity = query.getSingleResult();
		if (entity == null) {
			return null;
		} else {

			return (User) entity;
		}
	}

}
