package com.dianbao.service;

import java.util.List;

import com.dianbao.domain.ImagesModuleInfo;
import com.dianbao.util.Page;

public interface ImagesModuleInfoService {


    /**
     * @Title: 页面分页查询
     * @author YYL 2020年6月12日 下午12:17:08
     * @Description: TODO
     * @param param
     * @return
     */
    Page selectByPage(ImagesModuleInfo param);
    
    List<ImagesModuleInfo> findByList(ImagesModuleInfo param);
}
