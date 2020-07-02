package com.dianbao.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dianbao.domain.User;
import com.dianbao.service.TokenService;
import com.dianbao.service.UserService;
import com.dianbao.util.CommErrors;
import com.dianbao.util.CommException;
import com.dianbao.util.CommonUtils;
import com.dianbao.util.MD5Util;

/**
 * @author YangYongLin
 * @date 2020年6月19日下午4:12:24
 *
 */
@Controller
public class LoginController {
	private static Logger log = Logger.getLogger(LoginController.class);

	@Autowired
	private UserService userService;
	
	@Autowired
	private TokenService tokenService;
	
	@RequestMapping(value = "/login")
	@ResponseBody
    public Map<String, Object> login(@RequestBody User user, HttpServletRequest request){
		Map<String,Object> returnMap = new HashMap<>();
        //获取用户名和密码
        String usertel=user.getUserTel();
        String password=user.getUserPassword();
        String md5pwd = MD5Util.md5password(password);
        
        User vo = userService.selectUserByTel(usertel);
        if(vo!=null) {
        	if(!vo.getUserPassword().equals(md5pwd)) {
    			throw new CommException(CommErrors.LOGIN_ISPWD);
        	}
        }else {
			throw new CommException(CommErrors.LOGIN_ISNO);
        }
        
        String token = "";
        try {
        	//创建Token
            String userAgent=request.getHeader("user-agent");
            token = tokenService.generateToken(userAgent,vo);
            tokenService.save(token,vo);
			
		} catch (Exception e) {
			// TODO: handle exception
			throw new CommException(CommErrors.API_ERROR);
		}
        
        log.info(usertel + "登录成功");
        returnMap.put("token", token);
        returnMap.put("code", "1");
        returnMap.put("message", "登录成功");
        return returnMap;
    }


    @RequestMapping(value = "/logout")
    public String logout(HttpSession session){
        //清除session
        session.invalidate();
        //重定向到登录页面的跳转方法
        return "redirect:login";
    }
	
}
