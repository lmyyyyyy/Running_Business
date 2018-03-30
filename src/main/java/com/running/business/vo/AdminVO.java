package com.running.business.vo;

import com.running.business.pojo.RunAdmin;
import lombok.Data;

/**
 * @author liumingyu
 * @create 2017-12-24 下午5:08
 */
@Data
public class AdminVO extends RunAdmin {

    private String adminName;

    private String adminPhone;

    private String statusDesc;

    private String isDeleteDesc;

}
