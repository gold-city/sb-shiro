package com.cheng.shiro.mapper;

import com.cheng.shiro.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created with IntelliJ IDEA.
 * User: Cheng
 * Date: 2020/2/26
 * Time: 22:54
 * Description: No Description
 */
@Mapper
public interface UserMapper {
    User queryUserByName(String name);
    User queryUserById(Integer id);
}
