package com.example.serviceribbon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class HelloController {

//    @Resource
//    private HelloService helloService;

    @Autowired
    ProductClient client;

    @Value("${spring.application.name}")
    String serviceName;

    @RequestMapping("hi")
    public String hi(@RequestParam String name){
        String s = "服务名称："+serviceName+" "+client.home(name);
//        return helloService.hiService(name);
        return s;
    }
}
