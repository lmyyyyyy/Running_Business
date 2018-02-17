package com.running.business.vo;

import com.running.business.pojo.RunUserCoupon;
import lombok.Data;

/**
 * @author liumingyu
 * @create 2018-02-02 下午7:37
 */
@Data
public class CouponVO extends RunUserCoupon {

    private String userName;
    private String userPhone;
    private String statusDesc;
}
