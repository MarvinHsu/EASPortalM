package com.hsuforum.easportalm.service.impl;




import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsuforum.common.service.impl.BaseServiceImpl;
import com.hsuforum.easportalm.dao.SystemDao;
import com.hsuforum.easportalm.entity.System;
import com.hsuforum.easportalm.service.SystemService;

@Service("systemService")
public class SystemServiceImpl extends BaseServiceImpl<System, java.lang.String, SystemDao>
	implements SystemService {

	private static final long serialVersionUID = -565292475772815843L;

	@SuppressWarnings("unused")
	private final static Log LOG = LogFactory.getLog(SystemServiceImpl.class);

	@Autowired
	private SystemDao dao;

 	/**
	 * 回傳Data Access Object
	 *
	 * @return systemDao
	 */
	public SystemDao getDao() {
		return this.dao;
	}

	/**
	 * 設定Data Access Object
	 *
	 * @param baseDao
	 */
	public void setDao(final SystemDao baseDao) {
		this.dao = baseDao;
	}
 
		


	
	/**
	 * 假如Entity(Business Object)，有Many-to-One或Many-to-Many的狀況，
	 * 則Code Generator會產生此Interface以供修改使用，可自行改成需要的Method，
	 * 主要作用是在read頁面就Fetch所有相關關聯的資料，免得再update頁面出現問題
	 * @return List<System>
	 */
	public List<System> findAllFetchRelation(){
		
		return this.getDao().findAllFetchRelation();
	}



}