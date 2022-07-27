package com.buba.boot_phoenix.config;

import com.buba.boot_phoenix.config.interceptor.JwtInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: zl,
 * Date:    2022/7/7 16:22,
 * Version: IntelliJ IDEA 2021.2.1
 * 注册拦截器
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor())
                .addPathPatterns("/**")   // 拦截所有请求，通过判断 Token 是否合法来决定是否需要登录
.excludePathPatterns("/register/getStudentLogin", "/register/insertStudentInfo", "/**/getLoginEmail", "/**/sendEmailCode", "/**/resetPass", "/**/excelExport", "/**/excelImport", "/file/**"); //排除规则： 登录 注册 导入 导出
    }
    //对象注册/注入到SpringBoot容器中
    @Bean
    public JwtInterceptor jwtInterceptor(){
        return new JwtInterceptor();
    }
}
