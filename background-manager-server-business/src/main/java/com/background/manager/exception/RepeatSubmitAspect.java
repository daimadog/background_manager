package com.background.manager.exception;

import cn.hutool.crypto.SecureUtil;
import com.background.manager.cache.lock.annotation.Repeat;
import com.background.manager.cache.lock.client.RedissonLockClient;
import com.background.manager.util.LoginHelper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Aspect
@Slf4j
@Component
public class RepeatSubmitAspect {

    @Resource
    private RedissonLockClient redissonLockClient;

    /***
     * 定义controller切入点拦截规则，拦截Repeat注解的业务方法
     */
    @Pointcut("@annotation(Repeat)")
    public void pointCut(Repeat Repeat) {
    }

    /**
     * AOP分布式锁拦截
     *
     * @param joinPoint 连接点
     * @param repeat    j重复
     * @return {@link Object}
     * @throws Throwable 抛出异常
     */
    @Around(value = "pointCut(repeat)", argNames = "joinPoint,repeat")
    public Object repeatSubmit(ProceedingJoinPoint joinPoint, Repeat repeat) throws Throwable {
        String[] parameterNames = new LocalVariableTableParameterNameDiscoverer().getParameterNames(((MethodSignature) joinPoint.getSignature()).getMethod());
        if (Objects.nonNull(repeat)) {
            // 获取参数
            Object[] args = joinPoint.getArgs();
            //初始匿名幂等用户Key
            String userId = "-1";
            // 获取用户id
            try {
                userId = LoginHelper.getLoginId().toString();
            } catch (Exception e) {
                for (int i = 0; i < args.length; i++) {
                    if ("phone".equals(parameterNames[i])) {
                        userId = String.valueOf(args[i]);
                    }
                }
            }
            String methodName = joinPoint.getSignature().getName();
            String key = "repeatSubmit:" + userId + ":" + SecureUtil.md5(Arrays.toString(args) + Arrays.toString(parameterNames) + methodName);
            log.info("幂等key:{}", key);
            // 公平加锁，lockTime后锁自动释放
            boolean isLocked = false;
            try {
                isLocked = redissonLockClient.fairLock(key, TimeUnit.SECONDS, repeat.lockTime());
                // 如果成功获取到锁就继续执行
                if (isLocked) {
                    log.info("幂等key:{},加锁成功", key);
                    // 执行进程
                    return joinPoint.proceed();
                } else {
                    // 未获取到锁
                    throw new Exception("请勿重复提交");
                }
            } finally {
                // 如果锁还存在，在方法执行完成后，释放锁
                if (isLocked) {
                    redissonLockClient.unlock(key);
                }
            }
        }
        return joinPoint.proceed();
    }




}
