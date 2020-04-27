package com.megvii.springboot.permit;

import com.megvii.springboot.model.SysMenu;
import com.megvii.springboot.model.SysUser;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Component("rbacPermission")
public class RbacPermission{

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        Object principal = authentication.getPrincipal();
        boolean hasPermission = false;
        if (principal instanceof SysUser) {
            // 读取用户所拥有的权限菜单
            List<SysMenu> menus = ((SysUser) principal).getRoleMenus();
            System.out.println(menus.size());
            for (SysMenu menu : menus) {
                if (antPathMatcher.match(menu.getMenuUrl(), request.getRequestURI())) {
                    hasPermission = true;
                    break;
                }
            }
        }
        return hasPermission;
    }
}