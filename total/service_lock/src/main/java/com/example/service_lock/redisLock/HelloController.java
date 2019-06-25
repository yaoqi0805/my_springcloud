package com.example.service_lock.redisLock;

import com.example.service_lock.child.DistributedLockHandler;
import com.example.service_lock.child.Lock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("newHelloController")
public class HelloController {

    @Value("spring.application.name")
    private String name;

    @Autowired
    private DistributedLocker lockHandler;

    @RequestMapping(value = "index2")
    public String index() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    lockHandler.lock("test", new AquiredLockWorker<Object>() {

                        @Override
                        public Object invokeAfterLockAquire() throws Exception {
                            System.out.println("执行方法:"+Thread.currentThread().getName());
                            Thread.sleep(5000);
                            return null;
                        }
                    },new Thread(),name);
                }
            }).start();
        }
        return "你好";
    }

}
