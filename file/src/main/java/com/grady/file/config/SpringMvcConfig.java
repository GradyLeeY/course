package com.grady.file.config;

import org.springframework.beans.factory.annotation.Value;
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

    @Value("${file.path}")
    private String FILE_PATH;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/f/**").addResourceLocations("file:" + FILE_PATH);
        //http://127.0.0.1:9003/file/f/teacher/6XfOcgLk-Grady.jpg
        //http://127.0.0.1:9000/file/f/teacher/yJi1Kq27.jpg
    }
}
