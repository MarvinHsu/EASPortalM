package com.hsuforum.easportalm.dao.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.hsuforum.easportalm.dao.SystemDao;
import com.hsuforum.easportalm.entity.System;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SystemDaoTest {
	
	@Autowired
	SystemDao dao ;
	
	@Test
	public void testCreate() {
		System testingObj = new System();

		// TODO Input PK
		testingObj.setId("Input pk");
		dao.create(testingObj);
		
		assertNotEquals(testingObj.getId(), null);
	}

	@Test
	public void testRead() {
	
		System testingObj = dao.findByPK("Input pk");
		
		assertEquals(testingObj.getId(),"Input pk");
	}
	
	@Test
	public void testUpdate() {	
		// TODO update at least a field
		System testingObj = dao.findByPK("Input pk");
		testingObj.setName("test2");
		dao.update(testingObj);
		
		assertEquals(testingObj.getName(), "test2");
	}
	
	@Test
	public void testDelete() {	
		// TODO Input PK
		System testingObj = dao.findByPK("Input pk");	
		dao.delete(testingObj);
		System testingObj2 = dao.findByPK("Input pk");
		assertEquals(testingObj2, null);
	}	
}
