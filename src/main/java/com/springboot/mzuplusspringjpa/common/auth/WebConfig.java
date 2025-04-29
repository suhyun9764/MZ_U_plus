package com.springboot.mzuplusspringjpa.common.auth;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final ManagerAuthInterceptor managerAuthInterceptor;

    public WebConfig(ManagerAuthInterceptor managerAuthInterceptor) {
        this.managerAuthInterceptor = managerAuthInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(managerAuthInterceptor)
            .addPathPatterns("/**") // 모든 요청 가로채기
            .excludePathPatterns(
                "/api/managers/login",
                "/api/managers",
                "/login.html",
                "/register.html",
                "/css/**", "/js/**", "/images/**"
            ); // 로그인/회원가입은 예외
    }
}
