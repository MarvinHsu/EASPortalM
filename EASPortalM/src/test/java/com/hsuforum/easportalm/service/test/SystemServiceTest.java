package com.hsuforum.easportalm.service.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import com.hsuforum.easportalm.entity.System;
import com.hsuforum.easportalm.service.SystemService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
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
		
		assertNotEquals(testingObj.getId(), null);;
	}
	
	@Test
	public void testRead() {
	
		System testingObj = service.findByPK("Input pk");
		
		assertEquals(testingObj.getId(),"Input pk");
	}
	
	@Test
	public void testUpdate() {	
		
		// TODO update at least a field
		System testingObj = service.findByPK("Input pk");
		testingObj.setName("test2");
		service.update(testingObj);
		
		assertEquals(testingObj.getName(), "test2");
	}
	
	@Test
	public void testDelete() {
		
		// TODO Input PK	
		System testingObj = service.findByPK("Input pk");	
		service.delete(testingObj);
		System testingObj2 = service.findByPK("Input pk");
		assertEquals(testingObj2, null);
	}	
}
