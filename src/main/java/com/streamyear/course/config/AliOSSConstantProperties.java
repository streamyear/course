package com.streamyear.course.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 阿里云oss上传的配置文件
 */
@Component
@PropertySource(value={"classpath:application-oss.properties"},encoding = "utf-8")
public class AliOSSConstantProperties{

    @Value("${accessKeyId}")
    public String ACCESSKEYID;

    @Value("${accessKeySecret}")
    public String ACCESSKEYSECRET;

    @Value("${endpoint}")
    public String ENDPOINT;

    @Value("${bucketName}")
    public String BUCKETNAME;
}
