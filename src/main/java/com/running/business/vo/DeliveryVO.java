package com.running.business.vo;

import com.running.business.pojo.RunDeliveryuser;
import lombok.Data;

/**
 * @author liumingyu
 * @create 2018-01-14 下午2:54
 */
@Data
public class DeliveryVO extends RunDeliveryuser {

    private String statusDesc;

    private String isDeleteDesc;

    private String availableDesc;

    private String photo;

    private String name;

    private String card;

    private Boolean gender;

    private String genderDesc;

    private String phone;

    private Integer addressId;

    private String addressDesc;

    private Integer point;

    private String level;

    private Integer reportedTimes;

    private Double balance;

}
