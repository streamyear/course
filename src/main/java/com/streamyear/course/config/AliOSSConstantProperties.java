package com.streamyear.course.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 阿里云oss上传的配置文件
 */
@Component
@PropertySource(value = "classpath:application-oss.properties")
public class AliOSSConstantProperties implements InitializingBean {

    @Value("accessKeyId")
    private String accessKeyId;

    @Value("accessKeySecret")
    private String accessKeySecret;

    @Value("endpoint")
    private String endpoint;

    @Value("bucketName")
    private String bucketName;

    public static String ACCESSKEYID;

    public static String ACCESSKEYSECRET;

    public static String ENDPOINT;

    public static String BUCKETNAME;

    @Override
    public void afterPropertiesSet() throws Exception {
        ACCESSKEYID = accessKeyId;
        ACCESSKEYSECRET = accessKeySecret;
        ENDPOINT = endpoint;
        BUCKETNAME = bucketName;
    }
}
