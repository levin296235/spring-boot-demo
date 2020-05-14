package com.megvii.springboot.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.megvii.springboot.mapper.SysUserMapper;
import com.megvii.springboot.model.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SysUserService {

    @Autowired
    private SysUserMapper userMapper;


    public List<SysUser> queryUserList() {
        return userMapper.selectUserList();
    }

    public SysUser queryUserById(int id) {
        return userMapper.selectUserById(id);
    }

    public SysUser queryUserByName(String name) {
        return userMapper.selectUserByName(name);
    }

    public boolean editUserById(SysUser user) {
        userMapper.updateUserById(user);
        return true;
    }

    public boolean addUser(SysUser user) {
        userMapper.insertUser(user);
        return true;
    }

    public boolean delUserById(int id) {
        userMapper.deleteUserById(id);
        return true;
    }

    public IPage<SysUser> queryUserListByPage(int pageNum, int pageSize){
//        new QueryWrapper<SysUser>()
        return userMapper.selectPage(new Page<>(pageNum,pageSize),null );
    }

//    @Override
//    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
//        // 根据用户名查找用户
//        SysUser user = userMapper.selectUserByName(userName);
//        if (user != null) {
//            System.out.println(">>>>>>>>>>>>>>>>loadUserByUserName>>>>>>>>>>>>>>>>" + user);
//            //根据用户id获取用户角色
//            List<SysRole> roles = sysRoleMapper.selectUserRoleByUserId(user.getId());
//            // 填充权限
//            Collection<SimpleGrantedAuthority> authorities = new HashSet<>();
//            for (SysRole role : roles) {
//                authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
//            }
//            //填充权限菜单
//            List<SysMenu> menus = sysMenuMapper.selectRoleMenuByRoles(roles);
//            return new SysUser(user.getUsername(),user.getPassword(),authorities,menus);
//        } else {
//            System.out.println(userName +" not found");
//            throw new UsernameNotFoundException(userName +" not found");
//        }
//    }
}
