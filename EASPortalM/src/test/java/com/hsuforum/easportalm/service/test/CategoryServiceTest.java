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
	public void testRead() {
	
		Category testingObj = service.findByPK("Input pk");
		
		assertEquals(testingObj.getId(),"Input pk");
	}
	
}
