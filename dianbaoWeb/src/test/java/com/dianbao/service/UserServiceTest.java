package com.dianbao.service;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.dianbao.baseTest.SpringTestCase;
import com.dianbao.domain.User;

/**
 * 功能概要：UserService单元测试
 * 
 * @author yyl
 * @since  2020年6月3日 
 */
public class UserServiceTest extends SpringTestCase	{
	@Autowired
	private UserService userService;
	Logger logger = Logger.getLogger(UserServiceTest.class);
	
	@Test
	public void selectUserByIdTest(){
		User user = userService.selectUserById(9);
        logger.debug("查找结果" + user);
	}
	
 
}