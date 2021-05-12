package com.polo.distribute.lock;

/**
 * @author baoqianyong
 * @date 2021/5/11
 */
public interface Lock {

    /**
     * 加锁
     *
     * @param requestId
     */
    void lock(String requestId);

    /**
     * 解锁
     *
     * @param requestId
     */
    void unlock(String requestId);
}
