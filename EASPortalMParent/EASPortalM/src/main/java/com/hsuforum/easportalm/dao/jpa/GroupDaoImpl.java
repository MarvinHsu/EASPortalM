package com.hsuforum.easportalm.dao.jpa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.hsuforum.common.dao.jpa.BaseDaoImpl;
import com.hsuforum.easportalm.dao.GroupDao;
import com.hsuforum.easportalm.entity.Group;

/**
 * Group Data Access Object Implement
 *
 *
 */
@Repository("groupDao")
public class GroupDaoImpl extends BaseDaoImpl<Group, String> implements GroupDao {


	private static final long serialVersionUID = -1136997925734835504L;
	@PersistenceContext(name = "persistenceUnit")
	private EntityManager entityManager;

	/**
	 * @see com.hsuforum.common.dao.jpa.BaseDaoImpl#getEntityManager()
	 */
	@Override
	public EntityManager getEntityManager() {
		return this.entityManager;
	}

	/**
	 * @see com.hsuforum.common.dao.jpa.BaseDaoImpl#setEntityManager(javax.persistence.EntityManager)
	 */
	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	
	/**
	 * @see com.hsuforum.easportalm.dao.GroupDao#findAllFetchRelation()
	 */
	@Override
	public List<Group> findAllFetchRelation() {
		StringBuffer queryString = new StringBuffer();
		queryString.append("SELECT DISTINCT entity FROM Group entity ");
		queryString.append("LEFT JOIN FETCH entity.groupFunctions groupFunction ");
		queryString.append("LEFT JOIN FETCH entity.users ");
		queryString.append("LEFT JOIN FETCH entity.system ");
		queryString.append("LEFT JOIN FETCH groupFunction.group ");
		queryString.append("LEFT JOIN FETCH groupFunction.function ");
		queryString.append("LEFT JOIN FETCH groupFunction.functionItem ");
		queryString.append("ORDER BY entity.id	");

		List<Group> list = this.find(queryString);

		return list;
	}

	@Override
	public List<Group> findByCriteriaMap(final Map<String, ? extends Object> criteriaMap,
			final Map<String, String> operMap, final Map<String, String> sortMap) {
		StringBuffer queryString = new StringBuffer();
		queryString.append("SELECT DISTINCT entity FROM " + this.getPersistClass().getSimpleName()).append(" entity ");
		queryString.append("LEFT JOIN FETCH entity.groupFunctions groupFunction ");
		queryString.append("LEFT JOIN FETCH entity.users ");
		queryString.append("LEFT JOIN FETCH entity.system ");
		queryString.append("LEFT JOIN FETCH groupFunction.group ");
		queryString.append("LEFT JOIN FETCH groupFunction.function ");
		queryString.append("LEFT JOIN FETCH groupFunction.functionItem ");
		Map<String, Object> newCriteriaMap = new HashMap<String, Object>();

		// 用來判斷是否需要裁減String，因為條件句，最後可能是"AND"字串
		boolean isTruncateWhereQueryString = false;
		boolean isTruncateSortQueryString = false;
		for (String criteriaKey : criteriaMap.keySet()) {
			// 假如條件不為空則加入條件句
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
	 * @see com.hsuforum.easportalm.service.GroupService#findByPKFetchFuntions(java.lang.Long)
	 */
	@Override
	public Group findByPKFetchFunctions(String pk) {

		StringBuffer queryString = new StringBuffer();
		queryString.append("SELECT DISTINCT g ");
		queryString.append("FROM Group g ");
		queryString.append("    LEFT JOIN FETCH g.users ");
		queryString.append("WHERE g.id=:id ");
		queryString.append("ORDER BY g.id ");

		Map<String, String> params = new HashMap<String, String>();
		params.put("id", pk);

		List<Group> groupList = this.findByNamedParams(queryString, params);

		if (groupList.size() > 0) {
			return groupList.get(0);
		} else {
			return null;
		}
	}

	/**
	 * @see com.hsuforum.easportalm.dao.GroupDao#findByPKFetchUsers(java.lang.String)
	 */
	@Override
	public Group findByPKFetchUsers(String pk) {

		StringBuffer queryString = new StringBuffer();
		queryString.append("SELECT DISTINCT g ");
		queryString.append("FROM Group g ");
		queryString.append("    LEFT JOIN FETCH g.users ");
		queryString.append("WHERE g.id=:id ");
		queryString.append("ORDER BY g.id ");

		Map<String, String> params = new HashMap<String, String>();
		params.put("id", pk);

		List<Group> groupList = this.findByNamedParams(queryString, params);

		if (groupList.size() > 0) {
			return groupList.get(0);
		} else {
			return null;
		}
	}

}
