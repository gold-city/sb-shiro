package com.cheng.shiro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping(value = "/testController")
public class myController {

    @ResponseBody
    @RequestMapping(value = "/test")
    public String test(){
        return "test";
    }

    @RequestMapping(value = "/hello")
    public String test1(Map<String,Object> map){
        map.put("hello","hello");
        return "index";
    }
}
