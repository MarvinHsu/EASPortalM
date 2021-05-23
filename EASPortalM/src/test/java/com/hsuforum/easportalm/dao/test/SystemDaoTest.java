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
	public void testRead() {
	
		System testingObj = dao.findByPK("Input pk");
		
		assertEquals(testingObj.getId(),"Input pk");
	}

}
