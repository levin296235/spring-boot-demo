package com.megvii.springboot.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @Value("${custom.name}")
    private String name;

    @GetMapping("/sayHello")
    public String sayHello(){
//        int i = 10/0;
        return "hello," + name;
    }
}
