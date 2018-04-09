package com.running.business.service.impl;

import com.running.business.common.BaseResult;
import com.running.business.common.ResultEnum;
import com.running.business.exception.AppException;
import com.running.business.mapper.RunDeliveryInfoMapper;
import com.running.business.pojo.RunDeliveryInfo;
import com.running.business.pojo.RunDeliveryInfoExample;
import com.running.business.service.RunDeliveryInfoService;
import com.running.business.service.RunDeliveryuserService;
import com.running.business.util.FileUploadUtil;
import com.running.business.util.ValidateUtil;
import com.running.business.vo.DeliveryDetailVO;
import com.running.business.vo.DeliveryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class RunDeliveryInfoServiceImpl implements RunDeliveryInfoService {

    @Autowired
    private RunDeliveryInfoMapper runDeliveryInfoMapper;

    @Autowired
    private RunDeliveryuserService runDeliveryuserService;

    /**
     * 创建配送员信息
     *
     * @param deliveryInfo
     * @return
     */
    @Override
    public Integer saveRunDeliveryInfo(RunDeliveryInfo deliveryInfo) throws AppException {
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
    public void updateRunDeliveryInfo(RunDeliveryInfo deliveryInfo) throws AppException {
        if (deliveryInfo == null) {
            throw new AppException(ResultEnum.DELIVERY_INFO_ISEMPTY);
        }
        runDeliveryInfoMapper.updateByPrimaryKeySelective(deliveryInfo);
    }

    /**
     * 根据id删除配送员信息
     *
     * @param did
     */
    @Override
    public void deleteRunDeliveryInfoByID(Integer did) throws AppException {
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
    public RunDeliveryInfo getRunDeliveryInfoByID(Integer did) throws AppException {
        if (did == null) {
            return null;
        }
        RunDeliveryInfo info = runDeliveryInfoMapper.selectByPrimaryKey(did);
        return info;
    }

    /**
     * 根据id获取VO
     *
     * @param did
     * @return
     */
    @Override
    public DeliveryDetailVO getDeliveryVOByID(Integer did) throws AppException {
        return runDeliveryuserService.queryDeliveryDetailVO(did);
    }

    /**
     * 上传配送员头像
     *
     * @param file
     * @param did
     * @return
     */
    @Override
    public BaseResult uploadDeliveryImg(MultipartFile file, Integer did) throws AppException {
        try {
            String filePath = FileUploadUtil.uploadFile(file, "deliveryPhoto");
            RunDeliveryInfo runDeliveryInfo = runDeliveryInfoMapper.selectByPrimaryKey(did);
            runDeliveryInfo.setPhoto(filePath);
            runDeliveryInfoMapper.updateByPrimaryKeySelective(runDeliveryInfo);
        } catch (Exception e) {
            return BaseResult.fail("配送员头像图片上传异常");
        }
        return BaseResult.success();
    }

    /**
     * 检查系统生成昵称的唯一性，true为不存在可以使用，false为已存在不可使用
     *
     * @param name
     * @return
     * @throws AppException
     */
    @Override
    public boolean checkNameUnique(String name) throws AppException {
        RunDeliveryInfoExample example = new RunDeliveryInfoExample();
        RunDeliveryInfoExample.Criteria criteria = example.createCriteria();
        criteria.andNameEqualTo("游客_" + name);
        List<RunDeliveryInfo> list = runDeliveryInfoMapper.selectByExample(example);
        if (ValidateUtil.isValid(list)) {
            return false;
        }
        return true;
    }

    /**
     * 更新配送员积分
     *
     * @param did
     * @param point
     * @throws AppException
     */
    @Override
    public void updateDeliveryPoint(Integer did, Integer point) throws AppException {
        RunDeliveryInfo info = this.getRunDeliveryInfoByID(did);
        if (info == null) {
            return;
        }
        info.setPoint(info.getPoint() + point);
        this.updateRunDeliveryInfo(info);
    }


    /**
     * 转VO
     *
     * @param runDeliveryInfo
     * @return
     */
    public DeliveryVO convert2VO(RunDeliveryInfo runDeliveryInfo) throws AppException {
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
