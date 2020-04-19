package com.hsuforum.easportalm.dao.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.hsuforum.easportalm.dao.FunctionDao;
import com.hsuforum.easportalm.entity.Function;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional("transactionManager")
public class FunctionDaoTest {
	
	@Autowired
	FunctionDao dao ;
	
	@Test
	public void findBySystemIdFetchRelationTestCreate() {
		
		String systemId="PortalM";
		List<Function> result=dao.findBySystemIdFetchRelation(systemId);
		
		Assert.assertEquals(result, null);
	}

}
