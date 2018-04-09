package com.running.business.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author liumingyu
 * @create 2018-04-09 上午11:32
 */
@Data
public class DistanceVO implements Serializable {

    /**
     * 源经度
     */
    private String sourceLng;

    /**
     * 源纬度
     */
    private String sourceLat;

    /**
     * 目标经度
     */
    private String targetLng;

    /**
     * 目标纬度
     */
    private String targetLat;

    /**
     * 订单距离
     */
    private Double distance;

    /**
     * 配送／排队时长
     */
    private Long minutes;

    /**
     * 订单金额
     */
    private Integer money;
}
