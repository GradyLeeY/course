package com.grady.business.config;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

/**
 * @Author Grady
 * @Date 2020/5/24 13:49
 * @Version 1.0
 */
@SpringBootApplication
@EnableEurekaClient
@ComponentScan("com.grady")
@MapperScan("com.grady.server.mapper")
public class BusinessApplication {
    private static final Logger LOG = LoggerFactory.getLogger(BusinessApplication.class);
    public static void main(String[] args) {

        SpringApplication app = new SpringApplication(BusinessApplication.class);
        Environment env = app.run(args).getEnvironment();
        LOG.info("business启动成功！！");
        LOG.info("business地址: http://127.0.0.1:{}", env.getProperty("server.port"));
    }
}
