package com.dianbao.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dianbao.domain.ImagesModuleInfo;
import com.dianbao.service.ImagesModuleInfoService;
import com.dianbao.util.Page;

/**
 * 文件分类接口管理
 * 
 * @author YangYongLin
 *
 */
@Controller
@RequestMapping("/imagesmodule")
public class ImagesModuleInfoController {

	@Autowired
	private ImagesModuleInfoService imagesModuleInfoService;

	/**
	 * @Title: 文件类型数据查询
	 * @author YYL 2020年6月12日 下午12:19:45
	 * @param param
	 * @return
	 */
		  @RequestMapping("/findByList")
		  @ResponseBody
		  public Map<String, Object> findByList(@RequestBody(required = false) ImagesModuleInfo param){
			  if(param == null) {
					param = new ImagesModuleInfo();
				}
			  Map<String,Object> returnMap = new HashMap<>(); 
			  List<ImagesModuleInfo> list = imagesModuleInfoService.findByList(param);
			  
			  returnMap.put("moduleCode", param.getModuleCode());
			  returnMap.put("moduleName", param.getModuleName());
			  returnMap.put("moduleParentCode", param.getModuleParentCode());
			  returnMap.put("moduleParentName", param.getModuleParentName());
			  returnMap.put("list", list); 
			  return returnMap; 
		  }
		 

	/**
	 * @Title: 文件类型数据查询
	 * @author YYL 2020年6月12日 下午12:19:45
	 * @param param
	 * @return
	 */
	/*
	 * @RequestMapping(value = "/findByList", method = RequestMethod.POST)
	 * 
	 * @ResponseBody public Map<String, Object> findByList(@RequestBody
	 * ImagesModuleInfo param) { Map<String, Object> returnMap = new HashMap<>();
	 * List<ImagesModuleInfo> list = imagesModuleInfoService.findByList(param);
	 * 
	 * returnMap.put("moduleCode", param.getModuleCode());
	 * returnMap.put("moduleName", param.getModuleName());
	 * returnMap.put("moduleParentCode", param.getModuleParentCode());
	 * returnMap.put("moduleParentName", param.getModuleParentName());
	 * returnMap.put("list", list); return returnMap; }
	 */

	/**
	 * @Title: 文件类型分页查询
	 * @author YYL 2020年6月12日 下午12:19:45
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/selectByPage")
	@ResponseBody
	public Map<String, Object> selectByPage(@RequestBody(required = false) ImagesModuleInfo param) {
		if(param == null) {
			param = new ImagesModuleInfo();
		}
		Map<String, Object> returnMap = new HashMap<>();
		Page page = imagesModuleInfoService.selectByPage(param);

		returnMap.put("page", page);
		return returnMap;
	}

}
