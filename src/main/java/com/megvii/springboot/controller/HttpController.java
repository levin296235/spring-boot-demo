package com.megvii.springboot.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用于不同权限ajax请求测试
 */
@RestController
@RequestMapping("/test")
public class HttpController {

    @PostMapping("/public")
    public JSONObject doPublicHandler(Long id) {
        JSONObject json = new JSONObject();
        json.put("code", 200);
        json.put("msg", "请求成功" + id);
        return json;
    }

    @PostMapping("/vip")
    public JSONObject doVipHandler(Long id) {
        JSONObject json = new JSONObject();
        json.put("code", 200);
        json.put("msg", "请求成功" + id);
        return json;
    }
}