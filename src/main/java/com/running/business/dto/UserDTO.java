package com.running.business.dto;

import lombok.Data;

/**
 * @author liumingyu
 * @create 2018-01-21 下午6:36
 */
@Data
public class UserDTO {
    private Integer id;
    private String name;
    private String phone;
    private Boolean status;
    private Integer userType;
}
