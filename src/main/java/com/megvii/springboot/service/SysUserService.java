package com.megvii.springboot.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.megvii.springboot.mapper.SysMenuMapper;
import com.megvii.springboot.mapper.SysRoleMapper;
import com.megvii.springboot.mapper.SysUserMapper;
import com.megvii.springboot.model.SysMenu;
import com.megvii.springboot.model.SysRole;
import com.megvii.springboot.model.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
@Service
public class SysUserService implements UserDetailsService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysMenuMapper sysMenuMapper;

    public List<SysUser> queryUserList() {
        return sysUserMapper.selectUserList();
    }

    public SysUser queryUserById(int id) {
        return sysUserMapper.selectUserById(id);
    }

    public boolean editUserById(SysUser user) {
        sysUserMapper.updateUserById(user);
        return true;
    }

    public boolean addUser(SysUser user) {
        sysUserMapper.insertUser(user);
        return true;
    }

    public boolean delUserById(int id) {
        sysUserMapper.deleteUserById(id);
        return true;
    }

    public Page<SysUser> queryUserListByPage(int pageNum, int pageSize){
//        new QueryWrapper<SysUser>()
        Page<SysUser> page = sysUserMapper.selectPage(new Page<>(pageNum,pageSize),null );
        return page;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        // 根据用户名查找用户
        SysUser user = sysUserMapper.selectUserByName(userName);
        if (user != null) {
            System.out.println(">>>>>>>>>>>>>>>>loadUserByUserName>>>>>>>>>>>>>>>>" + user);
            //根据用户id获取用户角色
            List<SysRole> roles = sysRoleMapper.selectUserRoleByUserId(user.getId());
            // 填充权限
            Collection<SimpleGrantedAuthority> authorities = new HashSet<>();
            for (SysRole role : roles) {
                authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
            }
            //填充权限菜单
            List<SysMenu> menus = sysMenuMapper.selectRoleMenuByRoles(roles);
            return new SysUser(user.getUsername(),user.getPassword(),authorities,menus);
        } else {
            System.out.println(userName +" not found");
            throw new UsernameNotFoundException(userName +" not found");
        }
    }
}
