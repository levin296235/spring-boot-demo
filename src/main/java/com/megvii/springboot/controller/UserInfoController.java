package com.megvii.springboot.controller;

import com.megvii.springboot.mapper.UserInfoMapper;
import com.megvii.springboot.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserInfoController {

    private final UserInfoMapper userInfoMapper;

    @Autowired
    public UserInfoController(UserInfoMapper userInfoMapper) {
        this.userInfoMapper = userInfoMapper;
    }

    @RequestMapping("/queryUser")
    @ResponseBody
    public List<UserInfo> queryUser(){
        List<UserInfo> users = userInfoMapper.selectUserList();
        return users;
    }
}
