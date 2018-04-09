package com.running.business.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 地址VO
 *
 * @author liumingyu
 * @create 2018-04-09 上午11:02
 */
@Data
public class LocationVO implements Serializable {

    /**
     * 地址
     */
    private String address;

    /**
     * 经度
     */
    private String longitude;

    /**
     * 纬度
     */
    private String latitude;

    /**
     * 省
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 区
     */
    private String district;
}
