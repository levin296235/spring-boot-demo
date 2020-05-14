package com.megvii.springboot.service;

import com.megvii.springboot.mapper.SysUserRoleMapper;
import com.megvii.springboot.model.SysUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUserRoleService {
    @Autowired
    private SysUserRoleMapper userRoleMapper;

    public List<SysUserRole> queryListByUserId(Integer userId) {
        return userRoleMapper.selectListByUserId(userId);
    }
}
