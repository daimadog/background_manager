package com.background.manager.cache.lock.annotation;

import com.background.manager.cache.CachePrefix;

/**
 * 分布式锁枚举类
 *
 * @author ccsert
 * @date 2022/02/24
 */
public enum LockConstant {
    /**
     * 通用锁常量
     */
    COMMON(CachePrefix.HEADER + "commonLock:", 1, 500, "请勿重复点击"),

    /**
     * 统计锁
     */
    STATISTIC(CachePrefix.HEADER + "statistic:", 5, 100, ""),

    /**
     * 爆品新品清除锁
     */
    CLEAN(CachePrefix.HEADER + "clean:", 5, 100, ""),

    /**
     * 刷新商品缓存
     */
    FLUSH_GOODS(CachePrefix.HEADER + "FLUSHGOODS:", 5, 100, "");
    /**
     * 分布式锁前缀
     */
    private String keyPrefix;
    /**
     * 等到最大时间，强制获取锁
     */
    private int waitTime;
    /**
     * 锁失效时间
     */
    private int leaseTime;
    /**
     * 加锁提示
     */
    private String message;

    LockConstant(String keyPrefix, int waitTime, int leaseTime, String message) {
        this.keyPrefix = keyPrefix;
        this.waitTime = waitTime;
        this.leaseTime = leaseTime;
        this.message = message;
    }

    public String getKeyPrefix() {
        return keyPrefix;
    }

    public void setKeyPrefix(String keyPrefix) {
        this.keyPrefix = keyPrefix;
    }

    public int getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(int waitTime) {
        this.waitTime = waitTime;
    }

    public int getLeaseTime() {
        return leaseTime;
    }

    public void setLeaseTime(int leaseTime) {
        this.leaseTime = leaseTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
