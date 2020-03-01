package com.hsuforum.easportalm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsuforum.common.service.impl.BaseJpaServiceImpl;
import com.hsuforum.easportalm.dao.UserJpaRepository;
import com.hsuforum.easportalm.entity.User;
import com.hsuforum.easportalm.service.UserJpaService;

@Service("userJpaService")
public class UserJpaServiceImpl extends BaseJpaServiceImpl<User, String, UserJpaRepository> implements UserJpaService {
	private static final long serialVersionUID = 8166682726719828895L;

	@Autowired
	private UserJpaRepository repo;

	@Override
	public UserJpaRepository getRepo() {

		return this.repo;
	}

	@Override
	public void setRepo(UserJpaRepository repo) {
		this.repo = repo;

	}

}
