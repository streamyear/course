package com.streamyear.course.common.util;

import com.aliyun.oss.OSSClient;
import com.streamyear.course.config.AliOSSConstantProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.UUID;

public class AliyunOSSUtil {
    private static final Logger logger = LoggerFactory.getLogger(AliyunOSSUtil.class);

    /**
     * 保存文件
     * @param pathName 文件的路径+名字
     * @param is 文件内容
     * @return
     */
    public static String save(String pathName, InputStream is) {
        try {
            OSSClient ossClient = new OSSClient(AliOSSConstantProperties.ENDPOINT, AliOSSConstantProperties.ACCESSKEYID,
                    AliOSSConstantProperties.ACCESSKEYSECRET);
            ossClient.putObject(AliOSSConstantProperties.BUCKETNAME, pathName, is);
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
    public static String get(String pahtName){
        OSSClient ossClient = new OSSClient(AliOSSConstantProperties.ENDPOINT, AliOSSConstantProperties.ACCESSKEYID,
                AliOSSConstantProperties.ACCESSKEYSECRET);
        // 设置URL过期时间为1小时
        Date expiration = new Date(new Date().getTime() + 3600 * 1000);
        // 生成URL
        URL url = ossClient.generatePresignedUrl(AliOSSConstantProperties.BUCKETNAME, pahtName, expiration);
        ossClient.shutdown();
        return url.toString();
    }

    public static void main(String[] args) throws Exception {
        String fileName = UUID.randomUUID().toString().replace("-","") + ".jpg";
        String key = "event/compensate/" + fileName;
        InputStream in = new FileInputStream("C:\\Users\\Beck\\Desktop\\timg.jpg");
        String name = save(key, in);
        System.out.println("上传图片的结果: " + name);
    }
}
