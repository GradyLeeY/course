package com.course.file.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author Grady
 * @Date 2020/7/17 22:42
 * @Version 1.0
 */
@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/f/**").addResourceLocations("file:D:/file/grady/course/");
        //http://127.0.0.1:9003/file/f/teacher/6XfOcgLk-Grady.jpg
    }
}
