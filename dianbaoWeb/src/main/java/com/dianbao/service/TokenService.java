package com.dianbao.service;

import com.dianbao.domain.User;

public interface TokenService {

	//生成token
	public String generateToken(String userAgent, User user) throws Exception;

	 //生成save
	public void save(String token, User user) throws Exception;

	//验证
	public boolean validate(String userAgent,String token) throws Exception;
}
