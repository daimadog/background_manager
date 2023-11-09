package com.background.manager.cache.constant;

/**
 * 缓存名字
 *
 * @author ccsert
 * @date 2022/02/28
 */
public interface CacheNames extends RbacCacheNames,OauthCacheNames,ProductCacheNames,PlatformCacheNames, UserCacheNames {
    /**
     *
     * 参考CacheKeyPrefix
     * cacheNames 与 key 之间的默认连接字符
     */
    String UNION = "::";

    /**
     * key内部的连接字符（自定义）
     */
    String UNION_KEY = ":";

}
