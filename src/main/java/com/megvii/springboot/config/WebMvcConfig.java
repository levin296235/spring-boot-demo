package com.megvii.springboot.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.megvii.springboot.permit.CustomHandlerInterceptor;
import com.megvii.springboot.permit.CustomListener;
import com.megvii.springboot.permit.CustomFilter;
import org.apache.catalina.filters.RemoteIpFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.*;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    //拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //需要配置2：----------- 告知拦截器：/static/admin/** 与 /static/user/** 不需要拦截 （配置的是 路径）
        registry.addInterceptor(new CustomHandlerInterceptor()).addPathPatterns("/**").excludePathPatterns("/static/**","/webjars/**","/login/**","/home/**","/error/**");
    }

    //静态资源处理器
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //可以通过http://127.0.0.1:8080/web/home.html访问resources/web/home.html页面
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");//静态资源路径 css,js,img等
        registry.addResourceHandler("/templates/**").addResourceLocations("classpath:/templates/");//视图
        registry.addResourceHandler("/mapper/**").addResourceLocations("classpath:/mapper/");//mapper.xml
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/webjars/");
        registry.addResourceHandler("/webjars/**") .addResourceLocations("classpath:/META-INF/resources/webjars/");

    }

    //视图控制器配置
    @Override
    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/").setViewName("redirect:/login");
        registry.addViewController("/login").setViewName("login");//设置默认跳转视图为 /index
        registry.addViewController("/home").setViewName("home");//设置默认跳转视图为 /index

    }

   //视图解析器配置  控制controller String返回的页面视图跳转控制
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        // registry.viewResolver(new InternalResourceViewResolver("/jsp/", ".jsp"));
    }

    @Bean
    public RemoteIpFilter remoteIpFilter() {
        return new RemoteIpFilter();
    }

    //过滤器
    @Bean
    public FilterRegistrationBean testFilterRegistration() {

        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new CustomFilter());
        registration.addUrlPatterns("/*");
        registration.addInitParameter("paramName", "paramValue");
        registration.setName("MyFilter");
        registration.setOrder(1);
        return registration;
    }

    //监听器
    @Bean
    public ServletListenerRegistrationBean servletListenerRegistrationBean() {
        ServletListenerRegistrationBean slrBean = new ServletListenerRegistrationBean();
        slrBean.setListener(new CustomListener());
        return slrBean;
    }

    //跨域cors过滤器
    @Bean
    public CorsFilter corsFilter() {
        //1.添加CORS配置信息
        CorsConfiguration config = new CorsConfiguration();
        //放行哪些原始域
        config.addAllowedOrigin("*");
        //是否发送Cookie信息
        config.setAllowCredentials(true);
        //放行哪些原始域(请求方式)
        config.addAllowedMethod("*");
        //放行哪些原始域(头部信息)
        config.addAllowedHeader("*");
        //暴露哪些头部信息（因为跨域访问默认不能获取全部头部信息）
        config.addExposedHeader("content-type");

        //2.添加映射路径
        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration("/**", config);

        //3.返回新的CorsFilter.
        return new CorsFilter(configSource);
    }

    //消息内容转换配置，配置fastJson返回json转换
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        //创建fastJson消息转换器
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter(){
            protected void writeInternal(Object obj, HttpOutputMessage outputMessage){
                try {
                    if(obj != null){
                        Map<String,Object> map = new HashMap<>();
                        map.put("preUser",obj);
                        map.put("result","success");
                        super.writeInternal( map, outputMessage);
                    }else{
                        super.writeInternal( obj, outputMessage);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        //  FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        List<MediaType> supportedMediaTypes = new ArrayList<>();
        supportedMediaTypes.add(MediaType.APPLICATION_JSON);
        fastConverter.setSupportedMediaTypes(supportedMediaTypes);

        //创建配置类
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setCharset(Charset.forName("UTF-8"));
        fastJsonConfig.setDateFormat("YYYY-MM-dd");

        //修改配置返回内容的过滤
        fastJsonConfig.setSerializerFeatures(
                SerializerFeature.DisableCircularReferenceDetect,
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.PrettyFormat
        );
        fastConverter.setFastJsonConfig(fastJsonConfig);
        //将fastjson添加到视图消息转换器列表内
        converters.add(fastConverter);
    }
}