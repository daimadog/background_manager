package com.background.manager.cache.lock;

/**
 * 锁的模式
 *
 * @author jeecg
 */
public enum LockModel {
    /**
     * 可重入
     */
    REENTRANT,
    /**
     * 公平
     */
    FAIR,
    /**
     * 联锁(可以把一组锁当作一个锁来加锁和释放)
     */
    MULTIPLE,
    /**
     * 红锁
     */
    RED_LOCK,
    /**
     * 读锁
     */
    READ,
    /**
     * 写锁
     */
    WRITE,
    /**
     * 自动模式,当参数只有一个.使用 REENTRANT 参数多个 RED_LOCK
     */
    AUTO
}
