package com.running.business.vo;

import com.running.business.pojo.RunUser;
import lombok.Data;

/**
 * @author liumingyu
 * @create 2017-12-03 下午7:17
 */
@Data
public class UserVO extends RunUser {

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

}
