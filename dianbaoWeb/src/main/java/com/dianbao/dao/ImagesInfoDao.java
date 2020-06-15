package com.dianbao.dao;

import java.sql.SQLException;
import java.util.List;

import com.dianbao.domain.ImagesInfo;

public interface ImagesInfoDao {

	
	void insertSelective(ImagesInfo record) throws SQLException;
	
    void updateByPrimaryKeySelective(ImagesInfo record) throws SQLException;

    void deleteByPrimaryKey(Integer id) throws SQLException;
	
    ImagesInfo selectByPrimaryKey(java.lang.Integer id) throws SQLException;
    
    List<ImagesInfo> selectImagesByPage(ImagesInfo param);

	int countImagesByPage(ImagesInfo param);
}
