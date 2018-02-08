package com.running.business.service.impl;

import com.running.business.common.ResultEnum;
import com.running.business.exception.AppException;
import com.running.business.mapper.RunDeliveryInfoMapper;
import com.running.business.pojo.RunDeliveryInfo;
import com.running.business.service.RunDeliveryInfoService;
import com.running.business.vo.DeliveryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RunDeliveryInfoServiceImpl implements RunDeliveryInfoService {

    @Autowired
    private RunDeliveryInfoMapper runDeliveryInfoMapper;

    /**
     * 创建配送员信息
     *
     * @param deliveryInfo
     * @return
     */
    @Override
    public Integer saveRunDeliveryInfo(RunDeliveryInfo deliveryInfo) {
        if (deliveryInfo == null) {
            throw new AppException(ResultEnum.DELIVERY_INFO_ISEMPTY);
        }
        Integer did = runDeliveryInfoMapper.insert(deliveryInfo);
        return did;
    }

    /**
     * 更新配送员信息
     *
     * @param deliveryInfo
     */
    @Override
    public void updateRunDeliveryInfo(RunDeliveryInfo deliveryInfo) {
        if (deliveryInfo == null) {
            throw new AppException(ResultEnum.DELIVERY_INFO_ISEMPTY);
        }
        runDeliveryInfoMapper.updateByPrimaryKey(deliveryInfo);
    }

    /**
     * 根据id删除配送员信息
     *
     * @param did
     */
    @Override
    public void deleteRunDeliveryInfoByID(Integer did) {
        RunDeliveryInfo info = runDeliveryInfoMapper.selectByPrimaryKey(did);
        if (info == null) {
            throw new AppException(ResultEnum.DEL_ERROR.getCode(), ResultEnum.DEL_ERROR.getMsg());
        }
        runDeliveryInfoMapper.deleteByPrimaryKey(did);
    }

    /**
     * 根据id获取配送员信息
     *
     * @param did
     * @return
     */
    @Override
    public RunDeliveryInfo getRunDeliveryInfoByID(Integer did) {
        RunDeliveryInfo info = runDeliveryInfoMapper.selectByPrimaryKey(did);
        if (info == null) {
            throw new AppException(ResultEnum.QUERY_ERROR.getCode(), ResultEnum.QUERY_ERROR.getMsg());
        }
        return info;
    }

    /**
     * 根据id获取VO
     *
     * @param did
     * @return
     */
    @Override
    public DeliveryVO getDeliveryVOByID(Integer did) {
        RunDeliveryInfo runDeliveryInfo = this.getRunDeliveryInfoByID(did);
        if (runDeliveryInfo == null) {
            throw new AppException(ResultEnum.DELIVERY_INFO_ISEMPTY);
        }
        return convert2VO(runDeliveryInfo);
    }

    /**
     * 转VO
     *
     * @param runDeliveryInfo
     * @return
     */
    public DeliveryVO convert2VO(RunDeliveryInfo runDeliveryInfo) {
        DeliveryVO deliveryVO = new DeliveryVO();
        deliveryVO.setDid(runDeliveryInfo.getDid());
        deliveryVO.setAddressId(runDeliveryInfo.getAddressId());
        deliveryVO.setReportedTimes(runDeliveryInfo.getReportedTimes());
        deliveryVO.setPoint(runDeliveryInfo.getPoint());
        deliveryVO.setPhoto(runDeliveryInfo.getPhoto());
        deliveryVO.setPhone(runDeliveryInfo.getPhone());
        deliveryVO.setName(runDeliveryInfo.getName());
        deliveryVO.setLevel(runDeliveryInfo.getLevel());
        deliveryVO.setGender(runDeliveryInfo.getGender());
        deliveryVO.setCard(runDeliveryInfo.getCard());
        return deliveryVO;
    }
}
