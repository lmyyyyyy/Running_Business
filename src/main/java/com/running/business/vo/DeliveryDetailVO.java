package com.running.business.vo;

import com.github.pagehelper.PageInfo;
import com.running.business.pojo.RunDeliveryAddress;
import com.running.business.pojo.RunDeliveryBalanceRecord;
import com.running.business.pojo.RunDeliveryuser;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liumingyu
 * @create 2018-01-14 下午2:54
 */
@Data
public class DeliveryDetailVO extends RunDeliveryuser {

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

    private Double sendDistance;

    private Double deliveryDistance;

    private Double viewOrderDistance;

    private Integer orderCount;

    private PageInfo<RunDeliveryBalanceRecord> balanceRecordPageInfo = new PageInfo<>();

    private List<RunDeliveryAddress> addressList = new ArrayList<>();

}
