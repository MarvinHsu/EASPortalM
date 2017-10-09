package com.hsuforum.easportalm.dao.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.hsuforum.easportalm.dao.FunctionDao;
import com.hsuforum.easportalm.entity.Function;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:**/test/conf/spring/ApplicationContext.xml"})
//400請修改transactionManager為transactionManager400
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
