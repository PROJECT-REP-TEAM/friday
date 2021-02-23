package com.friday.finance;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.friday.finance.mapper")
public class FridayFinanceApplication {
    public static void main(String[] args) {
        SpringApplication.run(FridayFinanceApplication.class,args);
    }
}
