package com.running.business.vo;

import com.running.business.pojo.RunOrder;
import lombok.Data;

import java.util.Date;

/**
 * Created by sunxiaodong3 on 2018/1/7.
 */
@Data
public class OrderVO extends RunOrder{
    private String userName;
    private String userPhone;
    private String deliveryName;
    private String deliveryPhone;
    private String orderTypeDesc;
    private String orderStatusDesc;
    private String payTypeDesc;
    private Date probablyArriveTime;
    //单位：m
    private Double DistanceDesc;
}
