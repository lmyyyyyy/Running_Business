package com.running.business.vo;

import com.running.business.pojo.RunUser;
import com.running.business.pojo.RunUserAddress;
import com.running.business.pojo.RunUserPreference;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liumingyu
 * @create 2017-12-03 下午7:17
 */
@Data
public class UserDetailVO extends RunUser {

    private String userStatusDesc;

    private String isDeleteDesc;

    private String userPhoto;

    private String userName;

    private Boolean userGender;

    private String userGenderDesc;

    private String userPhone;

    private Integer userPoint;

    private Integer userAddress;

    private String userAddressDesc;

    private Double balance;

    private Integer reportedTimes;

    private Integer orderCount;

    private Integer payCount;

    private Integer refundApplyCount;

    private Integer refundRecordCount;

    //private List<RunUserBalanceRecord> balanceRecords = new ArrayList<>();

    //private List<RunOrder> orders = new ArrayList<>();

    //private List<RunOrderPay> pays = new ArrayList<>();

    private List<RunUserAddress> addresses = new ArrayList<>();

    private List<CouponVO> couponVOS = new ArrayList<>();

    private List<RunUserPreference> runUserPreferences = new ArrayList<>();

    //private List<RefundApplyVO> applyVOS = new ArrayList<>();

    //private List<RefundRecordVO> refundRecordVOS = new ArrayList<>();

    //private List<ReportRecordVO> reportRecordVOS = new ArrayList<>();
}
