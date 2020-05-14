package com.megvii.springboot.model;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
@Data
@ToString
public class SysUserRole implements Serializable {

    private Integer userId;

    private Integer roleId;
}
