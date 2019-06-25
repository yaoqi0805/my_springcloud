package com.example.service_lock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ServiceLockApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceLockApplication.class, args);
    }

}
