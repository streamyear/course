package com.streamyear.course.service;

import com.streamyear.course.entity.User;

import java.util.List;

/**
 * 用户的Service
 */
public interface UserService {
    /**
     * 获取所有的用户记录
     * @param pageNum 当前页
     * @param pageSize 每页展示的大小
     * @return
     */
    List<User> listAllRecord(Integer pageNum, Integer pageSize);
}
