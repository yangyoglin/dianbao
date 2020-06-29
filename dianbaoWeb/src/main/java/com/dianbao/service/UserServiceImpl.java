package com.dianbao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dianbao.dao.UserDao;
import com.dianbao.domain.User;

/**
 * 用户管理实现类
 * @author yyl
 *
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	public User selectUserById(Integer userId) {
		// TODO Auto-generated method stub
		return userDao.selectUserById(userId);
	}

	public User selectUserByTel(String userTel) {
		// TODO Auto-generated method stub
		return userDao.selectUserByTel(userTel);
	}

}
