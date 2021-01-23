package com.hsuforum.easportalm.dao.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import com.hsuforum.easportalm.dao.FunctionDao;
import com.hsuforum.easportalm.entity.Function;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional("transactionManager")
public class FunctionDaoTest {
	
	@Autowired
	FunctionDao dao ;
	
	@Test
	public void findBySystemIdFetchRelationTestCreate() {
		
		String systemId="PortalM";
		List<Function> result=dao.findBySystemIdFetchRelation(systemId);
		
		assertEquals(result, null);
	}

}
