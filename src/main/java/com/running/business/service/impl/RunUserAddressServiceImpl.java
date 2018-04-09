package com.running.business.service.impl;

import com.running.business.common.BaseResult;
import com.running.business.common.ResultEnum;
import com.running.business.exception.AppException;
import com.running.business.mapper.RunUserAddressMapper;
import com.running.business.pojo.RunUserAddress;
import com.running.business.pojo.RunUserAddressExample;
import com.running.business.pojo.RunUserAddressExample.Criteria;
import com.running.business.service.RunUserAddressService;
import com.running.business.util.LocationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class RunUserAddressServiceImpl implements RunUserAddressService {

    @Autowired
    private RunUserAddressMapper runUserAddressMapper;

    /**
     * 保存用户地址
     *
     * @param userAddress
     */
    @Override
    public void saveRunUserAddress(RunUserAddress userAddress) throws AppException {
        if (userAddress == null || userAddress.getUserAddress() == null || "".equals(userAddress.getUserAddress())) {
            throw new AppException(ResultEnum.USER_ADDRESS_INFO_EMTPY);
        }
        if (userAddress.getLatitude() == null || "".equals(userAddress.getLatitude()) || userAddress.getLongitude() == null || "".equals(userAddress.getLongitude())) {
            Map<String, String> map = LocationUtil.getLatitude(userAddress.getUserAddress());
            userAddress.setLatitude(map.get("lat"));
            userAddress.setLongitude(map.get("lng"));
        }
        userAddress.setAddTime(new Date());
        userAddress.setUid(userAddress.getUid());
        userAddress.setUpdateTime(new Date());
        userAddress.setIsDelete(false);
        userAddress.setUserAddress(userAddress.getUserAddress());
        runUserAddressMapper.insert(userAddress);
    }

    /**
     * 更新用户地址
     *
     * @param userAddress
     */
    @Override
    public void updateRunUserAddress(RunUserAddress userAddress) throws AppException {
        if (userAddress == null) {
            throw new AppException(ResultEnum.USER_ADDRESS_INFO_EMTPY);
        }
        runUserAddressMapper.updateByPrimaryKeySelective(userAddress);
    }

    /**
     * 根据id删除用户地址
     *
     * @param id
     */
    @Override
    public void deleteRunUserAddressByID(Integer id) throws AppException {
        RunUserAddress userAddress = runUserAddressMapper.selectByPrimaryKey(id);
        if (userAddress == null) {
            throw new AppException(ResultEnum.DEL_ERROR.getCode(), ResultEnum.DEL_ERROR.getMsg());
        }
        runUserAddressMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据id获取地址
     *
     * @param id
     * @return
     */
    @Override
    public BaseResult getRunUserAddressByID(Integer id) throws AppException {
        RunUserAddress userAddress = runUserAddressMapper.selectByPrimaryKey(id);
        return BaseResult.success(userAddress);
    }

    /**
     * 获取该用户的所有地址信息
     *
     * @param uid
     * @return
     */
    @Override
    public BaseResult getAllRunUserAddress(Integer uid) throws AppException {
        RunUserAddressExample example = new RunUserAddressExample();
        Criteria criteria = example.createCriteria();
        criteria.andUidEqualTo(uid);
        List<RunUserAddress> list = runUserAddressMapper.selectByExample(example);
        return BaseResult.success(list);
    }

    /**
     * 根据用户id删除所有地址
     *
     * @param uid
     * @return
     */
    @Override
    public void deleteAllRunUserAddressByUID(Integer uid) throws AppException {
        RunUserAddressExample example1 = new RunUserAddressExample();
        Criteria criteria1 = example1.createCriteria();
        criteria1.andUidEqualTo(uid);
        runUserAddressMapper.deleteByExample(example1);
    }

}
