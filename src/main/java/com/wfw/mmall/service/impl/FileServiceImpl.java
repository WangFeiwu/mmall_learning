package com.wfw.mmall.service.impl;

import com.google.common.collect.Lists;
import com.wfw.mmall.service.IFileService;
import com.wfw.mmall.util.FTPUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author wfw
 * @date 2018/8/29 15:09
 */
@Service("iFileService")
public class FileServiceImpl implements IFileService {
    private static final Logger logger= LoggerFactory.getLogger(FileServiceImpl.class);

    @Override
    public String upload(MultipartFile file, String path) {
        String fileName=file.getOriginalFilename();
        String fileExtensionName=fileName.substring(fileName.lastIndexOf(".")+1);
        String uploadFileName= UUID.randomUUID().toString()+"."+fileExtensionName;
        logger.info("开始上传文件,上传文件的文件名:{},上传的路径:{},新文件名:{}",fileName,path,uploadFileName);

        File fileDir=new File(path);
        if (!fileDir.exists()){
            fileDir.setWritable(true);
            fileDir.mkdirs();
        }
        File targetFile=new File(path,uploadFileName);
        try {
            file.transferTo(targetFile);//文件已上传成功

            /*
            FTPUtil.uploadFile(Lists.newArrayList(targetFile));//将targetFile上传到ftp服务器
            targetFile.delete();//上传完之后，删除upload文件夹下的文件
            */

        } catch (IOException e) {
            logger.error("上传文件异常",e);
            return null;
        }
        return targetFile.getName();
    }
}