package com.megvii.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.megvii.springboot.model.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

     List<SysUser> selectUserList();

     SysUser selectUserById(Integer id);

     SysUser selectUserByName(String userName);

     boolean updateUserById(SysUser user);

     boolean insertUser(SysUser user);

     boolean deleteUserById(Integer id);
}
