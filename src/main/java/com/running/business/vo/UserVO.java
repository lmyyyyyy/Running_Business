package com.running.business.vo;

import lombok.Data;

/**
 * @author liumingyu
 * @create 2017-12-03 下午7:17
 */
@Data
public class UserVO {
    private Integer uid;

    private String userPhoto;

    private String userName;

    private Boolean userGender;

    private String userPhone;

    private Integer userPoint;

    private Integer userAddress;
}
