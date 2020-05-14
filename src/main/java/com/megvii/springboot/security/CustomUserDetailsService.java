package com.megvii.springboot.security;

import com.megvii.springboot.model.SysRole;
import com.megvii.springboot.model.SysUser;
import com.megvii.springboot.model.SysUserRole;
import com.megvii.springboot.service.SysRoleService;
import com.megvii.springboot.service.SysUserRoleService;
import com.megvii.springboot.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private SysUserService userService;

    @Autowired
    private SysRoleService roleService;

    @Autowired
    private SysUserRoleService userRoleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 从数据库中取出用户信息
        SysUser user = userService.queryUserByName(username);

        // 判断用户是否存在
        if(user == null) {
            return null;
        }

        // 添加权限
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        List<SysUserRole> userRoles = userRoleService.queryListByUserId(user.getId());
        for (SysUserRole userRole : userRoles) {
            SysRole role = roleService.queryRoleById(userRole.getRoleId());
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }

        // 返回UserDetails实现类
        return new User(user.getUsername(), user.getPassword(), authorities);
    }
}