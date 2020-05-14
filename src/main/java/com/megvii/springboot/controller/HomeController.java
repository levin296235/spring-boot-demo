package com.megvii.springboot.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Log4j2
public class HomeController {

    @GetMapping(value = {"","/","/home"})
    public String home(){
        System.out.println("==================获取username==================" + getUsername());
        return "home";
    }

    @GetMapping("/home/homeAdmin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseBody
    public String helloAdmin() {
        return "Hello,admin";
    }

    @GetMapping("/home/homeUser")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @ResponseBody
    public String helloUser() {
        return "Hello,user";
    }

    @RequestMapping("home/admin")
    @ResponseBody
    @PreAuthorize("hasPermission('/admin','r')")
    public String printAdminR() {
        return "如果你看见这句话，说明你访问/admin路径具有r权限";
    }

    @RequestMapping("home/admin/c")
    @ResponseBody
    @PreAuthorize("hasPermission('/admin','c')")
    public String printAdminC() {
        return "如果你看见这句话，说明你访问/admin路径具有c权限";
    }

    public String getUsername(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!authentication.isAuthenticated()) {
            return null;
        }
        Object principal = authentication.getPrincipal();
        String username = null;
        if(principal instanceof org.springframework.security.core.userdetails.UserDetails){
            username = ((org.springframework.security.core.userdetails.UserDetails) principal).getUsername();
        }else {
            username = principal.toString();

        }
        return username;
    }
}
