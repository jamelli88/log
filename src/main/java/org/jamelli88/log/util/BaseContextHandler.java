package org.jamelli88.log.util;

import cn.hutool.core.convert.Convert;

import java.util.HashMap;
import java.util.Map;

public class BaseContextHandler {


    public static final ThreadLocal<Map<String, String>> THREAD_LOCAL = new ThreadLocal<>();

    /**
     * 获取用户token
     */
    public static String getToken() {
        return get("token", String.class);
    }

    public static void setToken(String token) {
        set("token", token);
    }

    /**
     * 获取用户token
     */
    public static Long getUserId() {
        return get("user_id", Long.class);
    }

    public static void setUserId(Long userId) {
        set("user_id", userId);
    }

    /**
     * 获取请求链路信息
     */
    public static Long getTraceId() {
        return get("trace_id", Long.class);
    }

    public static void setTraceId(Long userId) {
        set("trace_id", userId);
    }

    public static void set(String key, Object value) {
        Map<String, String> map = getLocalMap();
        map.put(key, value == null ? "": value.toString());
    }

    private static <T> T get(String key, Class<T> type) {
        Map<String, String> map = getLocalMap();
        return Convert.convert(type, map.get(key));
    }

    private static <T> T get(String key, Class<T> type, Object def) {
        Map<String, String> map = getLocalMap();
        return Convert.convert(type, map.getOrDefault(key, String.valueOf(def == null ? "" : def)));
    }

    public static String get(String key) {
        Map<String, String> map = getLocalMap();
        return map.getOrDefault(key, "");
    }

    /**
     * 移除当前线程缓存内容，避免 内存泄露
     *
     * @param
     * @return
     * @author jamel.li
     * @create 2024/5/29 11:19
     */
    public static void remove() {
        if (THREAD_LOCAL != null) {
            THREAD_LOCAL.remove();
        }
    }

    public static Map<String, String> getLocalMap() {
        Map<String, String> map = THREAD_LOCAL.get();
        if (map == null) {
            map = new HashMap<>(10);
            THREAD_LOCAL.set(map);
        }
        return map;
    }

    public static void setLocalMap(Map<String, String> threadLocalMap) {
        THREAD_LOCAL.set(threadLocalMap);
    }
}

