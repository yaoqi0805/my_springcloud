package com.example.serviceribbon;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Component;

@Component
public class ProductClientHystrix implements ProductClient{

//    @HystrixCommand(fallbackMethod = "hiError")
    @Override
    public String home(String name) {
        return "sorry "+name;
    }

//    public String hiError(String name){
//        return "hi,"+name+",sorry,error!";
//    }
}
