package com.example.serviceribbon;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "service-hi",fallback = ProductClientHystrix.class)
public interface ProductClient {

    @RequestMapping(value = "/product/hi",method = RequestMethod.POST)
    String home(@RequestParam(value = "name") String name);

}
