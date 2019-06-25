package com.example.service_ht;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *  不同服务之间HTTP调用：Feign默认集成Ribbon,默认实现负载均衡
 *
 */
@FeignClient(name = "service-hi",fallback = ProductClientHystrix.class)
public interface ProductClient {

    @RequestMapping(value = "/product/hi",method = RequestMethod.POST)
    String home(@RequestParam(value = "name") String name);

}
