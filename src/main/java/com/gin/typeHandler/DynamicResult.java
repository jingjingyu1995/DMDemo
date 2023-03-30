package com.gin.typeHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * mybatis 动态结果
 * @author hefeiyu 2021-11-15 16:25
 */
public class DynamicResult {

    private Map<String, Object> map = new HashMap<>();

    public DynamicResult() {
    }

    public void put(String key, Object result) {
        map.put(key, result);
    }

    @SuppressWarnings("unchecked")
    public <T> T get(String key) {
        return (T) map.get(key);
    }
}
