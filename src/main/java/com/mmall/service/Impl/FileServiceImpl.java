package com.mmall.service.Impl;

import com.google.common.collect.Lists;
import com.mmall.service.IFileService;
import com.mmall.util.FTPUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service("iFileService")
public class FileServiceImpl implements IFileService {

    private Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    @Override         //SpringMVC的文件格式
    public String upload(MultipartFile file, String path) {
        String filename = file.getOriginalFilename();
        //文件拓展名
        String fileExtensionName = filename.substring(filename.lastIndexOf(".") + 1);
        String uploadFileName = UUID.randomUUID() + "." + fileExtensionName;
                                                                           // 参数个数与字符串中的大括号数对应
        logger.info("开始上传文件，上传文件的文件名：{}，上传的路径：{}，新文件名：{}", filename, path, uploadFileName);
        File fileDir = new File(path);
        if (!fileDir.exists()) {
            fileDir.setWritable(true);
            fileDir.mkdirs();
        }
        File targetFile = new File(path, uploadFileName);
        try {
            //上传文件
            file.transferTo(targetFile);
            FTPUtil.uploadFile(Lists.newArrayList(targetFile));
            targetFile.delete();
        } catch (IOException e) {
            logger.error("上传文件异常", e);
            return null;
        }
        return targetFile.getName();
    }
}
