package com.hsuforum.easportalm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsuforum.common.service.impl.BaseJpaServiceImpl;
import com.hsuforum.easportalm.dao.GroupJpaRepository;
import com.hsuforum.easportalm.entity.Group;
import com.hsuforum.easportalm.service.GroupJpaService;

@Service("groupJpaService")
public class GroupJpaServiceImpl extends BaseJpaServiceImpl<Group, String, GroupJpaRepository>
		implements GroupJpaService {
	private static final long serialVersionUID = 8166682726719828895L;

	@Autowired
	private GroupJpaRepository repo;

	@Override
	public GroupJpaRepository getRepo() {

		return this.repo;
	}

	@Override
	public void setRepo(GroupJpaRepository repo) {
		this.repo = repo;

	}

}
