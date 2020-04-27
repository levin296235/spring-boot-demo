package com.megvii.springboot.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SysRole extends BaseModel{

    private Integer id;

    private String roleName;// 角色名称

    private String roleDesc;// 角色描述
}
