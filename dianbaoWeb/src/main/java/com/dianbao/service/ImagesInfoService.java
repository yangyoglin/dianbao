package com.dianbao.service;

import java.sql.SQLException;
import java.util.List;

import com.dianbao.domain.ImagesInfo;

public interface ImagesInfoService {

    void insertSelective(ImagesInfo record) throws SQLException;

    List selectByExample(ImagesInfo example) throws SQLException;

    ImagesInfo selectByPrimaryKey(java.lang.Long id) throws SQLException;

    int updateByPrimaryKeySelective(ImagesInfo record) throws SQLException;
    
}
