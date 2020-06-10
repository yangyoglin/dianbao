package com.dianbao.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.dianbao.domain.ImagesInfo;

@Service
public class ImagesInfoServiceImpl implements ImagesInfoService {

	@Override
	public void insertSelective(ImagesInfo record) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List selectByExample(ImagesInfo example) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ImagesInfo selectByPrimaryKey(Long id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(ImagesInfo record) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

}
