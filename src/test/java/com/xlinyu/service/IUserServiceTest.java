package com.xlinyu.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xlinyu.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml","classpath:configuration/applicationContext-*.xml"})
public class IUserServiceTest {

	@Resource
	private IUserService userService;
	
	@Test
	public void testFindUserByUsername() {
		User user = userService.findUserByUsername("admin");
		System.out.println(user);
		
	}

}
