package com.megvii.springboot.service;

import com.megvii.springboot.mapper.UserInfoMapper;
import com.megvii.springboot.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    public List<UserInfo> getUsers() {
        return userInfoMapper.selectUserList();
    }

    public UserInfo findUserById(int id) {
        return userInfoMapper.selectUserById(id);
    }

    public boolean updateUserById(UserInfo user) {
        userInfoMapper.updateUserById(user);
        return true;
    }

    public boolean addUser(UserInfo user) {
        userInfoMapper.insertUser(user);
        return true;
    }

    public boolean deleteUserById(int id) {
        userInfoMapper.deleteUserById(id);
        return true;
    }
}
