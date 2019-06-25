package com.example.gateway;

import com.example.gateway.myconfige.ApiFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

/**
 * 网关zuul
 * 作用:路由转发、异常处理、过滤拦截
 */

@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

//    @Bean
//    public ApiFilter preZuulFilter() {
//        return new ApiFilter();
//    }
}
