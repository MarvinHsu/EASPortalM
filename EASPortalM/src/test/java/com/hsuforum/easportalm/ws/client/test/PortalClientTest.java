package com.hsuforum.easportalm.ws.client.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.hsuforum.easportalm.ws.client.PortalClient;
import com.hsuforum.easportalm.ws.vo.ModuleWSVO2;
import com.hsuforum.easportalm.ws.vo.UserWSVO;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PortalClientTest {
	@Autowired
	PortalClient portalClient;
	@Test
	public void testFindUserById() {
		portalClient.setPortalWSURI("http://localhost:8080/EASPortal/rest");;
		String account="AS";
		String systemCode="EASPortalM";
		UserWSVO  userWSVO =portalClient.findUserById(systemCode, account);
		
		System.out.println("userWSVO="+userWSVO);
	}
	
	@Test
	public void testFindModuleBySystem() {
		portalClient.setPortalWSURI("http://localhost:8080/EASPortal/rest");;
		String systemCode="EASPortalM";
		ModuleWSVO2[] moduleWSVO2s =portalClient.findModuleBySystem(systemCode);
		
		System.out.println("moduleWSVO2s="+moduleWSVO2s);
	}
}
