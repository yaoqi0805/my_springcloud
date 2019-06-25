package com.example.service_hi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/product")
@RestController
public class TestController {

    @Value("${server.port}")
    private String port;

    @RequestMapping(value = "/hi",method = RequestMethod.POST)
    public String home(@RequestParam(value = "name") String name){
        System.out.println("hi "+ name+" , i am form port:"+ port);
        return "hi "+ name+" , i am form port:"+ port;
    }

    @RequestMapping(value = "/hi2",method = RequestMethod.GET)
    public String home2(@RequestParam(value = "name") String name){
        System.out.println("hi "+ name+" , i am form port:"+ port);
        return "hi "+ name+" , i am form port:"+ port;
    }

}
