package com.example.service_ht;

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
