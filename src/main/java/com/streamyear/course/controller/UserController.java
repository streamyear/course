package com.streamyear.course.controller;

import com.streamyear.course.entity.User;
import com.streamyear.course.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用户的Controller
 */
@RestController
@RequestMapping("user")
public class UserController {

    /**
     * 用户的Service
     */
    @Autowired
    private UserService userService;

    /**
     * 分页获取所有的用户信息
     * @param pageNum 当前页
     * @param pageSize 每页展示的数量
     * @return 用户的list
     */
    @RequestMapping("/listAllUserInfo")
    public List<User> listAllUserInfo(Integer pageNum, Integer pageSize){
        List<User> result = userService.listAllRecord(pageNum, pageSize);
        return result;
    }
}
