package com.hsuforum.easportalm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsuforum.common.service.impl.BaseJpaServiceImpl;
import com.hsuforum.easportalm.dao.ModuleJpaRepository;
import com.hsuforum.easportalm.entity.Module;
import com.hsuforum.easportalm.service.ModuleJpaService;

@Service("moduleJpaService")
public class ModuleJpaServiceImpl extends BaseJpaServiceImpl<Module, String, ModuleJpaRepository>
		implements ModuleJpaService {
	private static final long serialVersionUID = 8166682726719828895L;

	@Autowired
	private ModuleJpaRepository repo;

	@Override
	public ModuleJpaRepository getRepo() {

		return this.repo;
	}

	@Override
	public void setRepo(ModuleJpaRepository repo) {
		this.repo = repo;

	}

}
