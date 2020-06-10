package com.dianbao.service;

import org.springframework.web.multipart.MultipartFile;


public interface FileService {

    void upLoadFile(MultipartFile upload);
}
