package com.megvii.springboot.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.megvii.springboot.model.SysUser;
import com.megvii.springboot.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @GetMapping("/queryUser")
    @ResponseBody
    public List<SysUser> queryUser(){
        List<SysUser> users = sysUserService.queryUserList();
        return users;
    }

    @GetMapping("/queryOne")
    @ResponseBody
    public SysUser queryOne(int id){
        return sysUserService.queryUserById(id);
    }

    @GetMapping("/addUser")
    public String addUser(SysUser user) {
        sysUserService.addUser(user);
        return "add success";
    }

    @PostMapping("/editUser")
    public String editUser(SysUser userInfo) {
        sysUserService.editUserById(userInfo);
        return "update success";
    }

    @GetMapping("/deleteUser")
    public String deleteUser(int id) {
        sysUserService.delUserById(id);
        return "delete success";
    }

    @GetMapping("/list")
    public ModelAndView showUserList(int pageNum, int pageSize) {
        IPage<SysUser> page = sysUserService.queryUserListByPage(pageNum,pageSize);
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("sys_user");
        modelAndView.addObject("pageInfo",page);
        return modelAndView;
    }
}
