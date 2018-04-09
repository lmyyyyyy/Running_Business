package com.running.business.service;

import com.running.business.common.BaseResult;
import com.running.business.exception.AppException;
import com.running.business.pojo.RunDeliveryInfo;
import com.running.business.vo.DeliveryDetailVO;
import org.springframework.web.multipart.MultipartFile;

public interface RunDeliveryInfoService {

    /**
     * 创建配送员信息
     *
     * @param deliveryInfo
     * @return
     */
    Integer saveRunDeliveryInfo(RunDeliveryInfo deliveryInfo) throws AppException;

    /**
     * 更新配送员信息
     *
     * @param deliveryInfo
     * @return
     */
    void updateRunDeliveryInfo(RunDeliveryInfo deliveryInfo) throws AppException;

    /**
     * 根据id删除配送员信息
     *
     * @param did
     * @return
     */
    void deleteRunDeliveryInfoByID(Integer did) throws AppException;

    /**
     * 根据id获取配送员信息
     *
     * @param did
     * @return
     */
    RunDeliveryInfo getRunDeliveryInfoByID(Integer did) throws AppException;

    /**
     * 根据id获取vo
     *
     * @param did
     * @return
     */
    DeliveryDetailVO getDeliveryVOByID(Integer did) throws AppException;

    /**
     * 上传配送员头像
     *
     * @param file
     * @param did
     * @return
     */
    BaseResult uploadDeliveryImg(MultipartFile file, Integer did) throws AppException;

    /**
     * 检查系统生成昵称的唯一性，true为不存在可以使用，false为已存在不可使用
     *
     * @param name
     * @return
     * @throws AppException
     */
    boolean checkNameUnique(String name) throws AppException;

    /**
     * 更新配送员积分
     *
     * @param did
     * @param point
     * @throws AppException
     */
    void updateDeliveryPoint(Integer did, Integer point) throws AppException;
}
