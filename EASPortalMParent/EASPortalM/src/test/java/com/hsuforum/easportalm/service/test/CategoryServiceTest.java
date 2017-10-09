package com.hsuforum.easportalm.service.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.hsuforum.easportalm.entity.Category;
import com.hsuforum.easportalm.service.CategoryService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:**/test/conf/spring/ApplicationContext.xml"})
//400請修改transactionManager為transactionManager400
@Transactional("transactionManager")
public class CategoryServiceTest {
	
	@Autowired
	CategoryService service;
	
	@Test
	public void testCreate() {
		Category testingObj = new Category();

		// TODO Input PK
		testingObj.setId("Input pk");
		service.create(testingObj);
		
		Assert.assertNotEquals(testingObj.getId(), null);;
	}
	
	@Test
	public void testRead() {
	
		Category testingObj = service.findByPK("Input pk");
		
		Assert.assertEquals(testingObj.getId(),"Input pk");
	}
	
	@Test
	public void testUpdate() {	
		
		// TODO update at least a field
		Category testingObj = service.findByPK("Input pk");
		testingObj.setName("test2");
		service.update(testingObj);
		
		Assert.assertEquals(testingObj.getName(), "test2");
	}
	
	@Test
	public void testDelete() {
		
		// TODO Input PK	
		Category testingObj = service.findByPK("Input pk");	
		service.delete(testingObj);
		Category testingObj2 = service.findByPK("Input pk");
		Assert.assertEquals(testingObj2, null);
	}	
}
