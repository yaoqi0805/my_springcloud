package com.example.service_lock.redisLock;


import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class RedissonConnector {

    RedissonClient client;

    @PostConstruct
    public void init(){
        client = Redisson.create();
    }

    public RedissonClient getClient(){
        return client;
    }
}
