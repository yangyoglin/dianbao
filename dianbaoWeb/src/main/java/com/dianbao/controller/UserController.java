package com.dianbao.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dianbao.domain.User;
import com.dianbao.service.UserService;

/**
 * 功能概要：UserController
 * 
 * @author yyl
 * @since  2020年6月3日 
 */
@Controller
public class UserController {
	@Resource
	private UserService userService;
	
	/*@RequestMapping("/")  
    public ModelAndView getIndex(){    
		ModelAndView mav = new ModelAndView("index"); 
		User user = userService.selectUserById(9);
	    mav.addObject("user", user); 
        return mav;  
    }  */

	@RequestMapping("/")  
    public String getIndex(){    
		ModelAndView mav = new ModelAndView("index"); 
		User user = userService.selectUserById(9);
	    mav.addObject("user", user); 
        return "home";  
    }  

}
