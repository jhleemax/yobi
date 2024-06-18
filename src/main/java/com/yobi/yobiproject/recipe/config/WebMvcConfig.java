package com.yobi.yobiproject.recipe.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // /images/** 경로로 오는 요청을 C:/Yobi 디렉토리의 파일로 매핑
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:///C:/Yobi/");
    }
}