package com.dianbao.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.support.spring.FastJsonJsonView;

public class DefaultExceptionHandler implements HandlerExceptionResolver {  
	private static Logger log = Logger.getLogger(DefaultExceptionHandler.class);
    

	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,  CommException ex) {  
	        ModelAndView mv = new ModelAndView();
	        /*	使用FastJson提供的FastJsonJsonView视图返回，不需要捕获异常	*/
	        FastJsonJsonView view = new FastJsonJsonView();
	        Map<String, Object> attributes = new HashMap<String, Object>();
	        attributes.put("code", ex.getCode());
	        attributes.put("message", ex.getMessage());
	        view.setAttributesMap(attributes);
	        mv.setView(view); 
	        log.debug("异常:" + ex.getMessage(), ex);
	        return mv;
    }
	
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,  Exception ex) {  
	        ModelAndView mv = new ModelAndView();
	        /*	使用FastJson提供的FastJsonJsonView视图返回，不需要捕获异常	*/
	        FastJsonJsonView view = new FastJsonJsonView();
	        Map<String, Object> attributes = new HashMap<String, Object>();
	        CommException co = new CommException();
	        if(ex.getClass() == CommException.class) {
	        	BeanUtils.copyProperties(ex, co);
	        }
	        if(co!=null && co.getCode()!=null && co.getCode()!=0) {
		        attributes.put("code", co.getCode());
	        }else {
	        	attributes.put("code", "1000001");
	        }
	        attributes.put("message", ex.getMessage());
	        view.setAttributesMap(attributes);
	        mv.setView(view); 
	        log.debug("异常:" + ex.getMessage());
	        return mv;
    }
    
} 
