package com.cheng.shiro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/testController")
public class myController {

    @RequestMapping(value = "/add")
    public String add(){
        return "/user/add.html";
    }

    @RequestMapping(value = "/update")
    public String update(){
        return "/user/update";
    }

    @RequestMapping(value = "/toLogin")
    public String toLogin(){
        return "/user/login";
    }
}
