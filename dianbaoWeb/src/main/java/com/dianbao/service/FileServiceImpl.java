package com.dianbao.service;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl implements FileService {

    private String filePath="D:/img/"; //定义上传文件的存放位置
    
    
    @Override
    public void upLoadFile(MultipartFile upload) {
    	

        String fileName = upload.getOriginalFilename();  //获取上传文件的名字
        //判断文件夹是否存在,不存在则创建
        File file=new File(filePath);

        if(!file.exists()){
            file.mkdirs();
        }

        String newFilePath=filePath+fileName; //新文件的路径

        try {
            upload.transferTo(new File(newFilePath));  //将传来的文件写入新建的文件

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
