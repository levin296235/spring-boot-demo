package com.megvii.springboot.model;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class SysRole implements Serializable {

    private Integer id;

    private String roleName;// 角色名称

    private String roleDesc;// 角色描述
}
