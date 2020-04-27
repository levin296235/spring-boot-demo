package com.megvii.springboot.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;

/**
 * 缓存的对象必须实现Serializable
 */
@Data
@ToString
@TableName("t_user")
public class SysUser implements UserDetails {

    private Integer id;

    private String userName;

    private String passWord;

    private String phone;

    private String email;

    //排除表字段映射，使用transient或@TableField(exist = false)
    private transient List<SysRole> userRoles;// 用户权限集合

    private transient List<SysMenu> roleMenus;// 角色菜单集合

    private transient Collection<? extends GrantedAuthority> authorities;

    public SysUser() {

    }

    public SysUser(String userName, String passWord, Collection<? extends GrantedAuthority> authorities,
                      List<SysMenu> roleMenus) {
        this.userName = userName;
        this.passWord = passWord;
        this.authorities = authorities;
        this.roleMenus = roleMenus;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.passWord;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
