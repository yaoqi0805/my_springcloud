package com.example.service_lock_copy.redisLock;

/**
 * 获取锁管理类
 */
public interface DistributedLocker {

    /**
     * 获取锁
     * @param resourceName 锁的名称
     * @param worker 获取锁后的处理类
     * @param <T>
     * @return 处理完具体的业务逻辑要返回的数据
     * @throws UnableToAquireLockException
     * @throws Exception
     */
    <T> T lock(String resourceName, AquiredLockWorker<T> worker,
               Thread thread, String name) throws UnableToAquireLockException;

    <T> T lock(String resourceName, AquiredLockWorker<T> worker,
               int lockTime, Thread thread, String name);
}
