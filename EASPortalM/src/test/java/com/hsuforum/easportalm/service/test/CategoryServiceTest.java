package com.hsuforum.easportalm.service.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import com.hsuforum.easportalm.entity.Category;
import com.hsuforum.easportalm.service.CategoryService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
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
		
		assertNotEquals(testingObj.getId(), null);;
	}
	
	@Test
	public void testRead() {
	
		Category testingObj = service.findByPK("Input pk");
		
		assertEquals(testingObj.getId(),"Input pk");
	}
	
	@Test
	public void testUpdate() {	
		
		// TODO update at least a field
		Category testingObj = service.findByPK("Input pk");
		testingObj.setName("test2");
		service.update(testingObj);
		
		assertEquals(testingObj.getName(), "test2");
	}
	
	@Test
	public void testDelete() {
		
		// TODO Input PK	
		Category testingObj = service.findByPK("Input pk");	
		service.delete(testingObj);
		Category testingObj2 = service.findByPK("Input pk");
		assertEquals(testingObj2, null);
	}	
}
