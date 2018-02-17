package com.running.business.pojo;

import lombok.Data;

/**
 * 定时任务
 *
 * @author liumingyu
 * @create 2018-01-29 下午7:27
 */
@Data
public class ScheduleJob {
    private String jobId;
    private String jobName;
    private String jobGroup;
    private int jobStatus;
    private String cronExpression;
    private String jobDesc;
}
