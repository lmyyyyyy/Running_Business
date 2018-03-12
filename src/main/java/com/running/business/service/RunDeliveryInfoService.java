package com.running.business.service;

import com.fasterxml.jackson.databind.deser.Deserializers;
import com.running.business.common.BaseResult;
import com.running.business.pojo.RunDeliveryInfo;
import com.running.business.vo.DeliveryVO;
import org.springframework.web.multipart.MultipartFile;

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

    /**
     * 上传配送员头像
     * @param file
     * @param did
     * @return
     */
    BaseResult uploadDeliveryImg(MultipartFile file, Integer did);

}
