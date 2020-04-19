package com.hsuforum.easportalm.ws.client.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.hsuforum.easportalm.ws.client.PortalClient;
import com.hsuforum.easportalm.ws.vo.ModuleWSVO2;
import com.hsuforum.easportalm.ws.vo.UserWSVO;
@RunWith(SpringRunner.class)
@SpringBootTest
public class PortalClientTest {
	@Autowired
	PortalClient portalClient;
	@Test
	public void testFindUserById() {
		portalClient.setPortalWSURI("https://localhost:8443/EASPortal/rest");;
		String account="ADMIN";
		String systemCode="EASPortalM";
		UserWSVO  userWSVO =portalClient.findUserById(systemCode, account);
		
		System.out.println("userWSVO="+userWSVO);
	}
	
	@Test
	public void testFindModuleBySystem() {
		portalClient.setPortalWSURI("https://localhost:8443/EASPortal/rest");;
		String systemCode="EASPortalM";
		ModuleWSVO2[] moduleWSVO2s =portalClient.findModuleBySystem(systemCode);
		
		System.out.println("moduleWSVO2s="+moduleWSVO2s);
	}
}
