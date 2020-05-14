package com.megvii.springboot.permit;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
public class CustomFilter implements Filter {

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        log.info("**************初始化自定义过滤器CustomFilter**************");
    }

    @Override
    public void doFilter(ServletRequest srequest, ServletResponse sresponse,
                         FilterChain filterChain)
            throws IOException, ServletException {
        // TODO Auto-generated method stub
//        log.info("*************CustomFilter************* 开始");
//        HttpServletRequest request = (HttpServletRequest) srequest;
//        String requestURL = request.getRequestURL().toString();
//        log.info("**************CustomFilter REQUEST_URL 结束******************" + requestURL);
        filterChain.doFilter(srequest, sresponse);
    }

}