package com.friday.bills;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.friday.bills.mapper")
public class FridayBillsApplication {
    public static void main(String[] args) {
        SpringApplication.run(FridayBillsApplication.class,args);
    }
}
