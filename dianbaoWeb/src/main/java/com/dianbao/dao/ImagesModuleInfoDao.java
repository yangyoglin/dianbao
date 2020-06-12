package com.dianbao.dao;

import java.sql.SQLException;
import java.util.List;

import com.dianbao.domain.ImagesModuleInfo;

public interface ImagesModuleInfoDao {
	

	void deleteByPrimaryKey(java.lang.Integer id) throws SQLException;
    
    void insertSelective(ImagesModuleInfo record) throws SQLException;
    
    void updateByPrimaryKeySelective(ImagesModuleInfo record) throws SQLException;

    ImagesModuleInfo selectByPrimaryKey(java.lang.Integer id) throws SQLException;

    List<ImagesModuleInfo> selectByPage(ImagesModuleInfo param);

	int countByPage(ImagesModuleInfo param);
}
