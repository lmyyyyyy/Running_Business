package com.running.business.service;

import com.running.business.pojo.RunDeliveryInfo;
import com.running.business.vo.DeliveryVO;

public interface RunDeliveryInfoService {

    /**
     * 创建配送员信息
     *
     * @param deliveryInfo
     * @return
     */
    Integer saveRunDeliveryInfo(RunDeliveryInfo deliveryInfo);

    /**
     * 更新配送员信息
     *
     * @param deliveryInfo
     * @return
     */
    void updateRunDeliveryInfo(RunDeliveryInfo deliveryInfo);

    /**
     * 根据id删除配送员信息
     *
     * @param did
     * @return
     */
    void deleteRunDeliveryInfoByID(Integer did);

    /**
     * 根据id获取配送员信息
     *
     * @param did
     * @return
     */
    RunDeliveryInfo getRunDeliveryInfoByID(Integer did);

    /**
     * 根据id获取vo
     *
     * @param did
     * @return
     */
    DeliveryVO getDeliveryVOByID(Integer did);
}
