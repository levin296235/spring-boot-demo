package com.megvii.springboot.permit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
@Configuration
@Slf4j
public class CustomListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        log.info("----------------自定义初始化监听器上下文----------------");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        log.info("----------------自定义监听器销毁----------------");
    }
}