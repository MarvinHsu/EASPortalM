package com.hsuforum.easportalm.service.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.hsuforum.easportalm.entity.System;
import com.hsuforum.easportalm.service.SystemService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:**/test/conf/spring/ApplicationContext.xml"})
//400請修改transactionManager為transactionManager400
@Transactional("transactionManager")
public class SystemServiceTest {
	
	@Autowired
	SystemService service;
	
	@Test
	public void testCreate() {
		System testingObj = new System();

		// TODO Input PK
		testingObj.setId("Input pk");
		service.create(testingObj);
		
		Assert.assertNotEquals(testingObj.getId(), null);;
	}
	
	@Test
	public void testRead() {
	
		System testingObj = service.findByPK("Input pk");
		
		Assert.assertEquals(testingObj.getId(),"Input pk");
	}
	
	@Test
	public void testUpdate() {	
		
		// TODO update at least a field
		System testingObj = service.findByPK("Input pk");
		testingObj.setName("test2");
		service.update(testingObj);
		
		Assert.assertEquals(testingObj.getName(), "test2");
	}
	
	@Test
	public void testDelete() {
		
		// TODO Input PK	
		System testingObj = service.findByPK("Input pk");	
		service.delete(testingObj);
		System testingObj2 = service.findByPK("Input pk");
		Assert.assertEquals(testingObj2, null);
	}	
}
