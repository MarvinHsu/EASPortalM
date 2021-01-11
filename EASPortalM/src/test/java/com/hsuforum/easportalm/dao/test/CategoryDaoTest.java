package com.hsuforum.easportalm.dao.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import com.hsuforum.easportalm.dao.CategoryDao;
import com.hsuforum.easportalm.entity.Category;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional("transactionManager")
public class CategoryDaoTest {
	
	@Autowired
	CategoryDao dao ;
	
	@Test
	public void testCreate() {
		Category testingObj = new Category();

		// TODO Input PK
		testingObj.setId("Input pk");
		dao.create(testingObj);
		
		assertNotEquals(testingObj.getId(), null);
	}

	@Test
	public void testRead() {
	
		Category testingObj = dao.findByPK("Input pk");
		
		assertEquals(testingObj.getId(),"Input pk");
	}
	
	@Test
	public void testUpdate() {	
		// TODO update at least a field
		Category testingObj = dao.findByPK("Input pk");
		testingObj.setName("test2");
		dao.update(testingObj);
		
		assertEquals(testingObj.getName(), "test2");
	}
	
	@Test
	public void testDelete() {	
		// TODO Input PK
		Category testingObj = dao.findByPK("Input pk");	
		dao.delete(testingObj);
		Category testingObj2 = dao.findByPK("Input pk");
		assertEquals(testingObj2, null);
	}	
}
