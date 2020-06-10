package com.dianbao.service;

import com.dianbao.domain.User;

/**
 * 用户管理
 * @author yyl
 *
 */
public interface UserService {

	/**
	 * @Title: 根据ID查询用户  
	 * @author YYL 2020年6月3日 下午2:08:47
	 * @param userId
	 * @return User
	 */
	User selectUserById(Integer userId);
}
