package com.background.manager.cache.lock.annotation;

import java.lang.annotation.*;

/**
 * 防止重复提交的注解
 *
 * @author ccsert
 * @date 2022/02/24
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface Repeat {

    /**
     * 超时时间 单位秒(s)
     *
     * @return int
     */
    int lockTime();


    /**
     * redis 锁key的
     *
     * @return redis 锁key
     */
    String lockKey() default "";
}
