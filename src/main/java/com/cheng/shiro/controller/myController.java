package com.cheng.shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping(value = "/testController")
public class myController {

    @RequestMapping(value = "/add")
    public String add(){
        return "/user/add";
    }

    @RequestMapping(value = "/update")
    public String update(){
        return "/user/update";
    }

    @RequestMapping(value = "/toLogin")
    public String toLogin(){
        return "/user/login";
    }

    @RequestMapping(value = "/login")
    public String login(String user, String pass, Map<String,String> map){//自动将同name的属性赋值不需要@RequestParam（value）
        //使用shiro编写认证操作
        //获取subject
        Subject subject = SecurityUtils.getSubject();//该方法shrio提供的获取shiro
        //封装用户数据
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user, pass);
        //执行登录方法
        try {
            subject.login(usernamePasswordToken);//执行login方法，shrio讲密码认证交给配置的MyRealm，不出现异常，则登录成功，出现异常则失败
        }catch (UnknownAccountException e){
            //shrio提供的分用户名不存在，和密码错误的异常
            //不存在UnknownAccountException
            map.put("msg","用户名不存在！");
            return "/user/login";//如果使用重定向或者跳到接口的话数据就无法携带，所以直接跳页面
        }catch (IncorrectCredentialsException e){
            map.put("msg","密码错误!");
            return "/user/login";
        }
        //登录成功，无需携带数据，重定向到index---如果跳到接口，则Redirect:/接口名---如果转发到页面直接页面地址
        return "redirect:/testController/index";
    }

    @RequestMapping(value = "/index")
    public String index(){
        return "index";
    }

    @RequestMapping(value = "/uoAuth")
    public String uoAuth(){
        return "/user/uoAuth";
    }
}