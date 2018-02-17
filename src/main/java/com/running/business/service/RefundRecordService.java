package com.running.business.service;

import com.github.pagehelper.PageInfo;
import com.running.business.exception.AppException;
import com.running.business.pojo.RefundRecord;
import com.running.business.vo.RefundRecordVO;

/**
 * @author liumingyu
 * @create 2018-02-15 上午9:47
 */
public interface RefundRecordService {

    /**
     * 保存退款记录
     *
     * @param refundRecord
     * @throws AppException
     */
    void saveRefund(RefundRecord refundRecord) throws AppException;

    /**
     * 更新退款记录
     *
     * @param refundRecord
     * @throws AppException
     */
    void updateRefund(RefundRecord refundRecord) throws AppException;

    /**
     * 根据主键id删除退款记录
     *
     * @param id
     * @throws AppException
     */
    void deleteRefundById(Integer id) throws AppException;

    /**
     * 根据用户id删除所有退款记录
     *
     * @param uid
     * @throws AppException
     */
    void deleteRefundByUID(Integer uid) throws AppException;

    /**
     * 根据订单id删除退款记录
     *
     * @param orderId
     * @throws AppException
     */
    void deleteRefundByOID(String orderId) throws AppException;

    /**
     * 根据配送员id删除退款记录
     *
     * @param did
     * @throws AppException
     */
    void deleteRefundByDID(Integer did) throws AppException;

    /**
     * 根据主键id查询退款记录
     *
     * @param id
     * @return
     * @throws AppException
     */
    RefundRecordVO queryRefundByID(Integer id) throws AppException;

    /**
     * 根据订单id获取退款记录
     *
     * @param orderId
     * @return
     * @throws AppException
     */
    RefundRecordVO queryRefundByOID(String orderId) throws AppException;

    /**
     * 根据用户id分页查询退款记录
     *
     * @param uid
     * @param page
     * @param size
     * @param orderField
     * @param orderType
     * @return
     * @throws AppException
     */
    PageInfo<RefundRecordVO> pageRefundByUID(Integer uid, Integer page, Integer size, String orderField, String orderType) throws AppException;

    /**
     * 根据配送员id分页查询退款记录
     *
     * @param did
     * @param page
     * @param size
     * @param orderField
     * @param orderType
     * @return
     * @throws AppException
     */
    PageInfo<RefundRecordVO> pageRefundByDID(Integer did, Integer page, Integer size, String orderField, String orderType) throws AppException;

    /**
     * 根据操作人id分页查询退款记录
     *
     * @param operatorId
     * @param page
     * @param size
     * @param orderField
     * @param orderType
     * @return
     * @throws AppException
     */
    PageInfo<RefundRecordVO> pageRefundByOperatorId(Integer operatorId, Integer page, Integer size, String orderField, String orderType) throws AppException;
}