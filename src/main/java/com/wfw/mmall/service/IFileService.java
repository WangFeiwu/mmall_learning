package com.wfw.mmall.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author wfw
 * @date 2018/8/29 15:09
 */
public interface IFileService {

    /**
     * 上传文件
     * @param file
     * @param path
     * @return
     */
    String upload(MultipartFile file,String path);


}
