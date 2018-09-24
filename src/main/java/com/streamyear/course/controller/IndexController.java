package com.streamyear.course.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 根目录的Controller
 */
@RestController
public class IndexController {

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

}
