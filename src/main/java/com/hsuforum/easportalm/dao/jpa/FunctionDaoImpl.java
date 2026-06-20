package com.hsuforum.easportalm.dao.jpa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hsuforum.common.dao.jpa.BaseDaoImpl;
import com.hsuforum.easportalm.dao.FunctionDao;
import com.hsuforum.easportalm.entity.Function;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository("functionDao")
public class FunctionDaoImpl extends BaseDaoImpl<Function, java.lang.String> implements FunctionDao {

	private static final long serialVersionUID = 4333226427034993737L;
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
	 * @see com.hsuforum.easportalm.dao.FunctionDao#findAllFetchRelation()
	 */
	@Override
	public List<Function> findAllFetchRelation() {
		StringBuffer queryString = new StringBuffer();
		queryString.append("SELECT DISTINCT entity FROM Function entity	");
		queryString.append("LEFT JOIN FETCH entity.functionItems ");
		queryString.append("LEFT JOIN FETCH entity.groupFunctions groupFunction ");
		queryString.append("LEFT JOIN FETCH entity.module ");
		queryString.append("LEFT JOIN FETCH groupFunction.group ");
		queryString.append("LEFT JOIN FETCH groupFunction.function ");
		queryString.append("LEFT JOIN FETCH groupFunction.functionItem ");
		queryString.append("ORDER BY entity.name	");

		List<Function> list = this.find(queryString);

		return list;
	}

	@Override
	public List<Function> findByCriteriaMap(final Map<String, ? extends Object> criteriaMap,
			final Map<String, String> operMap, final Map<String, String> sortMap) {
		StringBuffer queryString = new StringBuffer();
		queryString.append("SELECT DISTINCT entity FROM " + this.getPersistClass().getSimpleName()).append(" entity ");
		queryString.append("LEFT JOIN FETCH entity.functionItems ");
		queryString.append("LEFT JOIN FETCH entity.groupFunctions groupFunction ");
		queryString.append("LEFT JOIN FETCH entity.module ");
		queryString.append("LEFT JOIN FETCH groupFunction.group ");
		queryString.append("LEFT JOIN FETCH groupFunction.function ");
		queryString.append("LEFT JOIN FETCH groupFunction.functionItem ");
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
	 * @see com.hsuforum.easportalm.dao.FunctionDao#findBySystemIdFetchRelation(java.lang.String)
	 */
	@Override
	public List<Function> findBySystemIdFetchRelation(String systemId) {
		StringBuffer queryString = new StringBuffer();
		queryString.append("SELECT DISTINCT entity FROM Function entity	");
		queryString.append("LEFT JOIN FETCH entity.functionItems ");
		queryString.append("LEFT JOIN FETCH entity.groupFunctions groupFunction ");
		queryString.append("LEFT JOIN FETCH entity.module ");
		queryString.append("LEFT JOIN FETCH groupFunction.group ");
		queryString.append("LEFT JOIN FETCH groupFunction.function ");
		queryString.append("LEFT JOIN FETCH groupFunction.functionItem ");
		queryString.append("WHERE entity.system.id= :systemId ");
		queryString.append("ORDER BY entity.name	");
		
		Map<String,Object> params=new HashMap<String, Object>();
		params.put("systemId", systemId);
		
		return this.findByNamedParams(queryString, params);
		
	}
}
