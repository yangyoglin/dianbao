package com.dianbao.dao;

import com.dianbao.domain.User;

/**
 * 功能概要：User的DAO类
 * 
 * @author yyl
 * @since 2020年6月3日
 */
public interface UserDao {

	/**
	 * @Title: 根据ID查询用户 
	 * @author YYL 2020年6月3日 下午2:07:42
	 * @param userId
	 * @return User
	 */
	public User selectUserById(Integer userId);
	/**
	 * @Title: 根据账号查询用户 
	 * @author YYL 2020年6月3日 下午2:07:42
	 * @param userTel
	 * @return User
	 */
	public User selectUserByTel(String userTel);
}
