package com.streamyear.course.common.util;

import com.aliyun.oss.OSSClient;
import com.streamyear.course.config.AliOSSConstantProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.net.URL;
import java.util.Date;

@Service
public class AliyunOSSUtil {
    @Autowired
    private AliOSSConstantProperties aliOSSConstantProperties;

    private static final Logger logger = LoggerFactory.getLogger(AliyunOSSUtil.class);

    /**
     * 保存文件
     * @param pathName 文件的路径+名字
     * @param is 文件内容
     * @return
     */
    public String save(String pathName, InputStream is) {
        try {
            OSSClient ossClient = new OSSClient(aliOSSConstantProperties.ENDPOINT, aliOSSConstantProperties.ACCESSKEYID,
                    aliOSSConstantProperties.ACCESSKEYSECRET);
            ossClient.putObject(aliOSSConstantProperties.BUCKETNAME, pathName, is);
            ossClient.shutdown();
            return pathName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取访问文件的url
     * @param pahtName 路径+名字
     * @return
     */
    public String get(String pahtName){
        OSSClient ossClient = new OSSClient(aliOSSConstantProperties.ENDPOINT, aliOSSConstantProperties.ACCESSKEYID,
                aliOSSConstantProperties.ACCESSKEYSECRET);
        // 设置URL过期时间为1小时
        Date expiration = new Date(new Date().getTime() + 3600 * 1000);
        // 生成URL
        URL url = ossClient.generatePresignedUrl(aliOSSConstantProperties.BUCKETNAME, pahtName, expiration);
        ossClient.shutdown();
        return url.toString();
    }
}
