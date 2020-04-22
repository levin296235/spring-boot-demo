package com.megvii.springboot.permit;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
public class MyFilter implements Filter {

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        log.info("初始化MyFilter");
    }

    @Override
    public void doFilter(ServletRequest srequest, ServletResponse sresponse,
                         FilterChain filterChain)
            throws IOException, ServletException {
        // TODO Auto-generated method stub
        log.info("MyFilter 请求处理");
        HttpServletRequest request = (HttpServletRequest) srequest;
        filterChain.doFilter(srequest, sresponse);
    }

}