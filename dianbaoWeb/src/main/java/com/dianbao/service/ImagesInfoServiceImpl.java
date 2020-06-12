package com.dianbao.service;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dianbao.dao.ImagesInfoDao;
import com.dianbao.domain.ImagesInfo;
import com.dianbao.util.Page;

@Service
public class ImagesInfoServiceImpl implements ImagesInfoService {

	@Value("${images.address}")
	private String imagesAddress;
	
	@Autowired
	private ImagesInfoDao imagesInfoDao;
	
	@Override
	public void insertSelective(ImagesInfo record) throws SQLException {
		// TODO Auto-generated method stub
		imagesInfoDao.insertSelective(record);
	}

	@Override
	public ImagesInfo selectByPrimaryKey(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return imagesInfoDao.selectByPrimaryKey(id);
	}

	@Override
	public void updateByPrimaryKeySelective(ImagesInfo record) throws SQLException {
		// TODO Auto-generated method stub
		imagesInfoDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public Page selectByPage(ImagesInfo param) {

        Page page = new Page();
        page.setCount(imagesInfoDao.countImagesByPage(param));
        page.setPageNo(param.getPageNo());
        if (page.getCount() > 0) {
        	page.setList(imagesInfoDao.selectImagesByPage(param));
        }else {
            page.setList(Page.EMPTY_OBJECT_ARRAY);
        }
        
		return page;
	}
	
	
	
	

	@Override
	public void deleteByPrimaryKey(java.lang.Integer id) throws SQLException {
		// TODO Auto-generated method stub
		imagesInfoDao.deleteByPrimaryKey(id);
	}
	
	@Override
	public ImagesInfo copyImages(ImagesInfo param,MultipartFile file)throws Exception{
		String imagesName = param.getImagesName();
		String moduleCode = param.getImagesModuleCode()+"/";
		String filename = null;// 定义文件名
		String contentType = file.getContentType(); // 获取文件类型（后缀）
		// 因为获取的后缀是XXXX/xxx形式
		contentType = contentType.substring(contentType.indexOf("/") + 1);
		filename = imagesName + "." + contentType;
		String fileAddress = imagesAddress +"/"+moduleCode + filename;
		file.transferTo(new File(fileAddress));// 保存图片
		param.setImagesAdress(fileAddress);
		param.setImagesUrl("/images/"+moduleCode + filename);
		return param;
	}

	@Override
	public List<ImagesInfo> selectByList(ImagesInfo param) {
		// TODO Auto-generated method stub
		return imagesInfoDao.selectImagesByPage(param);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
