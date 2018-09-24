package com.streamyear.course.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.streamyear.course.entity.User;
import com.streamyear.course.mapper.UserMapper;
import com.streamyear.course.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户的UserService实现类
 */
@Service
public class UserServiceImpl implements UserService {
    /**
     * 用户的dao
     */
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> listAllRecord(Integer pageNum, Integer pageSize) {
        pageNum = pageNum == null ? 1 : pageNum;
        pageSize = pageSize == null ? 10 : pageSize;
        PageHelper.startPage(pageNum, pageSize);
        List<User> allRecord = userMapper.listAllRecord();
        PageInfo<User> pageInfo = new PageInfo<>(allRecord);
        return pageInfo.getList();
    }
}
