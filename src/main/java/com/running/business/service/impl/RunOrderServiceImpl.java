package com.running.business.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.running.business.common.BaseResult;
import com.running.business.common.Config;
import com.running.business.common.ResultEnum;
import com.running.business.controller.RunOrderController;
import com.running.business.dto.InfoDTO;
import com.running.business.enums.DistanceMinutesRelationEnum;
import com.running.business.enums.OrderPayTypeEnum;
import com.running.business.enums.OrderStatusEnum;
import com.running.business.enums.OrderTypeEnum;
import com.running.business.enums.PaySourceTypeEnum;
import com.running.business.exception.AppException;
import com.running.business.facade.Cashier;
import com.running.business.mapper.RunOrderMapper;
import com.running.business.mapper.RunUserInfoMapper;
import com.running.business.pojo.RunDeliveryAddress;
import com.running.business.pojo.RunDeliveryDistance;
import com.running.business.pojo.RunDeliveryInfo;
import com.running.business.pojo.RunOrder;
import com.running.business.pojo.RunOrderExample;
import com.running.business.pojo.RunOrderExample.Criteria;
import com.running.business.pojo.RunOrderPay;
import com.running.business.pojo.RunUserInfo;
import com.running.business.pojo.RunUserPreference;
import com.running.business.service.RefundRecordService;
import com.running.business.service.RunDeliveryAddressService;
import com.running.business.service.RunDeliveryDistanceService;
import com.running.business.service.RunDeliveryInfoService;
import com.running.business.service.RunOrderPayService;
import com.running.business.service.RunOrderService;
import com.running.business.service.RunUserInfoService;
import com.running.business.service.RunUserPreferenceService;
import com.running.business.util.DateUtil;
import com.running.business.util.MapDistance;
import com.running.business.util.Run_StringUtil;
import com.running.business.util.ValidateUtil;
import com.running.business.vo.OrderVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RunOrderServiceImpl implements RunOrderService {

    private static Logger LOGGER = LoggerFactory.getLogger(RunOrderServiceImpl.class);

    private static final String LOG_PREFIX = "【订单模块】 ";

    @Autowired
    private RunOrderMapper runOrderMapper;

    @Autowired
    private RunUserInfoMapper runUserInfoMapper;

    @Autowired
    private RunDeliveryInfoService runDeliveryInfoService;

    @Autowired
    private RunUserInfoService runUserInfoService;

    @Autowired
    private Cashier cashier;

    @Autowired
    private RunOrderPayService runOrderPayService;

    @Autowired
    private RunDeliveryDistanceService runDeliveryDistanceService;


    @Autowired
    private RunDeliveryAddressService runDeliveryAddressService;

    @Autowired
    private RefundRecordService refundRecordService;

    @Autowired
    private RunUserPreferenceService runUserPreferenceService;

    /**
     * 验证订单是否已被抢 false:未被抢；true:已被抢
     *
     * @param orderId
     * @param did
     * @return
     * @throws AppException
     */
    @Override
    public boolean checkGrab(String orderId, Integer did) throws AppException {
        if (orderId == null || "".equals(orderId)) {
            throw new AppException(ResultEnum.ORDER_ID_IS_ERROR);
        }
        RunOrderExample example = new RunOrderExample();
        RunOrderExample.Criteria criteria = example.createCriteria();
        criteria.andOrderidEqualTo(orderId);
        criteria.andStatusGreaterThanOrEqualTo(OrderStatusEnum.RECEIVED.getCode());
        List<RunOrder> orders = runOrderMapper.selectByExample(example);
        if (orders == null || orders.isEmpty()) {
            return false;
        }
        return true;
    }

    /**
     * 生成订单ID(去重)
     *
     * @return
     * @throws AppException
     */
    @Override
    public String generatorOrderId() throws AppException {
        String orderId = null;
        List<RunOrder> list = null;
        while (list != null && list.size() != 0) {
            orderId = Run_StringUtil.getOrderId();
            RunOrderExample example = new RunOrderExample();
            RunOrderExample.Criteria criteria = example.createCriteria();
            criteria.andOrderidEqualTo(orderId);
            list = runOrderMapper.selectByExample(example);
        }
        return orderId;
    }

    /**
     * 验证订单Id是否存在
     *
     * @param orderId
     * @return
     * @throws AppException
     */
    @Override
    public boolean orderIdIsExist(String orderId) throws AppException {
        if (orderId == null || "".equals(orderId)) {
            return false;
        }
        RunOrderExample example = new RunOrderExample();
        RunOrderExample.Criteria criteria = example.createCriteria();
        criteria.andOrderidEqualTo(orderId);
        List<RunOrder> list = runOrderMapper.selectByExample(example);
        return ValidateUtil.isValid(list);
    }

    @Override
    public void saveRunOrder(RunOrder order) throws AppException {
        if (order == null) {
            throw new AppException(ResultEnum.ORDER_PARAMETER_IS_EMPTY);
        }
        runOrderMapper.insert(order);
    }

    /**
     * 更新订单
     *
     * @param order
     * @throws AppException
     */
    @Override
    public synchronized void updateRunOrder(RunOrder order) throws AppException {
        if (order == null) {
            throw new AppException(ResultEnum.ORDER_PARAMETER_IS_EMPTY);
        }
        runOrderMapper.updateByPrimaryKeySelective(order);
    }

    /**
     * 配送员抢单
     *
     * @param orderId
     * @param did
     * @throws AppException
     */
    @Override
    public synchronized void updateOrderByGrab(String orderId, Integer did) throws AppException {
        boolean flag = checkGrab(orderId, did);
        if (flag) {
            throw new AppException(ResultEnum.ORDER_HAVEN_BEEN_GRABED);
        }
        RunOrder order = new RunOrder();
        order.setOrderid(orderId);
        order.setDid(did);
        order.setStatus(OrderStatusEnum.RECEIVED.getCode());
        order.setRecvTime(new Date());
        order.setUpdateTime(new Date());
        this.updateRunOrder(order);
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
    public void deleteRunOrderByOID(String oid) throws AppException {
        RunOrder order = runOrderMapper.selectByPrimaryKey(oid);
        if (order == null) {
            throw new AppException(ResultEnum.DEL_ERROR.getCode(), ResultEnum.DEL_ERROR.getMsg());
        }
        runOrderMapper.deleteByPrimaryKey(oid);
    }

    @Override
    public void deleteAllRunOrderByUID(Integer uid) throws AppException {
        RunOrderExample example = new RunOrderExample();
        Criteria criteria = example.createCriteria();
        criteria.andUidEqualTo(uid);
        List<RunOrder> list = runOrderMapper.selectByExample(example);
        if (!ValidateUtil.isValid(list)) {
            throw new AppException(ResultEnum.DEL_ERROR.getCode(), ResultEnum.DEL_ERROR.getMsg());
        }
        RunOrderExample example1 = new RunOrderExample();
        Criteria criteria1 = example1.createCriteria();
        criteria1.andUidEqualTo(uid);
        runOrderMapper.deleteByExample(example1);
    }

    @Override
    public void deleteAllRunOrderByDID(Integer did) throws AppException {
        RunOrderExample example = new RunOrderExample();
        Criteria criteria = example.createCriteria();
        criteria.andDidEqualTo(did);
        List<RunOrder> list = runOrderMapper.selectByExample(example);
        if (!ValidateUtil.isValid(list)) {
            throw new AppException(ResultEnum.DEL_ERROR.getCode(), ResultEnum.DEL_ERROR.getMsg());
        }
        RunOrderExample example1 = new RunOrderExample();
        Criteria criteria1 = example1.createCriteria();
        criteria1.andDidEqualTo(did);
        runOrderMapper.deleteByExample(example1);
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
        if (oid == null || "".equals(oid)) {
            return null;
        }
        RunOrder order = runOrderMapper.selectByPrimaryKey(oid);
        if (order == null) {
            return null;
        }
        return convertOrder2VO(order, null, null);
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
        List<OrderVO> list = convertOrders2VOs(orders, null, null);
        return new PageInfo<>(list);
    }

    /**
     * 模糊分页 查询已支付可抢订单列表
     *
     * @param type
     * @param did
     * @param longitude
     * @param latitude
     * @param good
     * @param keyword
     * @param page
     * @param size
     * @param orderType
     * @return
     * @throws AppException
     */
    @Override
    public PageInfo<OrderVO> pageRunOrderByPaid(Integer type, Integer did, Double longitude, Double latitude, String good, String keyword, Integer page, Integer size, String orderType) throws AppException {
        if (page == null || page <= 0) {
            page = 1;
        }
        if (size == null || size <= 0) {
            size = 20;
        }
        if (orderType == null || "".equals(orderType)) {
            orderType = "DESC";
        }
        RunDeliveryDistance distance = null;
        RunDeliveryInfo info = null;
        RunDeliveryAddress address = null;
        if (did != null) {
            /*info = runDeliveryInfoService.getRunDeliveryInfoByID(did);
            if (info != null && info.getAddressId() != null) {
                address = runDeliveryAddressService.getRunDeliveryAddressByID(info.getAddressId());
            }*/
            distance = runDeliveryDistanceService.getRunDeliveryDistanceByDID(did);
        }
        PageHelper.startPage(page, size);
        RunOrderExample example = new RunOrderExample();
        RunOrderExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo(OrderStatusEnum.PAID.getCode());
        if (good != null && !"".equals(good.trim())) {
            criteria.andGoodsEqualTo(good);
        }
        if (keyword != null && !"".equals(keyword.trim())) {
            criteria.andRemarkLike("%" + keyword.trim() + "%");
        }
        double[] range;
        if (longitude != null && latitude != null) {
            if (distance != null) {
                range = MapDistance.getRectangle(longitude, latitude, distance.getSendDistance().longValue());

                criteria.andDistanceLessThanOrEqualTo(distance.getDeliveryDistance());
            } else {
                range = MapDistance.getRectangle(longitude, latitude, Config.ORDER_TARGET_ADDRESS_DISTANCE);

                criteria.andDistanceLessThanOrEqualTo(Double.valueOf(Config.ORDER_DISTANCE));
            }
            if (type != -1) {
                criteria.andSourceLatitudeBetween(String.valueOf(range[1]), String.valueOf(range[3]));
                criteria.andSourceLongitudeBetween(String.valueOf(range[0]), String.valueOf(range[2]));
                criteria.andTypeEqualTo(type);
            } else {
                criteria.andSourceLatitudeBetween(String.valueOf(range[1]), String.valueOf(range[3]));
                criteria.andSourceLongitudeBetween(String.valueOf(range[0]), String.valueOf(range[2]));
                criteria.andRecvLatitudeBetween(String.valueOf(range[1]), String.valueOf(range[3]));
                criteria.andRecvLongitudeBetween(String.valueOf(range[0]), String.valueOf(range[2]));
            }
        }
        example.setOrderByClause(" add_time " + orderType + ", distance " + orderType);
        List<RunOrder> orders = runOrderMapper.selectByExample(example);
        List<OrderVO> list = convertOrders2VOs(orders, longitude, latitude);
        return new PageInfo<>(orderByOrderVO(list));
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
        List<OrderVO> list = convertOrders2VOs(orders, null, null);
        return new PageInfo<>(list);
    }

    /**
     * 根据配送员ID，用户ID，订单状态，关键字模糊搜索订单列表
     *
     * @param keyword
     * @param type
     * @param did
     * @param uid
     * @param status
     * @param page
     * @param size
     * @param orderField
     * @param orderType
     * @return
     * @throws AppException
     */
    @Override
    public PageInfo<OrderVO> pageOrders(String keyword, Integer type, Integer did, Integer uid, Integer status, Integer page, Integer size, String orderField, String orderType) throws AppException {
        if (page == null || page <= 0) {
            page = 1;
        }
        if (size == null || size <= 0) {
            size = 10;
        }
        if (orderField == null || "".equals(orderField)) {
            orderField = "add_time";
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
        if (uid != null && uid > 0) {
            criteria.andUidEqualTo(uid);
        }
        if (type != null && type >= 0) {
            criteria.andTypeEqualTo(type);
        }
        if (status != null && status >= 0) {
            criteria.andStatusEqualTo(status);
        }
        criteria.andGoodsLike("%" + keyword + "%");
        example.setOrderByClause(orderField + " " + orderType);
        List<RunOrder> orders = runOrderMapper.selectByExample(example);
        List<OrderVO> orderVOS = convertOrders2VOs(orders, null, null);
        return new PageInfo<>(orderVOS);
    }

    /**
     * 根据用户id或配送员id获取订单数
     *
     * @param uid
     * @param did
     * @return
     * @throws AppException
     */
    @Override
    public Integer orderCountByUIDAndDID(Integer uid, Integer did) throws AppException {
        RunOrderExample example = new RunOrderExample();
        RunOrderExample.Criteria criteria = example.createCriteria();
        if (uid != null) {
            criteria.andUidEqualTo(uid);
        }
        if (did != null) {
            criteria.andDidEqualTo(did);
        }
        return runOrderMapper.countByExample(example);
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
     * 用户支付订单
     *
     * @param orderPay
     * @param request
     * @throws AppException
     */
    @Override
    public synchronized void pay(RunOrderPay orderPay, HttpServletRequest request) throws AppException {
        boolean flag = runOrderPayService.checkIsPay(orderPay.getUid(), orderPay.getOrderid());
        if (flag) {
            throw new AppException(ResultEnum.ORDER_HAS_PAY);
        }
        double money = orderPay.getOrderActualPrice();
        PaySourceTypeEnum paySourceTypeEnum = PaySourceTypeEnum.getOrderPayTypeEnum(orderPay.getPayType());
        cashier.pay(paySourceTypeEnum, money, request);
        //保存订单支付记录
        runOrderPayService.saveRunOrderPay(orderPay);
        //更新订单状态
        this.updateOrderStatus(orderPay.getOrderid(), OrderStatusEnum.PAID.getCode());
    }

    /**
     * 保存或更新用户偏好
     *
     * @param goodType
     * @param good
     * @param uid
     * @throws AppException
     */
    @Override
    public void saveOrUpdatePreference(String goodType, String good, Integer uid) throws AppException {
        if (uid == null) {
            throw new AppException(ResultEnum.USER_ID_IS_ERROR);
        }
        runUserPreferenceService.saveRunUserPreference(buildPreference(goodType, good, uid));
    }

    /**
     * 构建用户偏好对象
     *
     * @param goodType
     * @param good
     * @param uid
     * @return
     * @throws AppException
     */
    private RunUserPreference buildPreference(String goodType, String good, Integer uid) throws AppException {
        RunUserPreference preference = new RunUserPreference();
        if (uid == null) {
            throw new AppException(ResultEnum.USER_ID_IS_ERROR);
        }
        preference.setUid(uid);
        preference.setUserGoods(good);
        preference.setUserGoodstype(goodType);
        return preference;
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
    public List<OrderVO> convertOrders2VOs(List<RunOrder> orders, Double longitude, Double latitude) throws AppException {
        if (orders == null || orders.size() == 0) {
            return new ArrayList<>();
        }
        List<OrderVO> orderVOS = new ArrayList<>(orders.size());
        for (RunOrder order : orders) {
            if (order == null) {
                continue;
            }
            OrderVO orderVO = convertOrder2VO(order, longitude, latitude);
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
    public OrderVO convertOrder2VO(RunOrder order, Double longitude, Double latitude) {
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
        OrderPayTypeEnum orderPayTypeEnum = OrderPayTypeEnum.getOrderPayTypeEnum(order.getPayType());
        if (orderPayTypeEnum != null) {
            orderVO.setPayTypeDesc(orderPayTypeEnum.getDesc());
        }
        try {
            RunUserInfo runUserInfo = runUserInfoService.getRunUserInfoById(order.getUid());
            if (runUserInfo != null) {
                orderVO.setUserName(runUserInfo.getUserName());
                orderVO.setUserPhone(runUserInfo.getUserPhoto());
            }
        } catch (Exception e) {
            LOGGER.error("{} 查询用户信息异常 error = {}", LOG_PREFIX, e);
        }
        try {
            RunDeliveryInfo runDeliveryInfo = runDeliveryInfoService.getRunDeliveryInfoByID(order.getDid());
            if (runDeliveryInfo != null) {
                orderVO.setDeliveryName(runDeliveryInfo.getName());
                orderVO.setDeliveryPhone(runDeliveryInfo.getPhone());
            }
        } catch (Exception e) {
            LOGGER.error("{} 查询配送员信息异常 error = {}", LOG_PREFIX, e);
        }
        //预计到达时间
        if (order.getStatus() >= OrderStatusEnum.RECEIVED.getCode() && order.getStatus() <= OrderStatusEnum.SENDING.getCode()) {
            Long resultTime = DistanceMinutesRelationEnum.getOrderTypeEnum(order.getDistance()).getMs();
            resultTime += order.getAddTime().getTime();
            orderVO.setProbablyArriveTime(DateUtil.ms2Date(resultTime));
        }
        if (longitude != null && latitude != null) {
            Double distanceDesc = MapDistance.getDistanceOfMeter(longitude, latitude, Double.valueOf(order.getSourceLongitude()), Double.valueOf(order.getSourceLatitude()));
            orderVO.setDistanceDesc(distanceDesc);
        }
        return orderVO;
    }

    /**
     * 按照距离排序
     *
     * @param orderVOS
     * @return
     * @throws Exception
     */
    public List<OrderVO> orderByOrderVO(List<OrderVO> orderVOS) throws AppException {
        return orderVOS.stream().sorted(Comparator.comparing(OrderVO::getDistanceDesc)).collect(Collectors.toList());
    }

}
