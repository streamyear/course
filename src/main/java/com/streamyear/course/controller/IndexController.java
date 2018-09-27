package com.streamyear.course.controller;

import com.streamyear.course.common.service.HttpAPIService;
import com.streamyear.course.common.service.RedisService;
import com.streamyear.course.entity.User;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 根目录的Controller
 */
@RestController
public class IndexController {
    @Autowired
    private RedisService redisService;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private HttpAPIService httpAPIService;

    @RequestMapping("/")
    public String index(){
        return "欢迎您,访问SpringBoot项目!";
    }

    /**
     * 测试日期
     * @param joinDay
     * @return
     */
    @RequestMapping("date")
    public Date testDate(Date joinDay){
        return joinDay;
    }

    /**
     * 测试Redis
     */
    @RequestMapping("redis")
    public String testRedis(){
        redisService.set("name", "StreamYear");
        return "ok";
    }

    /**
     * 测试mq
     * @return
     * @throws Exception
     */
    @RequestMapping("mq")
    public String mqTest() throws Exception {
        amqpTemplate.convertAndSend("sendSMS", "manager", "StreamYearMq内容");
        return "ok";
    }
    
    @RequestMapping("testHttpClient")
    public User testHttpClient(){
        User user = new User();
        user.setAge(18);
        user.setName("StreamYear");
        return user;
    }

    @RequestMapping("httpClient")
    public String httpClient() throws Exception {
        String result = httpAPIService.doGet("http://localhost:9090/testHttpClient");
        return result;
    }

}
