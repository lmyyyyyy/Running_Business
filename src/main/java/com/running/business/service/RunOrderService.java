package com.running.business.service;

import com.github.pagehelper.PageInfo;
import com.running.business.common.BaseResult;
import com.running.business.dto.InfoDTO;
import com.running.business.exception.AppException;
import com.running.business.pojo.RunOrder;
import com.running.business.pojo.RunOrderPay;
import com.running.business.vo.OrderVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface RunOrderService {

    /**
     * 验证订单是否已被抢
     *
     * @param orderId
     * @param did
     * @return
     * @throws AppException
     */
    boolean checkGrab(String orderId, Integer did) throws AppException;

    /**
     * 生成订单ID(去重)
     *
     * @return
     * @throws AppException
     */
    String generatorOrderId() throws AppException;

    /**
     * 验证订单Id是否存在
     *
     * @param orderId
     * @return
     * @throws AppException
     */
    boolean orderIdIsExist(String orderId) throws AppException;

    void saveRunOrder(RunOrder order) throws AppException;

    /**
     * 更新订单
     *
     * @param order
     * @throws AppException
     */
    void updateRunOrder(RunOrder order) throws AppException;

    /**
     * 配送员抢单
     *
     * @param orderId
     * @param did
     * @throws AppException
     */
    void updateOrderByGrab(String orderId, Integer did) throws AppException;

    /**
     * 更新订单状态
     *
     * @param orderId
     * @param status
     * @throws AppException
     */
    void updateOrderStatus(String orderId, Integer status) throws AppException;

    /**
     * 更新配送员积分
     *
     * @param orderId
     * @throws AppException
     */
    void updateDeliveryPoint(String orderId) throws AppException;

    void deleteRunOrderByOID(String oid) throws AppException;

    void deleteAllRunOrderByUID(Integer uid) throws AppException;

    void deleteAllRunOrderByDID(Integer did) throws AppException;

    /**
     * 根据id获取订单
     *
     * @param oid
     * @return
     */
    OrderVO getRunOrderByOID(String oid) throws AppException;

    /**
     * 根据订单号和用户id查询订单
     *
     * @param orderId
     * @param uid
     * @return
     * @throws AppException
     */
    RunOrder queryOrderByOIDAndUID(String orderId, Integer uid) throws AppException;

    /**
     * 根据用户id查询订单
     *
     * @param uid
     * @return
     */
    BaseResult getAllRunOrderByUID(Integer uid) throws AppException;

    BaseResult getAllRunOrderByUID(Integer uid, Integer currentPage) throws AppException;

    BaseResult getAllRunOrderByDID(Integer did) throws AppException;

    /**
     * 根据配送员id查询订单
     *
     * @param did
     * @param currentPage
     * @return
     */
    BaseResult getAllRunOrderByDID(Integer did, Integer currentPage) throws AppException;

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
    PageInfo<OrderVO> pageRunOrderByDIDAndStatus(Integer did, Integer status, String keyword, Integer page, Integer size, String orderType) throws AppException;

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
    PageInfo<OrderVO> pageRunOrderByPaid(Integer type, Integer did, Double longitude, Double latitude, String good, String keyword, Integer page, Integer size, String orderType) throws AppException;

    BaseResult getAllRunOrder() throws AppException;

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
    PageInfo<OrderVO> pageOrderByUIDAndStatus(String keyword, Integer id, Integer status, Integer page, Integer size, String orderType) throws AppException;

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
    PageInfo<OrderVO> pageOrders(String keyword, Integer type, Integer did, Integer uid, Integer status, Integer page, Integer size, String orderField, String orderType) throws AppException;

    /**
     * 根据用户id或配送员id获取订单数
     *
     * @param uid
     * @param did
     * @return
     * @throws AppException
     */
    Integer orderCountByUIDAndDID(Integer uid, Integer did) throws AppException;

    /**
     * 拼接滚动消息
     *
     * @return
     * @throws AppException
     */
    List<InfoDTO> queryInfoDTO(Long seconds) throws AppException;

    /**
     * 用户支付订单
     *
     * @param orderPay
     * @param request
     * @throws AppException
     */
    void pay(RunOrderPay orderPay, HttpServletRequest request) throws AppException;

    /**
     * 保存或更新用户偏好
     *
     * @param goodType
     * @param good
     * @param uid
     * @throws AppException
     */
    void saveOrUpdatePreference(String goodType, String good, Integer uid) throws AppException;
}
