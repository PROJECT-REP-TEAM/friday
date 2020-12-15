package com.friday;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class FridayRegistryApplication {
    public static void main(String[] args) {
        SpringApplication.run(FridayRegistryApplication.class,args);
    }
}
