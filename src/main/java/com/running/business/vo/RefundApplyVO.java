package com.running.business.vo;

import com.running.business.pojo.RefundApply;
import lombok.Data;

/**
 * @author liumingyu
 * @create 2018-02-02 下午12:04
 */
@Data
public class RefundApplyVO extends RefundApply{

    private String userName;

    private String userPhone;

    private String statusDesc;

    private String operatorName;

    private String operatorPhone;
}
