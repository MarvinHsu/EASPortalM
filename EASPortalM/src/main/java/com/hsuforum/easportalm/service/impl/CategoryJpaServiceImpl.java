package com.hsuforum.easportalm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsuforum.common.service.impl.BaseJpaServiceImpl;
import com.hsuforum.easportalm.dao.CategoryJpaRepository;
import com.hsuforum.easportalm.entity.Category;
import com.hsuforum.easportalm.service.CategoryJpaService;

@Service("categoryJpaService")
public class CategoryJpaServiceImpl extends BaseJpaServiceImpl<Category, String, CategoryJpaRepository>
		implements CategoryJpaService {

	private static final long serialVersionUID = 291137290042693201L;
	@Autowired
	private CategoryJpaRepository repo;

	@Override
	public CategoryJpaRepository getRepo() {

		return this.repo;
	}

	@Override
	public void setRepo(CategoryJpaRepository repo) {
		this.repo = repo;

	}
}
