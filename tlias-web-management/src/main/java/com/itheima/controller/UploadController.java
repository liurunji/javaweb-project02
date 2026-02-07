package com.itheima.controller;

import com.itheima.pojo.Result;
import com.itheima.utils.AliyunOSSOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {
    @Autowired
    AliyunOSSOperator aliyunOSSOperator;
    /*
    //存储到本地的方法
    @PostMapping("/upload")
    public Result upload(String name, Integer age, MultipartFile file) throws IOException {  //这里的参数要和前端传过来的名保持一致
        log.info("接收到的参数：{}，{}，{}", name, age, file);
        String originalFilename = file.getOriginalFilename();  //获取原始文件名
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));  //扩展名：将原始的文件名的扩展名找到最后一个.进行截断得到
        String fileName = UUID.randomUUID() + extension;
        file.transferTo(new File("F:\\project_images\\" + fileName));
        return Result.success();
    }
     */

    /*
    存储到阿里云
     */
    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws Exception {
        log.info("接收到的文件对象：{}",file);
        //将文件交给OSS管理
        String url = aliyunOSSOperator.upload(file.getBytes(), file.getOriginalFilename());
        log.info("文件上传的URL：{}",url);
        return Result.success(url);
    }

}
