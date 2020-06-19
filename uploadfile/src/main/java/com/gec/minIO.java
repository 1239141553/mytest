package com.gec;

import io.minio.MinioClient;
import io.minio.policy.PolicyType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.text.SimpleDateFormat;
import java.util.Date;



@RestController
public class minIO {

    @Value("${minio.endpoint}")
    private String endpoint;
    @Value("${minio.bucketName}")
    private String bucketName;
    @Value("${minio.accessKey}")
    private String accessKey;
    @Value("${minio.secretKey}")
    private String secretKey;

    private Logger logger = LoggerFactory.getLogger(getClass());


    @PostMapping("/uploadFile")
    @CrossOrigin("*")
    public RespBean uploadFile(@RequestParam(value="file",required=false)  MultipartFile file){
        try {
            MinioClient minioClient = new MinioClient(endpoint,accessKey,secretKey);
            boolean isExist = minioClient.bucketExists(bucketName);
            if (!isExist){
                minioClient.makeBucket(bucketName);
                minioClient.setBucketPolicy(bucketName, ".", PolicyType.READ_ONLY );
            }
            String fileName = file.getOriginalFilename();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String objName = sdf.format(new Date())+"/"+fileName;
            //把存储对象上传至存储桶中
            minioClient.putObject(bucketName, objName, file.getInputStream(), file.getContentType());
            String ObjectUrl = minioClient.getObjectUrl(bucketName, objName);
            logger.info("文件上传成功");
            logger.info("文件路径"+ObjectUrl);
            return RespBean.ok(ObjectUrl);
        } catch (Exception e) {
            e.printStackTrace();
            return RespBean.error("文件上传失败");
        }
    }
}
