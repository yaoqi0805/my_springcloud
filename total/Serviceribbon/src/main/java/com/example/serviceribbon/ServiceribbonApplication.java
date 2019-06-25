package com.example.serviceribbon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@SpringBootApplication(scanBasePackages = "com.example.serviceribbon")
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.example.serviceribbon")
//@EnableEurekaClient
@EnableHystrix
public class ServiceribbonApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceribbonApplication.class, args);
    }

//    @Bean
//    @LoadBalanced
//    RestTemplate restTemplate(){
//        return new RestTemplate();
//    }
}
