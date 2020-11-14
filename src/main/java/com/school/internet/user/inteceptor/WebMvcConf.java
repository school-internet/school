package com.school.internet.user.inteceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Component
public class WebMvcConf implements WebMvcConfigurer  {

    @Autowired
    private AuthInterceptor authInterceptor;

    @Autowired
    private AuthActionInterceptor authActionInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor).excludePathPatterns("/static")
                .excludePathPatterns("/js/**")
                .excludePathPatterns("/css/**")
                .excludePathPatterns("/fonts/**")
                .excludePathPatterns("/img/**")
                .excludePathPatterns("/pages/**")
                .excludePathPatterns("/")
                .excludePathPatterns("/login")
                .excludePathPatterns("/wxindex")
                .excludePathPatterns("/wechart/auth")
                .excludePathPatterns("/error")
                .excludePathPatterns("/logout")
                .excludePathPatterns("/MP_verify_ghHyUNV7iYGEj2Ru.txt")
                .addPathPatterns("/**");
        registry.addInterceptor(authActionInterceptor)
                .excludePathPatterns("/static")
                .excludePathPatterns("/js/**")
                .excludePathPatterns("/css/**")
                .excludePathPatterns("/fonts/**")
                .excludePathPatterns("/img/**")
                .excludePathPatterns("/pages/**")
                .excludePathPatterns("/")
                .excludePathPatterns("/login")
                .excludePathPatterns("/wechart/auth")
                .excludePathPatterns("/MP_verify_ghHyUNV7iYGEj2Ru.txt")
                .excludePathPatterns("/wxindex")
                .excludePathPatterns("/error")
                .excludePathPatterns("/logout")
                .addPathPatterns("/**");


    }


    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }
}
