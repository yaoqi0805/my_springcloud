package com.example.service_lock.child;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private DistributedLockHandler lockHandler;

    @RequestMapping(value = "index")
    public String index() throws InterruptedException {
        Lock lock = new Lock("lynn","min");
        if (lockHandler.tryLock(lock)){
            System.out.println("执行方法");
            Thread.sleep(5000);
        }
        lockHandler.releaseLock(lock);
        return "你好";
    }

}
