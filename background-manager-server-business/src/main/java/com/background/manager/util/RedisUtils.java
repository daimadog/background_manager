package com.background.manager.util;

import cn.hutool.core.collection.IterUtil;
import cn.hutool.extra.spring.SpringUtil;
import org.redisson.api.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class RedisUtils {
    private static final RedissonClient CLIENT = (RedissonClient) SpringUtil.getBean(RedissonClient.class);
    private static final String PLACEHOLDER = "%s";

    public static long rateLimiter(String key, RateType rateType, int rate, int rateInterval) {
        RRateLimiter rateLimiter = CLIENT.getRateLimiter(key);
        rateLimiter.trySetRate(rateType, (long)rate, (long)rateInterval, RateIntervalUnit.SECONDS);
        return rateLimiter.tryAcquire() ? rateLimiter.availablePermits() : -1L;
    }

    public static RedissonClient getClient() {
        return CLIENT;
    }

    public static <T> void publish(String channelKey, T msg, Consumer<T> consumer) {
        RTopic topic = CLIENT.getTopic(channelKey);
        topic.publish(msg);
        consumer.accept(msg);
    }

    public static <T> void publish(String channelKey, T msg) {
        RTopic topic = CLIENT.getTopic(channelKey);
        topic.publish(msg);
    }

    public static <T> void subscribe(String channelKey, Class<T> clazz, Consumer<T> consumer) {
        RTopic topic = CLIENT.getTopic(channelKey);
        topic.addListener(clazz, (channel, msg) -> {
            consumer.accept(msg);
        });
    }

    public static <T> void setCacheObject(String key, T value) {
        setCacheObject(key, value, false);
    }

    public static <T> void setCacheObject(String key, T value, boolean isSaveTtl) {
        RBucket<Object> bucket = CLIENT.getBucket(key);
        if (isSaveTtl) {
            try {
                bucket.setAndKeepTTL(value);
            } catch (Exception var7) {
                long timeToLive = bucket.remainTimeToLive();
                bucket.set(value);
                bucket.expire(timeToLive, TimeUnit.MILLISECONDS);
            }
        } else {
            bucket.set(value);
        }

    }

    public static <T> void setCacheObject(String key, T value, long timeout, TimeUnit timeUnit) {
        RBucket<T> result = CLIENT.getBucket(key);
        result.set(value);
        result.expire(timeout, timeUnit);
    }

    public static <T> void setCommonCacheObject(String key, T value) {
        setCommonCacheObject(key, value, false);
    }

    public static <T> void setCommonCacheObject(String key, T value, boolean isSaveTtl) {
        RBucket<Object> bucket = CLIENT.getBucket("%s" + key);
        if (isSaveTtl) {
            try {
                bucket.setAndKeepTTL(value);
            } catch (Exception var7) {
                long timeToLive = bucket.remainTimeToLive();
                bucket.set(value);
                bucket.expire(timeToLive, TimeUnit.MILLISECONDS);
            }
        } else {
            bucket.set(value);
        }

    }

    public static <T> void setCommonCacheObject(String key, T value, long timeout, TimeUnit timeUnit) {
        RBucket<T> result = CLIENT.getBucket("%s" + key);
        result.set(value);
        result.expire(timeout, timeUnit);
    }

    public static <T> void addObjectListener(String key, ObjectListener listener) {
        RBucket<T> result = CLIENT.getBucket(key);
        result.addListener(listener);
    }

    public static boolean expire(String key, long timeout) {
        return expire(key, timeout, TimeUnit.SECONDS);
    }

    public static boolean expireCommonKey(String key, long timeout) {
        return expireCommonKey(key, timeout, TimeUnit.SECONDS);
    }

    public static boolean expire(String key, long timeout, TimeUnit unit) {
        RBucket rBucket = CLIENT.getBucket(key);
        return rBucket.expire(timeout, unit);
    }

    public static boolean expireCommonKey(String key, long timeout, TimeUnit unit) {
        RBucket rBucket = CLIENT.getBucket("%s" + key);
        return rBucket.expire(timeout, unit);
    }

    public static <T> T getCacheObject(String key) {
        RBucket<T> rBucket = CLIENT.getBucket(key);
        return rBucket.get();
    }

    public static <T> T getCommonCacheObject(String key) {
        RBucket<T> rBucket = CLIENT.getBucket("%s" + key);
        return rBucket.get();
    }

    public static <T> long getTimeToLive(String key) {
        RBucket<T> rBucket = CLIENT.getBucket(key);
        return rBucket.remainTimeToLive();
    }

    public static <T> long getCommonTimeToLive(String key) {
        RBucket<T> rBucket = CLIENT.getBucket("%s" + key);
        return rBucket.remainTimeToLive();
    }

    public static boolean deleteObject(String key) {
        return CLIENT.getBucket(key).delete();
    }

    public static boolean deleteCommonObject(String key) {
        return CLIENT.getBucket("%s" + key).delete();
    }

    public static void deleteObject(Collection collection) {
        RBatch batch = CLIENT.createBatch();
        collection.forEach((t) -> {
            batch.getBucket(t.toString()).deleteAsync();
        });
        batch.execute();
    }

    public static void deleteCommonObject(Collection collection) {
        RBatch batch = CLIENT.createBatch();
        collection.forEach((t) -> {
            batch.getBucket("%s" + t.toString()).deleteAsync();
        });
        batch.execute();
    }

    public static <T> boolean setCacheList(String key, List<T> dataList) {
        RList<T> rList = CLIENT.getList(key);
        return rList.addAll(dataList);
    }

    public static <T> void addListListener(String key, ObjectListener listener) {
        RList<T> rList = CLIENT.getList(key);
        rList.addListener(listener);
    }

    public static <T> List<T> getCacheList(String key) {
        RList<T> rList = CLIENT.getList(key);
        return rList.readAll();
    }

    public static <T> boolean setCacheSet(String key, Set<T> dataSet) {
        RSet<T> rSet = CLIENT.getSet(key);
        return rSet.addAll(dataSet);
    }

    public static <T> void addSetListener(String key, ObjectListener listener) {
        RSet<T> rSet = CLIENT.getSet(key);
        rSet.addListener(listener);
    }

    public static <T> Set<T> getCacheSet(String key) {
        RSet<T> rSet = CLIENT.getSet(key);
        return rSet.readAll();
    }

    public static <T> void setCacheMap(String key, Map<String, T> dataMap) {
        if (dataMap != null) {
            RMap<String, T> rMap = CLIENT.getMap(key);
            rMap.putAll(dataMap);
        }

    }

    public static <T> void addMapListener(String key, ObjectListener listener) {
        RMap<String, T> rMap = CLIENT.getMap(key);
        rMap.addListener(listener);
    }

    public static <T> Map<String, T> getCacheMap(String key) {
        RMap<String, T> rMap = CLIENT.getMap(key);
        return rMap.getAll(rMap.keySet());
    }

    public static <T> void setCacheMapValue(String key, String hKey, T value) {
        RMap<String, T> rMap = CLIENT.getMap(key);
        rMap.put(hKey, value);
    }

    public static <T> T getCacheMapValue(String key, String hKey) {
        RMap<String, T> rMap = CLIENT.getMap(key);
        return rMap.get(hKey);
    }

    public static <T> T delCacheMapValue(String key, String hKey) {
        RMap<String, T> rMap = CLIENT.getMap(key);
        return rMap.remove(hKey);
    }

    public static <K, V> Map<K, V> getMultiCacheMapValue(String key, Set<K> hKeys) {
        RMap<K, V> rMap = CLIENT.getMap(key);
        return rMap.getAll(hKeys);
    }

    public static Collection<String> keys(String pattern) {
        Iterable<String> iterable = CLIENT.getKeys().getKeysByPattern(pattern);
        return IterUtil.toList(iterable);
    }

    public static Boolean hasKey(String key) {
        RKeys rKeys = CLIENT.getKeys();
        return rKeys.countExists(new String[]{key}) > 0L;
    }

    private RedisUtils() {
    }
}
