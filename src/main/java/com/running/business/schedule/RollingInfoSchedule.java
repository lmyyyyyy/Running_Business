package com.running.business.schedule;

import com.running.business.common.Config;
import com.running.business.dto.InfoDTO;
import com.running.business.service.RunInfoService;
import com.running.business.service.RunOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author liumingyu
 * @create 2018-01-29 下午3:10
 */
@Component
public class RollingInfoSchedule {
    private static final Logger LOGGER = LoggerFactory.getLogger(RollingInfoSchedule.class);

    private static final String LOG_PREFIX = "【滚动消息定时任务】 ";

    @Autowired
    private RunOrderService runOrderService;

    @Autowired
    private RunInfoService runInfoService;

    @Scheduled(cron = "0 */30 * * * ? ")
    public void rollingInfo() {
        Long beginTime = System.currentTimeMillis();
        LOGGER.info("{} 开始", LOG_PREFIX);
        List<InfoDTO> infoDTOS = runOrderService.queryInfoDTO(Config.ROLLING_INFO_TIME * 1000L);
        if (infoDTOS == null || infoDTOS.size() == 0) {
            LOGGER.warn("{} 没有订单记录 任务停止", LOG_PREFIX);
            return;
        }
        runInfoService.batchSaveRunInfo(infoDTOS);
        LOGGER.info("{} 结束, 耗时[{}]秒", LOG_PREFIX, (System.currentTimeMillis() - beginTime) * 1000);
    }
}
