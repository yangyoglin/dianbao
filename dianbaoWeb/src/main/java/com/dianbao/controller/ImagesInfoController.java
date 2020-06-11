package com.dianbao.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.dianbao.domain.ImagesInfo;
import com.dianbao.service.ImagesInfoService;
import com.dianbao.util.CommErrors;
import com.dianbao.util.CommException;
import com.dianbao.util.CommonUtils;
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
			@RequestParam("imagesModuleCode") String imagesModuleCode,@RequestParam("imagesSort") int imagesSort,
			@RequestParam("imagesModuleName") String imagesModuleName, MultipartFile file, HttpServletRequest request)
			throws Exception {

		if(file == null){
            throw new CommException(CommErrors.ADMIN_PARAMS_NULL);
		}
		ImagesInfo param = new ImagesInfo();
		param.setImagesName(imagesName);
		param.setImagesModuleCode(imagesModuleCode);
		param.setImagesModuleName(imagesModuleName);
		param.setImagesSort(imagesSort);
		param.setImagesStatus("1");
		checkParam(param);
		param = imagesInfoService.copyImages(param, file);
		param.setCreateTime(new Date());
		
		imagesInfoService.insertSelective(param);

		return CommonUtils.result(1, "上传成功");
	}

	private void checkParam(ImagesInfo param) {
		if (param == null) {
            throw new CommException(CommErrors.ADMIN_PARAMS_NULL);
        }

        if(StringUtils.isBlank(param.getImagesModuleCode())){
            throw new CommException("文件所属模块编码不能为空!");
        }

        if(StringUtils.isBlank(param.getImagesModuleName())){
            throw new CommException("文件所属模块名称不能为空!");
        }
	}
	
	
	/*
	 * 
	 * @RequestMapping(value = "/{id}")
	 * 
	 * @ResponseBody public BoroughInfo info(@PathVariable("id") String id){
	 * BoroughInfo vo = boroughInfoService.selectByPrimaryKey(id);
	 * vo.setBoroughChannels(boroughInfoService.findByChannels(vo)); // 佣金方案列表
	 * vo.setCommissionList(boroughInfoService.selectCommissionsByBoroughInfoId(id))
	 * ; return vo; }
	 */
	/*
	
	*//**
		 * 删除一个楼盘资料
		 **//*
			 * @RequestMapping(value = "/attach/delete")
			 * 
			 * @ResponseBody public Map<String, Object> deleteAttach(@RequestBody
			 * BoroughInfoAttach param){
			 * boroughInfoService.deleteBoroughInfoAttachById(param.getId()); return
			 * CommonUtils.result(1, "删除成功"); }
			 */

}
