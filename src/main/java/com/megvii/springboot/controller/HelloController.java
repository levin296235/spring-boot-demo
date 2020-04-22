package com.megvii.springboot.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Value("${custom.name}")
    private String name;

    @GetMapping("/hello")
    public String sayHello(){
        return "hello,springboot2.0!";
    }

}
