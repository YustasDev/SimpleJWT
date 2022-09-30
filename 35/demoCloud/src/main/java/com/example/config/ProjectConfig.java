package com.example.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


@Configuration
public class ProjectConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}



//
//    @Configuration
//    @EnableFeignClients(basePackages = "com.example.proxy")
//    public class ProjectConfig {
//    }
