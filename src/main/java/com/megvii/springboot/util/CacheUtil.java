package com.megvii.springboot.util;

import com.megvii.springboot.model.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CacheUtil {

    @Cacheable(value = "userInfo", key = "#userInfo.name")
    public SysUser getUser(SysUser user){
        log.info("get user");
        return user;
    }

    @CachePut(value = "userInfo", key = "#userInfo.name")
    public SysUser saveUser(SysUser user){
        log.info("save user");
        return user;
    }

    @CacheEvict(value = "userInfo", key = "#name")//移除指定key的数据
    public SysUser deleteUser(SysUser user){
        log.info("delete user");
        return user;
    }

    @CacheEvict(value = "userInfo", allEntries = true)//移除所有数据
    public SysUser deleteAll(SysUser user){
        log.info("deleteAll user");
        return user;
    }
}

