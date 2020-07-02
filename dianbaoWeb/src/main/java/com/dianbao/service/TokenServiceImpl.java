package com.dianbao.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.dianbao.domain.User;
import com.dianbao.util.MD5Util;
import com.dianbao.util.RedisAPI;



@Service
public class TokenServiceImpl  implements TokenService {
		
		@Autowired
		private RedisAPI redisAPI;
		
		
		
		@Override
	      //   pc-32位加密的用户登录账号-用户id-年月日时分 秒-6位随机数
	    //前缀 PC-NAME-USERID-CREATIONDATE-RONDEM(6位)
	    // 生成token
	    public String generateToken(String userAgent, User user) throws Exception {
	        StringBuilder  str=new StringBuilder();
	        str.append("token:");
	        //获取用户代理返回的客户端类型
	        str.append(getPlatform(userAgent)+"-");
	       
	        str.append(MD5Util.getMd5(user.getUserTel(),32)+"-");
	        str.append(user.getUserId().toString()+"-");
	        str.append(new SimpleDateFormat("yyyyMMddHHmmsss").format(new Date())+"-");
	        str.append(MD5Util.getMd5(userAgent,6));
	
	        return str.toString();
	    }
		@Override
		// 保存token信息
		public void save(String token,User user) throws Exception {
		    if(token.startsWith("token:PC-")){
		        redisAPI.set(token, 2 * 60 * 60, JSON.toJSONString(user));
		    }else{
		        redisAPI.set(token, JSON.toJSONString(user));
		    }
		
		}
		
		@Override
		public boolean validate(String userAgent, String token) throws Exception {
		        String agentMd5 = token.split("-")[4];
		      if(!redisAPI.exists(token)){
		      return false;
		      }else if(!MD5Util.getMd5(userAgent,6).equals(agentMd5)){
		         return false;
		      }
		
		    return true;
		 }
		
		
		/**java获取客户端*/
		public static String getPlatform(String agent){
		
		    /**User Agent中文名为用户代理，简称 UA，它是一个特殊字符串头，使得服务器
		    能够识别客户使用的操作系统及版本、CPU 类型、浏览器及版本、浏览器渲染引擎、浏览器语言、浏览器插件等*/  
		    //客户端类型常量
		    String type = "";
		    if(agent.contains("iPhone")||agent.contains("iPod")||agent.contains("iPad")){  
		        type = "IOS";
		    } else if(agent.contains("Android") || agent.contains("Linux")) { 
		        type = "APK";
		    } else if(agent.indexOf("micromessenger") > 0){ 
		        type = "WX";
		    }else {
		        type = "PC";
		    }
		    return type;
		}

}
