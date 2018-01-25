package com.running.business.vo;

import lombok.Data;

/**
 * @author liumingyu
 * @create 2018-01-14 下午2:54
 */
@Data
public class DeliveryVO {

    private Integer did;

    private String photo;

    private String name;

    private String card;

    private Boolean gender;

    private String phone;

    private Integer addressId;

    private Integer point;

    private String level;

    private Integer reportedTimes;
}
