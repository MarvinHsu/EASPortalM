package com.hsuforum.easportalm.dao.jpa;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.hsuforum.common.dao.jpa.BaseDaoImpl;
import com.hsuforum.easportalm.dao.SystemDao;
import com.hsuforum.easportalm.entity.System;

@Repository("systemDao")
public class SystemDaoImpl extends BaseDaoImpl<System, java.lang.String>
		implements SystemDao {


	private static final long serialVersionUID = 41148058097310352L;
	@PersistenceContext(unitName = "default")
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
	 * 假如Entity(Business Object)，有Many-to-One或Many-to-Many的狀況，
	 * 則Code Generator會產生此Interface以供修改使用，可自行改成需要的Method，
	 * 主要作用是在read頁面就Fetch所有相關關聯的資料，免得再update頁面出現問題
	 * p.s.如有master detail的狀況請自行加入Fetch
	 * @return List<System>
	 */
	public List<System> findAllFetchRelation(){
		StringBuffer queryString = new StringBuffer();
		queryString.append("SELECT DISTINCT obj FROM System obj	");
		queryString.append("ORDER BY obj.id	") ;
	
		List<System> list = this.find(queryString);
		
		return list;
	}


}
