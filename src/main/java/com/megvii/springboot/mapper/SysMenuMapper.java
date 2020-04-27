package com.megvii.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.megvii.springboot.model.SysMenu;
import com.megvii.springboot.model.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenuMapper> {

    List<SysMenu> selectRoleMenuByRoles(@Param("roles") List<SysRole> roles);
}
