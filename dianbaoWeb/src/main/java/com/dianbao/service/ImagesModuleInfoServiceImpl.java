package com.dianbao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dianbao.dao.ImagesModuleInfoDao;
import com.dianbao.domain.ImagesModuleInfo;
import com.dianbao.util.Page;

@Service
public class ImagesModuleInfoServiceImpl implements ImagesModuleInfoService {

	
	@Autowired
	private ImagesModuleInfoDao moduleDao;
	
	
	
	@Override
	public Page selectByPage(ImagesModuleInfo param) {
		// TODO Auto-generated method stub
        Page page = new Page();
        page.setCount(moduleDao.countByPage(param));
        page.setPageNo(param.getPageNo());
        if (page.getCount() > 0) {
        	page.setList(moduleDao.selectByPage(param));
        }else {
            page.setList(Page.EMPTY_OBJECT_ARRAY);
        }
        
		return page;
	}



	@Override
	public List<ImagesModuleInfo> findByList(ImagesModuleInfo param) {
		// TODO Auto-generated method stub
		return moduleDao.selectByPage(param);
	}

	
	
	
	
	
}
