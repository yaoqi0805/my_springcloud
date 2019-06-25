package com.example.service_lock_copy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ServiceLockCopyApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceLockCopyApplication.class, args);
    }

}
