package com.megvii.springboot.util;

import com.megvii.springboot.model.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CacheUtil {

    @Cacheable(value = "userInfo", key = "#userInfo.name")
    public UserInfo getUser(UserInfo user){
        log.info("get user");
        return user;
    }

    @CachePut(value = "userInfo", key = "#userInfo.name")
    public UserInfo saveUser(UserInfo user){
        log.info("save user");
        return user;
    }

    @CacheEvict(value = "userInfo", key = "#name")//移除指定key的数据
    public UserInfo deleteUser(UserInfo user){
        log.info("delete user");
        return user;
    }

    @CacheEvict(value = "userInfo", allEntries = true)//移除所有数据
    public UserInfo deleteAll(UserInfo user){
        log.info("deleteAll user");
        return user;
    }
}

