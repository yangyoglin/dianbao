package com.dianbao.controller;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.dianbao.domain.ImagesInfo;
import com.dianbao.service.ImagesInfoService;
import com.dianbao.util.CommErrors;
import com.dianbao.util.CommException;
import com.dianbao.util.CommonUtils;
import com.dianbao.util.Page;
import com.dianbao.util.StringUtils;

/**
 * 文件管理接口
 * 
 * @author YangYongLin
 *
 */
@Controller
@RequestMapping("/imagesinfo")
public class ImagesInfoController {

	@Autowired
	private ImagesInfoService imagesInfoService;

	@RequestMapping("/uploadImg")
	@ResponseBody
	public Map<String, Object> upload(@RequestParam("imagesName") String imagesName,
			@RequestParam("imagesModuleCode") String imagesModuleCode, @RequestParam("imagesSort") String imagesSort,
			@RequestParam("imagesModuleName") String imagesModuleName,@RequestParam("note") String note,
			MultipartFile file, HttpServletRequest request)
			throws Exception {

		if (file == null) {
			throw new CommException(CommErrors.ADMIN_PARAMS_NULL);
		}
		ImagesInfo param = new ImagesInfo();
		param.setImagesName(imagesName);
		param.setImagesModuleCode(imagesModuleCode);
		param.setImagesModuleName(imagesModuleName);
		if(StringUtils.isNotEmpty(imagesSort)) {
			param.setImagesSort(Integer.valueOf(imagesSort));
		}
		param.setImagesStatus("1");
		checkParam(param);
		if(file != null && file.getSize()>0) {
			checkImages(file);
			param = imagesInfoService.copyImages(param, file);
		}
		param.setCreateTime(new Date());

		if(StringUtils.isNotEmpty(note)) {
			param.setNote(note);
		}
		
		imagesInfoService.insertSelective(param);

		return CommonUtils.result(1, "上传成功");
	}

	private void checkParam(ImagesInfo param) {
		if (param == null) {
			throw new CommException(CommErrors.ADMIN_PARAMS_NULL);
		}

		if (StringUtils.isBlank(param.getImagesModuleCode())) {
			throw new CommException("文件所属模块编码不能为空!");
		}

		if (StringUtils.isBlank(param.getImagesModuleName())) {
			throw new CommException("文件所属模块名称不能为空!");
		}
	}
	
	private void checkImages(MultipartFile file) {
		String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        suffix = suffix.toLowerCase();
        if(suffix.equals(".jpg") || suffix.equals(".jpeg") || suffix.equals(".png") || suffix.equals(".gif")){
        	
        }else {
			throw new CommException(CommErrors.IMAGES_ERROR);
        }
		
	}

	
	  
	  @RequestMapping(value = "/update")
	  @ResponseBody
	  public Map<String, Object> saveInfo(@RequestBody ImagesInfo param){
		  if (StringUtils.isBlank(param)) {
			  throw new CommException(CommErrors.ADMIN_PARAMS_NULL);
		  }
		  
		  if(StringUtils.isBlank(param.getId())) {
			  throw new CommException(CommErrors.ADMIN_PARAMS_NULL);
		  }

		  try {
			ImagesInfo vo = imagesInfoService.selectByPrimaryKey(param.getId().intValue());
			if(vo!= null) {
				imagesInfoService.updateByPrimaryKeySelective(vo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
		  
			return CommonUtils.result(1, "修改成功");
	 }


	
	  
	  @RequestMapping(value = "/find/{id}")
	  @ResponseBody
	  public ImagesInfo findInfo(@PathVariable("id") String id){
		  if (StringUtils.isBlank(id)) {
			  throw new CommException(CommErrors.ADMIN_PARAMS_NULL);
		  }
		  
		  if(!StringUtils.isNumeric1(id)) {
			  throw new CommException(CommErrors.MON_ERROR);
		  }
		  ImagesInfo vo = null;
		try {
			int sid = Integer.parseInt(id);
			vo = imagesInfoService.selectByPrimaryKey(sid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		  }
			  return vo;
	 }

	  
		 
	  	 /**
		 * 删除文件
		 **/
		  @RequestMapping(value = "/delete/{id}")
		  @ResponseBody
		  public Map<String, Object> deleteAttach(@PathVariable("id") String id){
			  if (StringUtils.isBlank(id)) {
				  throw new CommException(CommErrors.ADMIN_PARAMS_NULL);
			  }
			  
			  if(!StringUtils.isNumber(id)) {
				  throw new CommException(CommErrors.MON_ERROR);
			  }

			  try {
				  int sid = Integer.parseInt(id);
				  imagesInfoService.deleteByPrimaryKey(sid);
			  } catch (SQLException e) {
				  // TODO Auto-generated catch block
				  e.printStackTrace();
			  }
			  return  CommonUtils.result(1, "删除成功");
		  }
			 
			  

		    /**
		     * @Title: 文件分页查询
		     * @author YYL 2020年6月12日 下午12:19:45
		     * @param param
		     * @return
		     */
		  
		    @RequestMapping(value = "/selectByPage")
		    @ResponseBody
		    public Map<String, Object> selectByPage(@RequestBody(required = false) ImagesInfo param){
		    	Map<String,Object> returnMap = new HashMap<>();
		    	if(param == null) {
		    		param = new ImagesInfo();
		    	}
		    	Page page = imagesInfoService.selectByPage(param);
		    	
		    	returnMap.put("imagesName", param.getImagesName());
		    	returnMap.put("imagesModuleCode", param.getImagesModuleCode());
		    	returnMap.put("imagesModuleName", param.getImagesModuleName());
		    	returnMap.put("beginDate", param.getBeginDate());
		    	returnMap.put("endDate", param.getEndDate());
		    	returnMap.put("page", page);
				return returnMap;
		    }


		    /**
		     * @Title: 文件数据查询
		     * @author YYL 2020年6月12日 下午12:19:45
		     * @param param
		     * @return
		     */
		    @RequestMapping(value = "/selectByList")
		    @ResponseBody
		    public Map<String, Object> selectByList(@RequestBody(required = false) ImagesInfo param){
		    	Map<String,Object> returnMap = new HashMap<>();
		    	if(param == null) {
		    		param = new ImagesInfo();
		    	}
		    	List<ImagesInfo> list = imagesInfoService.selectByList(param);
		    	returnMap.put("imagesModuleCode", param.getImagesModuleCode());
		    	returnMap.put("list", list);
				return returnMap;
		    }
		    
		    
		    
	  
}
