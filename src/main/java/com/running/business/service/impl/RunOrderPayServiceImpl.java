package com.running.business.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.running.business.common.BaseResult;
import com.running.business.common.ResultEnum;
import com.running.business.exception.AppException;
import com.running.business.mapper.RunOrderPayMapper;
import com.running.business.pojo.RunOrderPay;
import com.running.business.pojo.RunOrderPayExample;
import com.running.business.pojo.RunOrderPayExample.Criteria;
import com.running.business.service.RunOrderPayService;
import com.running.business.util.ValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RunOrderPayServiceImpl implements RunOrderPayService {

    @Autowired
    private RunOrderPayMapper runOrderPayMapper;

    /**
     * 检查该订单是否支付过 true:支付过；false:未支付过
     *
     * @param uid
     * @param orderId
     * @return
     * @throws AppException
     */
    @Override
    public boolean checkIsPay(Integer uid, String orderId) throws AppException {
        RunOrderPayExample example = new RunOrderPayExample();
        RunOrderPayExample.Criteria criteria = example.createCriteria();
        criteria.andUidEqualTo(uid);
        criteria.andOrderidEqualTo(orderId);
        List<RunOrderPay> runOrderPays = runOrderPayMapper.selectByExample(example);
        if (runOrderPays == null || runOrderPays.isEmpty()) {
            return false;
        }
        return true;
    }

    /**
     * 保存支付记录
     *
     * @param pay
     * @throws AppException
     */
    @Override
    public void saveRunOrderPay(RunOrderPay pay) throws AppException {
        if (pay == null) {
            throw new AppException(ResultEnum.ORDER_PAY_INFO_EMPTY);
        }
        pay.setPayTime(new Date());
        runOrderPayMapper.insert(pay);
    }

    @Override
    public void updateRunOrderPay(RunOrderPay pay) throws AppException {
        if (pay == null) {
            throw new AppException(ResultEnum.ORDER_PAY_INFO_EMPTY);
        }
        runOrderPayMapper.updateByPrimaryKeySelective(pay);
    }

    @Override
    public void deleteRunOrderPayByID(Integer id) throws AppException {
        RunOrderPay pay = runOrderPayMapper.selectByPrimaryKey(id);
        if (pay == null) {
            throw new AppException(ResultEnum.DEL_ERROR.getCode(), ResultEnum.DEL_ERROR.getMsg());
        }
        runOrderPayMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteRunOrderPayByOID(String oid) throws AppException {
        RunOrderPayExample example = new RunOrderPayExample();
        Criteria criteria = example.createCriteria();
        criteria.andOrderidEqualTo(oid);
        List<RunOrderPay> list = runOrderPayMapper.selectByExample(example);
        if (!ValidateUtil.isValid(list)) {
            throw new AppException(ResultEnum.DEL_ERROR.getCode(), ResultEnum.DEL_ERROR.getMsg());
        }
        RunOrderPayExample example1 = new RunOrderPayExample();
        Criteria criteria1 = example1.createCriteria();
        criteria1.andOrderidEqualTo(oid);
        runOrderPayMapper.deleteByExample(example1);
    }

    @Override
    public void deleteAllRunOrderByUID(Integer uid) throws AppException {
        RunOrderPayExample example = new RunOrderPayExample();
        Criteria criteria = example.createCriteria();
        criteria.andUidEqualTo(uid);
        List<RunOrderPay> list = runOrderPayMapper.selectByExample(example);
        if (!ValidateUtil.isValid(list)) {
            throw new AppException(ResultEnum.DEL_ERROR.getCode(), ResultEnum.DEL_ERROR.getMsg());
        }
        RunOrderPayExample example1 = new RunOrderPayExample();
        Criteria criteria1 = example1.createCriteria();
        criteria1.andUidEqualTo(uid);
        runOrderPayMapper.deleteByExample(example1);
    }

    @Override
    public BaseResult getRunOrderPayByID(Integer id) throws AppException {
        RunOrderPay pay = runOrderPayMapper.selectByPrimaryKey(id);
        if (pay == null) {
            throw new AppException(ResultEnum.QUERY_ERROR.getCode(), ResultEnum.QUERY_ERROR.getMsg());
        }
        return BaseResult.success(pay);
    }

    @Override
    public BaseResult getRunOrderPayByOID(String oid) throws AppException {
        RunOrderPayExample example = new RunOrderPayExample();
        Criteria criteria = example.createCriteria();
        criteria.andOrderidEqualTo(oid);
        List<RunOrderPay> list = runOrderPayMapper.selectByExample(example);
        if (!ValidateUtil.isValid(list)) {
            throw new AppException(ResultEnum.ORDER_PAY_INFO_EMPTY.getCode(), ResultEnum.ORDER_PAY_INFO_EMPTY.getMsg());
        }
        return BaseResult.success(list);
    }

    @Override
    public BaseResult getAllRunOrderPayByUID(Integer uid) throws AppException {
        RunOrderPayExample example = new RunOrderPayExample();
        Criteria criteria = example.createCriteria();
        criteria.andUidEqualTo(uid);
        List<RunOrderPay> list = runOrderPayMapper.selectByExample(example);
        if (!ValidateUtil.isValid(list)) {
            throw new AppException(ResultEnum.NOT_MSG.getCode(), ResultEnum.NOT_MSG.getMsg());
        }
        return BaseResult.success(list);
    }

    @Override
    public BaseResult getAllRunOrderPay() throws AppException {
        RunOrderPayExample example = new RunOrderPayExample();
        List<RunOrderPay> list = runOrderPayMapper.selectByExample(example);
        if (!ValidateUtil.isValid(list)) {
            throw new AppException(ResultEnum.NOT_MSG.getCode(), ResultEnum.NOT_MSG.getMsg());
        }
        return BaseResult.success(list);
    }

    /**
     * 根据订单号和用户id获取支付信息
     *
     * @param orderId
     * @param uid
     * @return
     * @throws AppException
     */
    @Override
    public RunOrderPay queryPayByOIDAndUID(String orderId, Integer uid) throws AppException {
        if (orderId == null || "".equals(orderId)) {
            throw new AppException(ResultEnum.ORDER_ID_IS_ERROR);
        }
        RunOrderPayExample example = new RunOrderPayExample();
        RunOrderPayExample.Criteria criteria = example.createCriteria();
        criteria.andOrderidEqualTo(orderId);
        if (uid != null && uid > 0) {
            criteria.andUidEqualTo(uid);
        }
        List<RunOrderPay> list = runOrderPayMapper.selectByExample(example);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    /**
     * 根据用户id获取支付总数
     *
     * @param uid
     * @return
     * @throws AppException
     */
    @Override
    public Integer payCountByUIDOrDId(Integer uid) throws AppException {
        RunOrderPayExample example = new RunOrderPayExample();
        RunOrderPayExample.Criteria criteria = example.createCriteria();
        if (uid != null) {
            criteria.andUidEqualTo(uid);
        }
        return runOrderPayMapper.countByExample(example);
    }

    /**
     * 根据用户id和支付类型分页查询支付记录
     *
     * @param uid
     * @param type
     * @param page
     * @param size
     * @param orderType
     * @return
     * @throws AppException
     */
    @Override
    public PageInfo<RunOrderPay> pagePaysByUIDAndType(Integer uid, Integer type, Integer page, Integer size, String orderType) throws AppException {
        if (page == null || page <= 0) {
            page = 1;
        }
        if (size == null || size <= 0) {
            size = 20;
        }
        if (orderType == null || "".equals(orderType)) {
            orderType = "DESC";
        }
        PageHelper.startPage(page, size);
        RunOrderPayExample example = new RunOrderPayExample();
        RunOrderPayExample.Criteria criteria = example.createCriteria();
        if (uid != null && uid > 0) {
            criteria.andUidEqualTo(uid);
        }
        if (type != null && type >= 0) {
            criteria.andPayTypeEqualTo(type);
        }
        example.setOrderByClause(" pay_time " + orderType);
        List<RunOrderPay> pays = runOrderPayMapper.selectByExample(example);
        return new PageInfo<>(pays);
    }
}
