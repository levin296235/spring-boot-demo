package com.megvii.springboot.service;

import com.megvii.springboot.mapper.SysPermissionMapper;
import com.megvii.springboot.model.SysPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysPermissionService {
    @Autowired
    private SysPermissionMapper permissionMapper;
    // 获取指定角色所有权限
    public List<SysPermission> queryPermissionByRoleId(Integer roleId) {
        return permissionMapper.selectPermissionByRoleId(roleId);
    }
}
