package com.dianbao.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.dianbao.service.FileService;


/**
 * 文件上传接口
 * @author YYL 2020年6月4日 上午11:56:14
 */
@Controller
@RequestMapping("/file")	
public class FileController {

    @Autowired
    private FileService fileService;

    @RequestMapping("/uploadImg")
	public String upload(@RequestParam("username")String username,@RequestParam("password")
	String password,MultipartFile file,HttpServletRequest request)
			throws Exception
	{
		String sqlPath=null;//保存数据库的路径
		String filename=null;//定义文件名
		String contentType=file.getContentType();	//获取文件类型（后缀）
		//因为获取的后缀是XXXX/xxx形式
		contentType=contentType.substring(contentType.indexOf("/")+1);
		filename=username+"."+contentType;
		System.out.println(filename);
		//String url = request.getSession().getServletContext().getRealPath("/userhead");
		String url = "D://img";
        System.out.println(url);  
        url=url+"/";
		file.transferTo(new File(url+filename));//保存图片
		//User u=userService.userMessage(username);
		//u.setHead("userhead/"+filename);
		//System.out.println("头像为："+u.getHead());
		//model.addAttribute("user",u);
		return "showImage";
	}
}
