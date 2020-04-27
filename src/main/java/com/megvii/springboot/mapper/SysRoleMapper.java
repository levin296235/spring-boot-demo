package com.megvii.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.megvii.springboot.model.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {

    List<SysRole> selectUserRoleByUserId(@Param("id") Integer id);
}
