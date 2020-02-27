package com.cheng.shiro.service.impl;

import com.cheng.shiro.entity.User;
import com.cheng.shiro.mapper.UserMapper;
import com.cheng.shiro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: Cheng
 * Date: 2020/2/26
 * Time: 23:08
 * Description: No Description
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;//该mapper在spring中通过配置进行全体mapper扫描，在springboot中需要在springboot主类中加入mapper扫描注解
    @Override
    public User queryUserByName(String name) {
        return userMapper.queryUserByName(name);
    }

    @Override
    public User queryUserById(Integer id) {
        return userMapper.queryUserById(id);
    }
}
