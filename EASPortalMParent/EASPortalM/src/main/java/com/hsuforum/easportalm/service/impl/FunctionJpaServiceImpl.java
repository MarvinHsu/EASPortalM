package com.hsuforum.easportalm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsuforum.common.service.impl.BaseJpaServiceImpl;
import com.hsuforum.easportalm.dao.FunctionJpaRepository;
import com.hsuforum.easportalm.entity.Function;
import com.hsuforum.easportalm.service.FunctionJpaService;

@Service("functionJpaService")
public class FunctionJpaServiceImpl extends BaseJpaServiceImpl<Function, String, FunctionJpaRepository>
		implements FunctionJpaService {
	private static final long serialVersionUID = 8166682726719828895L;
	
	@Autowired
	private FunctionJpaRepository repo;
	
	@Override
	public FunctionJpaRepository getRepo() {

		return this.repo;
	}

	@Override
	public void setRepo(FunctionJpaRepository repo) {
		this.repo=repo;
		
	}

}
