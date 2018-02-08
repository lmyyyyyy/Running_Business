package com.running.business.sdk.common;

import com.running.business.pojo.RunOrder;
import lombok.Data;

/**
 * @author liumingyu
 * @create 2018-01-14 下午4:27
 */
@Data
public class Request {

    private Integer bizId;

    private Integer operatorId;

    private String className;

    private String methodName;

    private RunOrder order;

    private Object[] args;
}
