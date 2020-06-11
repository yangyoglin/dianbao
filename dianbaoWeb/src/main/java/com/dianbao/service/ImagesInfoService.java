package com.dianbao.service;

import java.sql.SQLException;

import org.springframework.web.multipart.MultipartFile;

import com.dianbao.domain.ImagesInfo;
import com.dianbao.util.Page;

public interface ImagesInfoService {

	void deleteByPrimaryKey(java.lang.Integer id) throws SQLException;
	
    void insertSelective(ImagesInfo record) throws SQLException;

    ImagesInfo selectByPrimaryKey(java.lang.Integer id) throws SQLException;

    void updateByPrimaryKeySelective(ImagesInfo record) throws SQLException;
    
    Page selectByPage(ImagesInfo param);
    
    
    /**
     * @Title: 拷贝文件至指定目录
     * @author YYL 2020年6月11日 下午5:03:07
     * @param param
     * @param file
     * @return ImagesInfo       加入文件保存地址
     * @throws Exception
     */
    ImagesInfo copyImages(ImagesInfo param,MultipartFile file)throws Exception;
    
}
