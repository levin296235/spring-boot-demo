package com.megvii.springboot.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SysMenu extends BaseModel {

    private Integer id;

    private String menuName;//菜单名称

    private String menuUrl;//Controller路径

    private String menuCode;//菜单编码

    private Long parentId;//父菜单ID

    private Integer menuType;//菜单类型：0-菜单1-按钮

    private Integer orderNum;//显示序号
}
