package com.friday;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.friday.user.mapper")
public class FridayUserServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(FridayUserServiceApplication.class,args);
    }
}
