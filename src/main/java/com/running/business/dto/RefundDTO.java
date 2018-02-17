package com.running.business.dto;

import lombok.Data;

/**
 * @author liumingyu
 * @create 2018-02-15 下午3:21
 */
@Data
public class RefundDTO {

    private Integer operatorId;
    private String orderId;
    private Integer responsibility;
    private Integer status;
    private Integer id;
    private Integer did;
    private Integer uid;
}
