package com.hsuforum.easportalm.dao.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.hsuforum.easportalm.dao.SystemDao;
import com.hsuforum.easportalm.entity.System;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:**/test/conf/spring/ApplicationContext.xml"})
//400請修改transactionManager為transactionManager400
@Transactional("transactionManager")
public class SystemDaoTest {
	
	@Autowired
	SystemDao dao ;
	
	@Test
	public void testCreate() {
		System testingObj = new System();

		// TODO Input PK
		testingObj.setId("Input pk");
		dao.create(testingObj);
		
		Assert.assertNotEquals(testingObj.getId(), null);
	}

	@Test
	public void testRead() {
	
		System testingObj = dao.findByPK("Input pk");
		
		Assert.assertEquals(testingObj.getId(),"Input pk");
	}
	
	@Test
	public void testUpdate() {	
		// TODO update at least a field
		System testingObj = dao.findByPK("Input pk");
		testingObj.setName("test2");
		dao.update(testingObj);
		
		Assert.assertEquals(testingObj.getName(), "test2");
	}
	
	@Test
	public void testDelete() {	
		// TODO Input PK
		System testingObj = dao.findByPK("Input pk");	
		dao.delete(testingObj);
		System testingObj2 = dao.findByPK("Input pk");
		Assert.assertEquals(testingObj2, null);
	}	
}
