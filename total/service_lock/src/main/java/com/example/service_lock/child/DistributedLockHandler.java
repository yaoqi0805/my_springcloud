package com.example.service_lock.child;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

@Component
public class DistributedLockHandler {

    private static final Logger logger = LoggerFactory.getLogger(DistributedLockHandler.class);

    private final static long LOCK_EXPIRE = 30 * 1000L;//单个业务持有锁的时间3
    private final static long LOCK_TRY_INTERVAL = 30L;//默认30ms尝试一次
    private final static long LOCK_TRY_TIMEOUT = 20 * 1000L;//默认尝试20s

    @Autowired
    private StringRedisTemplate template;

    /**
     * 尝试获取全局锁
     *
     * @param lock 所名称
     * @return true 获取成功，false 获取失败
     */
    public boolean tryLock(Lock lock) {
        return getLock(lock, LOCK_TRY_TIMEOUT, LOCK_TRY_INTERVAL, LOCK_EXPIRE);
    }

    /**
     * 尝试获取全局锁
     *
     * @param lock    所名称
     * @param timeout 获取超时时间 单位ms
     * @return true 获取成功，false 获取失败
     */
    public boolean tryLock(Lock lock, long timeout) {
        return getLock(lock, timeout, LOCK_TRY_INTERVAL, LOCK_EXPIRE);
    }

    /**
     * 尝试获取全局锁
     *
     * @param lock        所名称
     * @param timeout     获取超时时间 单位ms
     * @param tryInterval 多长时间获取一次 单位ms
     * @return true 获取成功，false 获取失败
     */
    public boolean tryLock(Lock lock, long timeout, long tryInterval) {
        return getLock(lock, timeout, tryInterval, LOCK_EXPIRE);
    }

    /**
     * 尝试获取全局锁
     *
     * @param lock           所名称
     * @param timeout        获取超时时间 单位ms
     * @param tryInterval    多长时间获取一次 单位ms
     * @param lockExpireTime 获取成功后锁的过期时间
     * @return true 获取成功，false 获取失败
     */
    public boolean tryLock(Lock lock, long timeout, long tryInterval, long lockExpireTime) {
        return getLock(lock, timeout, tryInterval, lockExpireTime);
    }

    /**
     * 操作redis获取全局锁
     *
     * @param lock           所名称
     * @param timeout        获取超时时间 单位ms
     * @param tryInterval    多长时间获取一次 单位ms
     * @param lockExpireTime 获取成功后锁的过期时间
     * @return true 获取成功，false 获取失败
     */
    public boolean getLock(Lock lock, long timeout, long tryInterval, long lockExpireTime) {

        if (StringUtil.isEmpty(lock.getName()) || StringUtil.isEmpty(lock.getValue())) {
            return false;
        }

        long startTime = System.currentTimeMillis();

        do {
            if (!template.hasKey(lock.getName())) {
                ValueOperations<String, String> ops = template.opsForValue();
                ops.set(lock.getName(), lock.getValue(), lockExpireTime);
                return true;
            } else {//存在锁
                logger.info("lock is exist!!");
                System.out.println("lock is exist!!");
            }
            if (System.currentTimeMillis() - startTime > timeout) {
                return false;
            }
            try {
                Thread.sleep(tryInterval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }while (template.hasKey(lock.getName()));
        return false;
    }

    /**
     * 释放锁
     *  @param lock
     */
    public void releaseLock(Lock lock){
        if (!StringUtil.isEmpty(lock.getName())){
            template.delete(lock.getName());
        }
    }

}
