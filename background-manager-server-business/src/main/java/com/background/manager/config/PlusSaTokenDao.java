package com.background.manager.config;

import cn.dev33.satoken.dao.SaTokenDao;
import cn.dev33.satoken.util.SaFoxUtil;
import com.background.manager.util.RedisUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class PlusSaTokenDao  implements SaTokenDao {
    public PlusSaTokenDao() {
    }

    public String get(String key) {
        return (String) RedisUtils.getCommonCacheObject(key);
    }

    public void set(String key, String value, long timeout) {
        if (timeout != 0L && timeout > -2L) {
            if (timeout == -1L) {
                RedisUtils.setCommonCacheObject(key, value);
            } else {
                RedisUtils.setCommonCacheObject(key, value, timeout, TimeUnit.SECONDS);
            }
        }
    }

    public void update(String key, String value) {
        long expire = this.getTimeout(key);
        if (expire != -2L) {
            this.set(key, value, expire);
        }
    }

    public void delete(String key) {
        RedisUtils.deleteCommonObject(key);
    }

    public long getTimeout(String key) {
        return RedisUtils.getCommonTimeToLive(key) / 1000L;
    }

    public void updateTimeout(String key, long timeout) {
        if (timeout == -1L) {
            long expire = this.getTimeout(key);
            if (expire != -1L) {
                this.set(key, this.get(key), timeout);
            }
        } else {
            RedisUtils.expireCommonKey(key, timeout, TimeUnit.SECONDS);
        }
    }

    public Object getObject(String key) {
        return RedisUtils.getCommonCacheObject(key);
    }

    public void setObject(String key, Object object, long timeout) {
        if (timeout != 0L && timeout > -2L) {
            if (timeout == -1L) {
                RedisUtils.setCacheObject(key, object);
            } else {
                RedisUtils.setCacheObject(key, object, timeout, TimeUnit.SECONDS);
            }
        }
    }

    public void updateObject(String key, Object object) {
        long expire = this.getObjectTimeout(key);
        if (expire != -2L) {
            this.setObject(key, object, expire);
        }
    }

    public void deleteObject(String key) {
        RedisUtils.deleteObject(key);
    }

    public long getObjectTimeout(String key) {
        return RedisUtils.getCommonTimeToLive(key) / 1000L;
    }

    public void updateObjectTimeout(String key, long timeout) {
        if (timeout == -1L) {
            long expire = this.getObjectTimeout(key);
            if (expire != -1L) {
                this.setObject(key, this.getObject(key), timeout);
            }
        } else {
            RedisUtils.expire(key, timeout, TimeUnit.SECONDS);
        }
    }

    public List<String> searchData(String prefix, String keyword, int start, int size) {
        Collection<String> keys = RedisUtils.keys(prefix + "*" + keyword + "*");
        List<String> list = new ArrayList(keys);
        return SaFoxUtil.searchList(list, start, size);
    }

}
