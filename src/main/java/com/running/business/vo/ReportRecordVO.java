package com.running.business.vo;

import com.running.business.pojo.ReportRecord;
import lombok.Data;

/**
 * @author liumingyu
 * @create 2018-02-02 下午2:39
 */
@Data
public class ReportRecordVO extends ReportRecord{

    private String userName;
    private String userPhone;
    private String deliveryName;
    private String deliveryPhone;
    private String levelDesc;
    private String activeSideDesc;
}
