package com.example.service_ht;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

//    @Resource
//    private HelloService helloService;

    @Autowired
    ProductClient client;

    @Value("${spring.application.name}")
    String serviceName;

//    @Value("${mygit}")
//    String mygit;

    @RequestMapping("hi")
    public String hi(@RequestParam String name){
        String s = "服务名称："+serviceName+" "+client.home(name);
//        return helloService.hiService(name);
//        System.out.println(mygit);
        return s;
    }
}
