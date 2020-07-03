package com.dianbao.domain;

import java.util.Date;

/**
 * Token实体
 * @author YangYongLin
 * @date 2020年7月2日上午9:50:13
 *
 */
public class Dto {
    private String token;
 
    //token创建时间
    private String tokenCreatedTime;
 
    //失效时间
    private String tokenExpiryTime;
 
    private String isLogin;
 
    public String getToken() {
        return token;
    }
 
    public void setToken(String token) {
        this.token = token;
    }

	public String getTokenCreatedTime() {
		return tokenCreatedTime;
	}

	public void setTokenCreatedTime(String tokenCreatedTime) {
		this.tokenCreatedTime = tokenCreatedTime;
	}

	public String getTokenExpiryTime() {
		return tokenExpiryTime;
	}

	public void setTokenExpiryTime(String tokenExpiryTime) {
		this.tokenExpiryTime = tokenExpiryTime;
	}

	public String getIsLogin() {
        return isLogin;
    }
 
    public void setIsLogin(String isLogin) {
        this.isLogin = isLogin;
    }
}
