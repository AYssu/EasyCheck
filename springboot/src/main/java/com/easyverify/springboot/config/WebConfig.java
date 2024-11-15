package com.easyverify.springboot.config;

import com.easyverify.springboot.interceptor.EasyInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private EasyInterceptor easyInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       registry.addInterceptor(easyInterceptor)
               .excludePathPatterns("/user/login","/user/register","/open/*");
    }
}
