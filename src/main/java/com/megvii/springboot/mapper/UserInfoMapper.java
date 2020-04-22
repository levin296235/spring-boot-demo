package com.megvii.springboot.mapper;

import com.megvii.springboot.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserInfoMapper {

     List<UserInfo> selectUserList();

     UserInfo selectUserById(int id);

     boolean updateUserById(UserInfo user);

     boolean insertUser(UserInfo user);

     boolean deleteUserById(int id);
}
