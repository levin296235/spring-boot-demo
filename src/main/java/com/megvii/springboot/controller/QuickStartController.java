package com.megvii.springboot.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
public class QuickStartController {
    @Value("${person.name}")
    private String name;
    @Value("${person.age}")
    private Integer age;

    @RequestMapping("/quick")
    @ResponseBody
    public String quick(){
        return "springboot 访问成功! name="+name+",age="+age;
    }

    /* 对应 http://127.0.0.1:7091/sayHi/tom */
    @RequestMapping("/sayHi/{name}")
    public String sayHi(@PathVariable("name")String name ){
        return "hi!"+ name;
    }
    /*对应 http://127.0.0.1:7091/sayHello?name=tom */
    @RequestMapping("/sayHello")
    public String sayHello(@RequestParam String name){
        return "你好!"+name;
    }
    /* 对应POST http://127.0.0.1:7091/sayGood 消息体为tom */
    public String sayGood(@RequestBody(required = false) String name){
        return "find!"+name;
    }

    @RequestMapping("/preUser")
    @CrossOrigin("http://localhost:8080")
    public Map<String,String> preUser(){
        Map<String,String> map = new HashMap<String,String>();
        map.put("preUser","success");
        return map;
    }
    //局部跨域（手工设置响应头）
    @RequestMapping("/hello")
    @ResponseBody
    public String index(HttpServletResponse response){
        response.addHeader("Access-Control-Allow-Origin", "http://localhost:8080");
        return "Hello World";
    }
}
