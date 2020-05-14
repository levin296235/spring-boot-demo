package com.megvii.springboot.model;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 缓存的对象必须实现Serializable
 */
@Data
@ToString
public class SysUser implements Serializable {


    private Integer id;

    private String username;

    private String password;

    private String realName;

    private String gender;

    private String phone;

    private String email;

    private String avatar;

    private String createTime;

    private String lastLoginTime;

    private String state;

}
