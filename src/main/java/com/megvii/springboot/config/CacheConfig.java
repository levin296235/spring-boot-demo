package com.megvii.springboot.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableCaching
public class CacheConfig {

    @Primary //@Primary原因是因为有ehcache缓存了，又有redis缓存必须选择哪个缓存为主
    @Bean
    public EhCacheCacheManager ehCacheCacheManager(EhCacheManagerFactoryBean bean) {
        return new EhCacheCacheManager(bean.getObject());
    }

    @Bean
    public EhCacheManagerFactoryBean ehCacheManagerFactoryBean(){
        EhCacheManagerFactoryBean cacheManagerFactoryBean  = new EhCacheManagerFactoryBean();
        cacheManagerFactoryBean .setConfigLocation(new ClassPathResource("ehcache.xml"));
        cacheManagerFactoryBean .setShared(true);
        return cacheManagerFactoryBean;
    }
}
