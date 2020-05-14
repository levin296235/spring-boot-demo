package com.megvii.springboot.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.megvii.springboot.model.SysUser;
import com.megvii.springboot.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping("/list")
    public String list(Model model, @RequestParam(value = "pageNum", defaultValue = "0") int pageNum, @RequestParam(value = "pageSize", defaultValue = "5") int pageSize) {
        IPage<SysUser> users= sysUserService.queryUserListByPage(pageNum,pageSize);
//        System.out.println("总页数" + users.getTotal());
//        System.out.println("当前页是：" + pageNum);
        model.addAttribute("users", users);
        return "user/sys_user_list";
    }

    @RequestMapping("/add")
    public String add(@ModelAttribute SysUser user) {
        user.setPassword(BCrypt.hashpw(user.getPassword(),BCrypt.gensalt()));
        sysUserService.addUser(user);
        return "redirect:/user/list";
    }

    @RequestMapping("/toAdd")
    public String toAdd() {
        return "user/sys_user_add";
    }



    @RequestMapping("/toEdit")
    public String toEdit(Model model,Integer id) {
        SysUser user=sysUserService.queryUserById(id);
        model.addAttribute("user", user);
        return "user/sys_user_edit";
    }

    @RequestMapping("/edit")
    public String edit(SysUser user) {
        user.setPassword(BCrypt.hashpw(user.getPassword(),BCrypt.gensalt()));
        sysUserService.editUserById(user);
        return "redirect:/user/list";
    }


    @RequestMapping("/delete")
    public String delete(Integer id) {
        sysUserService.delUserById(id);
        return "redirect:/user/list";
    }

    /**
     * 初始化用户数据
     */
    @RequestMapping("/initUserData")
    public @ResponseBody String initUserData() {
        //普通用户
        SysUser user=new SysUser();
        user.setUsername("user");
        user.setPassword(new BCryptPasswordEncoder().encode("user"));
        sysUserService.addUser(user);
        //管理员
        SysUser admin=new SysUser();
        admin.setUsername("admin");
        admin.setPassword(new BCryptPasswordEncoder().encode("admin"));
        sysUserService.addUser(admin);

        return "success";
    }
}
