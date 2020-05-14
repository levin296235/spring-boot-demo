package com.megvii.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.megvii.springboot.model.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {

    SysRole selectRoleById(@Param("id") Integer id);

    SysRole selectRoleByName(@Param("roleName") String roleName);
}
