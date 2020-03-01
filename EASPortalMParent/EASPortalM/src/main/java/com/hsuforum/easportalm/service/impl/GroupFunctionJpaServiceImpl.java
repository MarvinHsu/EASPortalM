package com.hsuforum.easportalm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsuforum.common.service.impl.BaseJpaServiceImpl;
import com.hsuforum.easportalm.dao.GroupFunctionJpaRepository;
import com.hsuforum.easportalm.entity.GroupFunction;
import com.hsuforum.easportalm.service.GroupFunctionJpaService;

@Service("groupFunctionJpaService")
public class GroupFunctionJpaServiceImpl
		extends BaseJpaServiceImpl<GroupFunction, String, GroupFunctionJpaRepository>
		implements GroupFunctionJpaService {
	private static final long serialVersionUID = 8166682726719828895L;

	@Autowired
	private GroupFunctionJpaRepository repo;

	@Override
	public GroupFunctionJpaRepository getRepo() {

		return this.repo;
	}

	@Override
	public void setRepo(GroupFunctionJpaRepository repo) {
		this.repo = repo;

	}

}
