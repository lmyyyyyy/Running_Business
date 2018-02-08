package com.running.business.sdk.common;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;

/**
 * @author liumingyu
 * @create 2018-01-14 下午4:27
 */
@Data
public class Response<T> {
    private Map<String, Future> futureMap = new HashMap<>();

    private Map<String, Object> resultMap = new HashMap<>();

    private T data;

    public void addFutureMap(String objectCode, Future future) {
        futureMap.put(objectCode, future);
    }

    public void addResultMap(String objectCode, T value) {
        resultMap.put(objectCode, value);
    }
}
