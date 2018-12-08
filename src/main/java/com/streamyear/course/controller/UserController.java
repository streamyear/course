package com.streamyear.course.controller;

import com.streamyear.course.common.excel.ExcelUtil;
import com.streamyear.course.entity.User;
import com.streamyear.course.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    /**
     * 用excel表导出所有的用户信息
     * @return
     */
    @RequestMapping("/export/allUser")
    public void exportAllUserMessage(HttpServletResponse response){

        List<User> list = userService.listAllRecord(1, 200000);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYYMMddHHmmss");
        String fileName = "用户信息-" + LocalDateTime.now().format(formatter);
        String sheetName = "Sheet1";

        ExcelUtil.writeExcel(response, list, fileName, sheetName, new User());
    }

}
