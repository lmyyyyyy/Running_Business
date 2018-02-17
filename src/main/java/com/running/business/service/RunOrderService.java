package com.running.business.service;

import com.github.pagehelper.PageInfo;
import com.running.business.common.BaseResult;
import com.running.business.dto.InfoDTO;
import com.running.business.exception.AppException;
import com.running.business.pojo.RunOrder;
import com.running.business.vo.OrderVO;

import java.util.List;

public interface RunOrderService {

    BaseResult saveRunOrder(RunOrder order) throws AppException;

    BaseResult updateRunOrder(RunOrder order) throws AppException;

    /**
     * 更新订单状态
     *
     * @param orderId
     * @param status
     * @throws AppException
     */
    void updateOrderStatus(String orderId, Integer status) throws AppException;

    BaseResult deleteRunOrderByOID(String oid) throws AppException;

    BaseResult deleteAllRunOrderByUID(Integer uid) throws AppException;

    BaseResult deleteAllRunOrderByDID(Integer did) throws AppException;

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
     * 拼接滚动消息
     *
     * @return
     * @throws AppException
     */
    List<InfoDTO> queryInfoDTO(Long seconds) throws AppException;
}
