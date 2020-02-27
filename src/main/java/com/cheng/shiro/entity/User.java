package com.cheng.shiro.entity;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * User: Cheng
 * Date: 2020/2/24
 * Time: 23:12
 * Description: No Description
 *
 *
 */
@Data
public class User {
    private Integer id;
    private String name;
    private String pass;
    private String perms;
}
