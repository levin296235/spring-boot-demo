package com.megvii.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.megvii.springboot.model.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

     List<SysUser> selectUserList();

     SysUser selectUserById(@Param("id") Integer id);

     SysUser selectUserByName(@Param("username") String username);

     boolean updateUserById(SysUser user);

     boolean insertUser(SysUser user);

     boolean deleteUserById(@Param("id") Integer id);
}
