package com.shop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.shop.mapper")
@EnableTransactionManagement
public class EditClientApp {
    public static void main(String[] args) {
        SpringApplication.run(EditClientApp.class,args);
    }
}
