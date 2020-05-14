package com.megvii.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.megvii.springboot.model.SysUserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

//    List<SysRole> selectUserRoleByUserId(@Param("id") Integer id);

    List<SysUserRole> selectListByUserId(@Param("userId") Integer userId);

}
