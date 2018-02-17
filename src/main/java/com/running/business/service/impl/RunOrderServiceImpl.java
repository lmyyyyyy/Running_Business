package com.running.business.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.running.business.common.BaseResult;
import com.running.business.common.ResultEnum;
import com.running.business.dto.InfoDTO;
import com.running.business.enums.DistanceMinutesRelationEnum;
import com.running.business.enums.OrderPayTypeEnum;
import com.running.business.enums.OrderStatusEnum;
import com.running.business.enums.OrderTypeEnum;
import com.running.business.exception.AppException;
import com.running.business.mapper.RunOrderMapper;
import com.running.business.mapper.RunUserInfoMapper;
import com.running.business.pojo.RunDeliveryInfo;
import com.running.business.pojo.RunOrder;
import com.running.business.pojo.RunOrderExample;
import com.running.business.pojo.RunOrderExample.Criteria;
import com.running.business.pojo.RunUserInfo;
import com.running.business.service.RunDeliveryInfoService;
import com.running.business.service.RunOrderService;
import com.running.business.service.RunUserInfoService;
import com.running.business.util.DateUtil;
import com.running.business.util.ValidateUtil;
import com.running.business.vo.OrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class RunOrderServiceImpl implements RunOrderService {

    @Autowired
    private RunOrderMapper runOrderMapper;

    @Autowired
    private RunUserInfoMapper runUserInfoMapper;

    @Autowired
    private RunDeliveryInfoService runDeliveryInfoService;

    @Autowired
    private RunUserInfoService runUserInfoService;

    @Override
    public BaseResult saveRunOrder(RunOrder order) throws AppException {
        runOrderMapper.insert(order);
        return BaseResult.success();
    }

    @Override
    public BaseResult updateRunOrder(RunOrder order) throws AppException {
        runOrderMapper.updateByPrimaryKey(order);
        return BaseResult.success(order);
    }

    /**
     * 更新订单状态
     *
     * @param orderId
     * @param status
     * @throws AppException
     */
    @Override
    public void updateOrderStatus(String orderId, Integer status) throws AppException {
        if (orderId == null || "".equals(orderId)) {
            throw new AppException(ResultEnum.ORDER_ID_IS_ERROR);
        }
        if (status == null || status < 0) {
            throw new AppException(ResultEnum.ORDER_STATUS_IS_ERROR);
        }
        RunOrder order = new RunOrder();
        order.setOrderid(orderId);
        order.setStatus(status);
        order.setUpdateTime(new Date());
        if (status.equals(OrderStatusEnum.SENDING)) {
            order.setTargetTime(new Date());
        } else if (status.equals(OrderStatusEnum.FINISH)) {
            order.setFinishTime(new Date());
        } else if (status.equals(OrderStatusEnum.RECEIVED)) {
            order.setRecvTime(new Date());
        }
        runOrderMapper.updateByPrimaryKeySelective(order);
    }

    @Override
    public BaseResult deleteRunOrderByOID(String oid) throws AppException {
        RunOrder order = runOrderMapper.selectByPrimaryKey(oid);
        if (order == null) {
            return BaseResult.fail(ResultEnum.DEL_ERROR.getCode(), ResultEnum.DEL_ERROR.getMsg());
        }
        runOrderMapper.deleteByPrimaryKey(oid);
        return BaseResult.success(order);
    }

    @Override
    public BaseResult deleteAllRunOrderByUID(Integer uid) throws AppException {
        RunOrderExample example = new RunOrderExample();
        Criteria criteria = example.createCriteria();
        criteria.andUidEqualTo(uid);
        List<RunOrder> list = runOrderMapper.selectByExample(example);
        if (!ValidateUtil.isValid(list)) {
            return BaseResult.fail(ResultEnum.DEL_ERROR.getCode(), ResultEnum.DEL_ERROR.getMsg());
        }
        RunOrderExample example1 = new RunOrderExample();
        Criteria criteria1 = example1.createCriteria();
        criteria1.andUidEqualTo(uid);
        runOrderMapper.deleteByExample(example1);
        return BaseResult.success();
    }

    @Override
    public BaseResult deleteAllRunOrderByDID(Integer did) throws AppException {
        RunOrderExample example = new RunOrderExample();
        Criteria criteria = example.createCriteria();
        criteria.andDidEqualTo(did);
        List<RunOrder> list = runOrderMapper.selectByExample(example);
        if (!ValidateUtil.isValid(list)) {
            return BaseResult.fail(ResultEnum.DEL_ERROR.getCode(), ResultEnum.DEL_ERROR.getMsg());
        }
        RunOrderExample example1 = new RunOrderExample();
        Criteria criteria1 = example1.createCriteria();
        criteria1.andDidEqualTo(did);
        runOrderMapper.deleteByExample(example1);
        return BaseResult.success();
    }

    /**
     * 根据订单id获取订单
     *
     * @param oid
     * @return
     * @throws AppException
     */
    @Override
    public OrderVO getRunOrderByOID(String oid) throws AppException {
        RunOrder order = runOrderMapper.selectByPrimaryKey(oid);
        if (order == null) {
            return null;
        }
        return convertOrder2VO(order);
    }

    /**
     * 根据订单号和用户id查询订单
     *
     * @param orderId
     * @param uid
     * @return
     * @throws AppException
     */
    @Override
    public RunOrder queryOrderByOIDAndUID(String orderId, Integer uid) throws AppException {
        if (orderId == null || "".equals(orderId)) {
            throw new AppException(ResultEnum.ORDER_ID_IS_ERROR);
        }
        if (uid == null || uid <= 0) {
            throw new AppException(ResultEnum.USER_ID_IS_ERROR);
        }
        RunOrderExample example = new RunOrderExample();
        RunOrderExample.Criteria criteria = example.createCriteria();
        criteria.andOrderidEqualTo(orderId);
        criteria.andUidEqualTo(uid);
        criteria.andStatusNotIn(Arrays.asList(OrderStatusEnum.SUBMMIT.getCode(), OrderStatusEnum.UNPAID.getCode()));
        List<RunOrder> orders = runOrderMapper.selectByExample(example);
        if (orders == null || orders.isEmpty()) {
            return null;
        }
        return orders.get(0);
    }

    @Override
    public BaseResult getAllRunOrderByUID(Integer uid) throws AppException {
        RunOrderExample example = new RunOrderExample();
        Criteria criteria = example.createCriteria();
        criteria.andUidEqualTo(uid);
        List<RunOrder> list = runOrderMapper.selectByExample(example);
        if (!ValidateUtil.isValid(list)) {
            return BaseResult.fail(ResultEnum.NOT_MSG.getCode(), ResultEnum.NOT_MSG.getMsg());
        }
        return BaseResult.success(list);
    }

    /**
     * 根据用户ID查询订单
     *
     * @param uid         用户ID
     * @param currentPage 当前页
     * @return
     */
    @Override
    public BaseResult getAllRunOrderByUID(Integer uid, Integer currentPage) throws AppException {
        RunOrderExample example = new RunOrderExample();
        Criteria criteria = example.createCriteria();
        criteria.andUidEqualTo(uid);
        PageHelper.startPage(currentPage, 20);
        List<RunOrder> list = runOrderMapper.selectByExample(example);
        if (!ValidateUtil.isValid(list)) {
            return BaseResult.fail(ResultEnum.NOT_MSG.getCode(), ResultEnum.NOT_MSG.getMsg());
        }
        PageInfo<RunOrder> pageInfo = new PageInfo<RunOrder>(list);
        return BaseResult.success(pageInfo);
    }

    @Override
    public BaseResult getAllRunOrderByDID(Integer did) throws AppException {
        RunOrderExample example = new RunOrderExample();
        Criteria criteria = example.createCriteria();
        criteria.andDidEqualTo(did);
        List<RunOrder> list = runOrderMapper.selectByExample(example);
        if (!ValidateUtil.isValid(list)) {
            return BaseResult.fail(ResultEnum.NOT_MSG.getCode(), ResultEnum.NOT_MSG.getMsg());
        }
        return BaseResult.success(list);
    }

    /**
     * 根据配送员ID查询订单
     *
     * @param did         配送员ID
     * @param currentPage 当前页
     * @return
     */
    @Override
    public BaseResult getAllRunOrderByDID(Integer did, Integer currentPage) throws AppException {
        RunOrderExample example = new RunOrderExample();
        Criteria criteria = example.createCriteria();
        criteria.andDidEqualTo(did);
        PageHelper.startPage(currentPage, 20);
        List<RunOrder> list = runOrderMapper.selectByExample(example);
        if (!ValidateUtil.isValid(list)) {
            return BaseResult.fail(ResultEnum.NOT_MSG.getCode(), ResultEnum.NOT_MSG.getMsg());
        }
        PageInfo<RunOrder> pageInfo = new PageInfo<>(list);
        return BaseResult.success(pageInfo);
    }

    /**
     * 模糊分页 根据配送员id和状态查询订单
     *
     * @param did
     * @param status
     * @param keyword
     * @param page
     * @param size
     * @param orderType
     * @return
     * @throws AppException
     */
    @Override
    public PageInfo<OrderVO> pageRunOrderByDIDAndStatus(Integer did, Integer status, String keyword, Integer page, Integer size, String orderType) throws AppException {
        if (did == null || did <= 0) {
            throw new AppException(ResultEnum.DELIVERY_ID_IS_ERROR);
        }
        if (page == null || page <= 0) {
            page = 1;
        }
        if (size == null || size <= 0) {
            size = 10;
        }
        if (orderType == null || "".equals(orderType)) {
            orderType = "DESC";
        }
        PageHelper.startPage(page, size);
        RunOrderExample example = new RunOrderExample();
        RunOrderExample.Criteria criteria = example.createCriteria();
        if (did != null && did > 0) {
            criteria.andDidEqualTo(did);
        }
        if (status != -1) {
            criteria.andStatusEqualTo(status);
        }
        if (keyword != null && !"".equals(keyword.trim())) {
            criteria.andRemarkLike("%" + keyword.trim() + "%");
        }
        example.setOrderByClause(" add_time " + orderType);
        List<RunOrder> orders = runOrderMapper.selectByExample(example);
        List<OrderVO> list = convertOrders2VOs(orders);
        return new PageInfo<>(list);
    }

    @Override
    public BaseResult getAllRunOrder() throws AppException {
        RunOrderExample example = new RunOrderExample();
        List<RunOrder> list = runOrderMapper.selectByExample(example);
        if (!ValidateUtil.isValid(list)) {
            return BaseResult.fail(ResultEnum.NOT_MSG.getCode(), ResultEnum.NOT_MSG.getMsg());
        }
        return BaseResult.success(list);
    }

    /**
     * 模糊分页 根据用户id和状态查询订单
     *
     * @param keyword
     * @param id
     * @param status
     * @param page
     * @param size
     * @param orderType
     * @return
     * @throws AppException
     */
    @Override
    public PageInfo<OrderVO> pageOrderByUIDAndStatus(String keyword, Integer id, Integer status, Integer page, Integer size, String orderType) throws AppException {
        if (id == null || id <= 0) {
            throw new AppException(ResultEnum.DELIVERY_ID_IS_ERROR);
        }
        if (page == null || page <= 0) {
            page = 1;
        }
        if (size == null || size <= 0) {
            size = 10;
        }
        if (orderType == null || "".equals(orderType)) {
            orderType = "DESC";
        }
        PageHelper.startPage(page, size);
        RunOrderExample example = new RunOrderExample();
        example.setOrderByClause("add_time " + orderType);
        Criteria criteria = example.createCriteria();
        if (id != null && id > 0) {
            criteria.andUidEqualTo(id);
        }
        if (status != -1) {
            criteria.andStatusEqualTo(status);
        }
        if (keyword != null && !"".equals(keyword.trim())) {
            criteria.andRemarkLike("%" + keyword.trim() + "%");
        }
        List<RunOrder> orders = runOrderMapper.selectByExample(example);
        List<OrderVO> list = convertOrders2VOs(orders);
        return new PageInfo<>(list);
    }

    /**
     * 拼接滚动消息
     *
     * @return
     * @throws AppException
     */
    @Override
    public List<InfoDTO> queryInfoDTO(Long seconds) throws AppException {
        RunOrderExample example = new RunOrderExample();
        RunOrderExample.Criteria criteria = example.createCriteria();
        Long endTime = System.currentTimeMillis();
        Long beginTime = endTime - seconds;
        criteria.andAddTimeBetween(DateUtil.ms2Date(beginTime), DateUtil.ms2Date(endTime));
        List<RunOrder> orders = runOrderMapper.selectByExample(example);

        return convertOrder2Info(orders);
    }

    /**
     * 将订单拼接成滚动消息
     *
     * @param orders
     * @return
     * @throws AppException
     */
    private List<InfoDTO> convertOrder2Info(List<RunOrder> orders) throws AppException {
        if (orders == null || orders.size() == 0) {
            return new ArrayList<>();
        }
        List<InfoDTO> infoDTOS = new ArrayList<>();
        for (RunOrder order : orders) {
            StringBuffer result = new StringBuffer();
            if (order == null) {
                continue;
            }
            RunUserInfo info = runUserInfoMapper.selectByPrimaryKey(order.getUid());
            if (info == null) {
                continue;
            }
            result.append(info.getUserName());
            result.append("在");
            result.append(DateUtil.calculateTime(order.getAddTime()));
            result.append("购买了");
            result.append(order.getGoods());
            InfoDTO infoDTO = new InfoDTO();
            infoDTO.setContent(result.toString());
            infoDTO.setInfoTime(order.getAddTime());
            infoDTOS.add(infoDTO);
        }
        return infoDTOS;
    }

    /**
     * 订单集合转VO集合
     *
     * @param orders
     * @return
     * @throws AppException
     */
    public List<OrderVO> convertOrders2VOs(List<RunOrder> orders) throws AppException {
        if (orders == null || orders.size() == 0) {
            return new ArrayList<>();
        }
        List<OrderVO> orderVOS = new ArrayList<>(orders.size());
        for (RunOrder order : orders) {
            if (order == null) {
                continue;
            }
            OrderVO orderVO = convertOrder2VO(order);
            if (orderVO == null) {
                continue;
            }
            orderVOS.add(orderVO);
        }
        return orderVOS;
    }

    /**
     * 订单转VO
     *
     * @param order
     * @return
     */
    public OrderVO convertOrder2VO(RunOrder order) {
        if (order == null) {
            return null;
        }
        OrderVO orderVO = new OrderVO();
        orderVO.setOrderid(order.getOrderid());
        orderVO.setUid(order.getUid());
        orderVO.setType(order.getType());
        orderVO.setOrderTypeDesc(OrderTypeEnum.getOrderTypeEnum(order.getType()).getDesc());
        orderVO.setStatus(order.getStatus());
        orderVO.setOrderStatusDesc(OrderStatusEnum.getOrderStatusEnum(order.getStatus()).getDesc());
        orderVO.setAddTime(order.getAddTime());
        orderVO.setUpdateTime(order.getUpdateTime());
        orderVO.setAmount(order.getAmount());
        orderVO.setDid(order.getDid());
        orderVO.setDistance(order.getDistance());
        orderVO.setFee(order.getFee());
        orderVO.setFinishTime(order.getFinishTime());
        orderVO.setGoods(order.getGoods());
        orderVO.setPayAmout(order.getPayAmout());
        orderVO.setRecvLatitude(order.getRecvLatitude());
        orderVO.setRecvLongitude(order.getRecvLongitude());
        orderVO.setRecvTime(order.getRecvTime());
        orderVO.setRemark(order.getRemark());
        orderVO.setRequireTime(order.getRequireTime());
        orderVO.setSourceAddress(order.getSourceAddress());
        orderVO.setSourceLatitude(order.getSourceLatitude());
        orderVO.setSourceLongitude(order.getSourceLongitude());
        orderVO.setSourcePhone(order.getSourcePhone());
        orderVO.setSourceRemarkAddress(order.getSourceRemarkAddress());
        orderVO.setTargetAddress(order.getTargetAddress());
        orderVO.setTargetPhone(order.getTargetPhone());
        orderVO.setTargetRemarkAddress(order.getTargetRemarkAddress());
        orderVO.setTargetTime(order.getTargetTime());
        orderVO.setTimeLong(order.getTimeLong());
        orderVO.setPayType(order.getPayType());
        orderVO.setPayTypeDesc(OrderPayTypeEnum.getOrderPayTypeEnum(order.getPayType()).getDesc());
        RunUserInfo runUserInfo = runUserInfoService.getRunUserInfoById(order.getUid());
        if (runUserInfo != null) {
            orderVO.setUserName(runUserInfo.getUserName());
            orderVO.setUserPhone(runUserInfo.getUserPhoto());
        }
        RunDeliveryInfo runDeliveryInfo = runDeliveryInfoService.getRunDeliveryInfoByID(order.getDid());
        if (runDeliveryInfo != null) {
            orderVO.setDeliveryName(runDeliveryInfo.getName());
            orderVO.setDeliveryPhone(runDeliveryInfo.getPhone());
        }
        Long resultTime = DistanceMinutesRelationEnum.getOrderTypeEnum(order.getDistance()).getMs();
        orderVO.setProbablyArriveTime(DateUtil.ms2Date(resultTime));
        return orderVO;
    }

}
