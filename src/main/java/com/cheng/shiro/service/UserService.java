package com.cheng.shiro.service;

import com.cheng.shiro.entity.User;

/**
 * Created with IntelliJ IDEA.
 * User: Cheng
 * Date: 2020/2/26
 * Time: 23:08
 * Description: No Description
 */
public interface UserService {
    User queryUserByName(String name);
    User queryUserById(Integer id);
}
