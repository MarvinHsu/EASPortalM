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
	public void testRead() {
	
		Category testingObj = dao.findByPK("Input pk");
		
		assertEquals(testingObj.getId(),"Input pk");
	}
	

}
