package com.dianbao.util;


import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.dianbao.common.Authorization;
import com.dianbao.controller.LoginController;

public class NoRetryInterceptor implements HandlerInterceptor {
	private static Logger log = Logger.getLogger(NoRetryInterceptor.class);

	@Autowired
	private RedisAPI redisAPI;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		//如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

		//如果请求的方法是handler方法
		if(handler instanceof HandlerMethod){
			HandlerMethod handlerMethod=(HandlerMethod)handler;
			//获取请求的方法
			Method method = handlerMethod.getMethod();
			//获取方法上标注的Authorization注解，没有返回null
			Authorization annotation = method.getAnnotation(Authorization.class);
			//标注了注解,则进行拦截
			if(annotation!=null){ 
				String token = request.getHeader("token");
				//没有提交token进行拦截
				if(token==null){
					log.info("没有token，返回未登录");
					throw new CommException(CommErrors.LOGIN_NOLOGIN);
				}else {
					if(redisAPI.exists(token)) {
						log.info("token验证通过");
						
						//刷新过期时间，先判断剩余过期时间是否大于-1(永久生存或者不存在的都是-1)
						if(redisAPI.ttl(token)>0) {
							redisAPI.expire(token, 2 * 60 * 60);
						}
						return true;
					}else {
						log.info("token不一致，返回登录已过期");
						throw new CommException(CommErrors.LOGIN_AUTH_FAIR);
					}
					
				}
				
			}
		}

		log.info("token验证通过");
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

		System.out.println("Interceptor_postHandle_One");
	}

	@Override
	public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

		System.out.println("Interceptor_afterCompletion_One");
	}
}

