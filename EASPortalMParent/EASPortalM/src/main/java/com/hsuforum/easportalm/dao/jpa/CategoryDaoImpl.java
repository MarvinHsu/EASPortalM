package com.hsuforum.easportalm.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.hsuforum.common.dao.jpa.BaseDaoImpl;
import com.hsuforum.easportalm.dao.CategoryDao;
import com.hsuforum.easportalm.entity.Category;

@Repository("categoryDao")
public class CategoryDaoImpl extends BaseDaoImpl<Category, java.lang.String>
		implements CategoryDao {

	private static final long serialVersionUID = -2575451437963389960L;
	@PersistenceContext(unitName = "persistenceUnit")
    private EntityManager entityManager;
    
    /**
     * 取得EntityManager
     * @return
     */
    public EntityManager getEntityManager(){
    	return this.entityManager;
    }

    /**
     * 設定EntityManager
     * @param entityManager
     */
    public void setEntityManager(EntityManager entityManager){
    	this.entityManager = entityManager;
    }


	
	
	/**
	 * @see com.hsuforum.easportalm.dao.CategoryDao#findAllFetchRelation()
	 */
	public List<Category> findAllFetchRelation(){
		StringBuffer queryString = new StringBuffer();
		queryString.append("SELECT DISTINCT obj FROM Category obj	");
				queryString.append("ORDER BY obj.id	") ;
	
		List<Category> list = this.find(queryString);
		
		return list;
	}


}
