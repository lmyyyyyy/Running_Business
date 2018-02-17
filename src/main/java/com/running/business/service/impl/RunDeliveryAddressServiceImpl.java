package com.running.business.service.impl;

import com.running.business.common.ResultEnum;
import com.running.business.exception.AppException;
import com.running.business.mapper.RunDeliveryAddressMapper;
import com.running.business.pojo.RunDeliveryAddress;
import com.running.business.pojo.RunDeliveryAddressExample;
import com.running.business.pojo.RunDeliveryAddressExample.Criteria;
import com.running.business.service.RunDeliveryAddressService;
import com.running.business.util.ValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RunDeliveryAddressServiceImpl implements RunDeliveryAddressService {

    @Autowired
    private RunDeliveryAddressMapper runDeliveryAddressMapper;

    /**
     * 添加地址
     *
     * @param address
     * @return
     * @throws AppException
     */
    @Override
    public Integer saveRunDeliveryAddress(RunDeliveryAddress address) throws AppException {
        if (address == null) {
            throw new AppException(ResultEnum.DELIVERY_ADDRESS_IS_EMPTY);
        }
        address.setAddTime(new Date());
        address.setIsDelete(false);
        address.setUpdateTime(new Date());
        address.setDid(address.getDid());
        Integer id = runDeliveryAddressMapper.insert(address);
        return id;
    }

    /**
     * 更新地址
     *
     * @param address
     * @throws AppException
     */
    @Override
    public void updateRunDeliveryAddress(RunDeliveryAddress address) throws AppException {
        if (address == null) {
            throw new AppException(ResultEnum.DELIVERY_ADDRESS_IS_EMPTY);
        }
        address.setUpdateTime(new Date());
        runDeliveryAddressMapper.updateByPrimaryKeySelective(address);
    }

    /**
     * 根据id删除地址
     *
     * @param id
     * @throws AppException
     */
    @Override
    public void deleteRunDeliveryAddressByID(Integer id) throws AppException {
        RunDeliveryAddress address = runDeliveryAddressMapper.selectByPrimaryKey(id);
        if (address == null) {
            throw new AppException(ResultEnum.DEL_ERROR.getCode(), ResultEnum.DEL_ERROR.getMsg());
        }
        runDeliveryAddressMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据did删除所有地址
     *
     * @param did
     * @throws AppException
     */
    @Override
    public void deleteAllRunDeliveryAddressByDID(Integer did) throws AppException {
        RunDeliveryAddressExample example = new RunDeliveryAddressExample();
        Criteria criteria = example.createCriteria();
        criteria.andDidEqualTo(did);
        List<RunDeliveryAddress> list = runDeliveryAddressMapper.selectByExample(example);
        if (!ValidateUtil.isValid(list)) {
            throw new AppException(ResultEnum.DEL_ERROR.getCode(), ResultEnum.DEL_ERROR.getMsg());
        }
        RunDeliveryAddressExample example1 = new RunDeliveryAddressExample();
        Criteria criteria1 = example1.createCriteria();
        criteria1.andDidEqualTo(did);
        runDeliveryAddressMapper.deleteByExample(example1);
    }

    /**
     * 根据地址id获取地址信息
     *
     * @param id
     * @return
     */
    @Override
    public RunDeliveryAddress getRunDeliveryAddressByID(Integer id) throws AppException {
        RunDeliveryAddress address = runDeliveryAddressMapper.selectByPrimaryKey(id);
        if (address == null) {
            throw new AppException(ResultEnum.QUERY_ERROR.getCode(), ResultEnum.QUERY_ERROR.getMsg());
        }
        return address;
    }

    /**
     * 根据配送员id获取所有地址
     *
     * @param did
     * @return
     */
    @Override
    public List<RunDeliveryAddress> getAllRunDeliveryAddressByDID(Integer did) throws AppException {
        RunDeliveryAddressExample example = new RunDeliveryAddressExample();
        Criteria criteria = example.createCriteria();
        criteria.andDidEqualTo(did);
        List<RunDeliveryAddress> list = runDeliveryAddressMapper.selectByExample(example);
        return list;
    }

}
