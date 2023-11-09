package com.background.manager.config;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BackgroundAuthContext {

    public static ThreadLocal<Map<String, Object>> getInstance() {
        return BackgroundAuthContext.BackgroundAuthContextHolder.INSTANCE;
    }

    public static void put(String key, Object object) {
        ((Map)getInstance().get()).put(key, object);
    }

    public static Object get(String key) {
        return ((Map)getInstance().get()).get(key);
    }

    public static void remove(String key) {
        ((Map)getInstance().get()).remove(key);
    }

    public static void clear() {
        ((Map)getInstance().get()).clear();
    }

    public static void removeContext() {
        getInstance().remove();
    }

    private BackgroundAuthContext() {
    }

    private static class BackgroundAuthContextHolder {
        private static final ThreadLocal<Map<String, Object>> INSTANCE = new ThreadLocal<Map<String, Object>>() {
            protected Map<String, Object> initialValue() {
                return new ConcurrentHashMap();
            }
        };

        private BackgroundAuthContextHolder() {
        }
    }



}
