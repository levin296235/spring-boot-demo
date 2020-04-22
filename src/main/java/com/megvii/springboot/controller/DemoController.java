package com.megvii.springboot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequestMapping("/demo")
public class DemoController {
    @GetMapping
    @ResponseBody
    public String index() {
        return "Welcome to learn Spring Security!";
    }
}
