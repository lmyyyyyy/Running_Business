package com.running.business.vo;

import com.running.business.pojo.RefundRecord;
import lombok.Data;

/**
 * @author liumingyu
 * @create 2018-02-15 上午10:01
 */
@Data
public class RefundRecordVO extends RefundRecord{

    private String userName;

    private String userPhone;

    private String deliveryName;

    private String deliveryPhone;

    private String responsibilityDesc;

    private String sourceDesc;

    private String operatorName;

    private String operatorPhone;
}
