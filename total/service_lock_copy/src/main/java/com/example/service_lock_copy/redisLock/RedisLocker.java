package com.example.service_lock_copy.redisLock;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class RedisLocker implements DistributedLocker{

    private final static String LOCKER_PREFIX = "lock:";

    @Autowired
    RedissonConnector redissonConnector;

    @Override
    public <T> T lock(String resourceName, AquiredLockWorker<T> worker,
                      Thread thread,String name) throws UnableToAquireLockException {

        return this.lock(resourceName,worker,100,thread,name);
    }

    @Override
    public <T> T lock(String resourceName, AquiredLockWorker<T> worker,
                      int lockTime,Thread thread,String name) {
        RedissonClient redisson = redissonConnector.getClient();
        RLock lock = redisson.getLock(LOCKER_PREFIX+resourceName);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            boolean b = lock.tryLock(100,lockTime, TimeUnit.SECONDS);
            if (b){
                System.out.println("线程获取锁："+thread.getName()+"   服务："+name+"  时间："+sdf.format(new Date()));
                return worker.invokeAfterLockAquire();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("线程释放锁："+thread.getName()+"   服务："+name+"  时间："+sdf.format(new Date()));
            lock.unlock();
        }
        throw new UnableToAquireLockException();
    }
}
