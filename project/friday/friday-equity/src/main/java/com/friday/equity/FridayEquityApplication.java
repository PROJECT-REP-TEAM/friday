package com.friday.equity;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.friday.equity.mapper")
public class FridayEquityApplication {
    public static void main(String[] args) {
        SpringApplication.run(FridayEquityApplication.class,args);
    }
}
