package com.megvii.springboot.service;

import com.megvii.springboot.mapper.SysRoleMapper;
import com.megvii.springboot.model.SysRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysRoleService {
    @Autowired
    private SysRoleMapper roleMapper;

    public SysRole queryRoleById(Integer id){
        return roleMapper.selectRoleById(id);
    }

    public SysRole selectByName(String roleName) {
        return roleMapper.selectRoleByName(roleName);
    }
}
