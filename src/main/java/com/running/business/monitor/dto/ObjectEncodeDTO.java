package com.running.business.monitor.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author liumingyu
 * @create 2018-01-21 下午2:59
 */
@Data
public class ObjectEncodeDTO implements Serializable{
    private Object data;
    private String className;
    private String encodingType;
}
