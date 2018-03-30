package com.running.business.service;

import com.github.pagehelper.PageInfo;
import com.running.business.dto.RefundDTO;
import com.running.business.exception.AppException;
import com.running.business.pojo.RefundApply;
import com.running.business.vo.RefundApplyVO;

/**
 * @author liumingyu
 * @create 2018-02-02 上午10:34
 */
public interface RefundApplyService {

    /**
     * 验证该用户下的订单是否已退过款 false：没退过，true：退过
     *
     * @param orderId
     * @param uid
     * @return
     * @throws AppException
     */
    boolean checkRefundApply(String orderId, Integer uid) throws AppException;

    /**
     * 创建退款申请
     *
     * @param apply
     * @throws AppException
     */
    void saveRefundApply(RefundApply apply) throws AppException;

    /**
     * 更新退款申请
     *
     * @param apply
     * @throws AppException
     */
    void updateRefundApply(RefundApply apply) throws AppException;

    /**
     * 更新退款申请状态
     *
     * @param refundDTO
     */
    void updateApplyStatus(RefundDTO refundDTO) throws AppException;

    /**
     * 根据申请id删除退款申请
     *
     * @param id
     * @throws AppException
     */
    void deleteRefundApplyByID(Integer id) throws AppException;

    /**
     * 根据订单号删除退款申请
     *
     * @param orderId
     * @throws AppException
     */
    void deleteRefundApplyByOrderid(String orderId) throws AppException;

    /**
     * 根据用户id批量删除退款申请
     *
     * @param uid
     * @throws AppException
     */
    void deleteAllApplyByUID(Integer uid) throws AppException;

    /**
     * 根据id查询退款信息
     *
     * @param id
     * @return
     * @throws AppException
     */
    RefundApplyVO queryApplyByID(Integer id) throws AppException;

    /**
     * 根据订单号和用户id查询退款信息
     *
     * @param orderId
     * @return
     * @throws AppException
     */
    RefundApplyVO queryApplyByOrderID(String orderId, Integer uid) throws AppException;

    /**
     * 根据用户id和状态分页查询退款申请
     *
     * @param uid
     * @param status
     * @param page
     * @param size
     * @param orderField
     * @param orderType
     * @return
     * @throws AppException
     */
    PageInfo<RefundApplyVO> pageApplysByUID(Integer uid, Integer status, Integer page, Integer size, String orderField, String orderType) throws AppException;

    /**
     * 根据状态分页查询退款申请
     *
     * @param status
     * @param page
     * @param size
     * @param orderField
     * @param orderType
     * @return
     * @throws AppException
     */
    PageInfo<RefundApplyVO> pageApplys(Integer status, Integer page, Integer size, String orderField, String orderType) throws AppException;

    /**
     * 根据操作人id分页查询处理过的退款申请
     *
     * @param operatorId
     * @param page
     * @param size
     * @param orderField
     * @param orderType
     * @return
     * @throws AppException
     */
    PageInfo<RefundApplyVO> pageApplysByOperatorId(Integer operatorId, Integer page, Integer size, String orderField, String orderType) throws AppException;

    /**
     * 获取当前用户的退款申请数
     *
     * @param uid
     * @return
     * @throws AppException
     */
    Integer applyCountByUID(Integer uid) throws AppException;
}
