package com.running.business.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.running.business.common.ResultEnum;
import com.running.business.dto.RefundDTO;
import com.running.business.enums.OrderStatusEnum;
import com.running.business.enums.RefundApplyStatusEnum;
import com.running.business.exception.AppException;
import com.running.business.mapper.RefundApplyMapper;
import com.running.business.pojo.RefundApply;
import com.running.business.pojo.RefundApplyExample;
import com.running.business.pojo.RefundRecord;
import com.running.business.pojo.RunAdminInfo;
import com.running.business.pojo.RunOrder;
import com.running.business.pojo.RunOrderPay;
import com.running.business.pojo.RunUserBalance;
import com.running.business.pojo.RunUserBalanceRecord;
import com.running.business.pojo.RunUserInfo;
import com.running.business.service.RefundApplyService;
import com.running.business.service.RefundRecordService;
import com.running.business.service.RunAdminInfoService;
import com.running.business.service.RunOrderPayService;
import com.running.business.service.RunOrderService;
import com.running.business.service.RunUserBalanceRecordService;
import com.running.business.service.RunUserBalanceService;
import com.running.business.service.RunUserInfoService;
import com.running.business.util.RandomUtil;
import com.running.business.vo.RefundApplyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author liumingyu
 * @create 2018-02-02 上午10:35
 */
@Service
public class RefundApplyServiceImpl implements RefundApplyService {

    @Autowired
    private RefundApplyMapper refundApplyMapper;

    @Autowired
    private RunOrderService runOrderService;

    @Autowired
    private RunUserInfoService runUserInfoService;

    @Autowired
    private RunAdminInfoService runAdminInfoService;

    @Autowired
    private RunOrderPayService runOrderPayService;

    @Autowired
    private RefundRecordService refundRecordService;

    @Autowired
    private RunUserBalanceService runUserBalanceService;

    @Autowired
    private RunUserBalanceRecordService runUserBalanceRecordService;

    /**
     * 验证该用户下的订单是否已退过款 false：没退过，true：退过
     *
     * @param orderId
     * @param uid
     * @return
     * @throws AppException
     */
    @Override
    public boolean checkRefundApply(String orderId, Integer uid) throws AppException {
        if (orderId == null || "".equals(orderId)) {
            throw new AppException(ResultEnum.REFUND_ORDERID_IS_ERROR);
        }
        RefundApplyExample example = new RefundApplyExample();
        RefundApplyExample.Criteria criteria = example.createCriteria();
        criteria.andOrderidEqualTo(orderId);
        criteria.andUidEqualTo(uid);
        List<RefundApply> applies = refundApplyMapper.selectByExample(example);
        if (applies == null || applies.isEmpty()) {
            return false;
        }
        return true;
    }

    /**
     * 创建退款申请
     *
     * @param apply
     * @throws AppException
     */
    @Override
    public void saveRefundApply(RefundApply apply) throws AppException {
        if (checkRefundApply(apply.getOrderid(), apply.getUid())) {
            throw new AppException(ResultEnum.REFUND_HAS_BEEN_APPLIED);
        }
        if (apply == null) {
            throw new AppException(ResultEnum.REFUND_INFO_IS_EMTPY);
        }
        if (apply.getReason() == null || "".equals(apply.getReason())) {
            throw new AppException(ResultEnum.REFUND_REASON_IS_EMPTY);
        }
        if (apply.getOrderid() == null || "".equals(apply.getOrderid())) {
            throw new AppException(ResultEnum.REFUND_ORDERID_IS_ERROR);
        }
        RunOrder runOrder = runOrderService.queryOrderByOIDAndUID(apply.getOrderid(), apply.getUid());
        if (runOrder == null) {
            throw new AppException(ResultEnum.INPUT_ERROR);
        }
        runOrderService.updateOrderStatus(runOrder.getOrderid(), OrderStatusEnum.REFUND_APPLY.getCode());
        Long currentTime = System.currentTimeMillis();
        Long diffTime = (currentTime - runOrder.getAddTime().getTime()) / 1000 * 60 * 60 * 24;
        if (diffTime > 7) {
            throw new AppException(ResultEnum.REFUND_APPLY_TIME_ERROR);
        }
        RunOrderPay pay = runOrderPayService.queryPayByOIDAndUID(apply.getOrderid(), apply.getUid());
        if (pay == null) {
            throw new AppException(ResultEnum.ORDER_PAY_INFO_EMPTY);
        }
        apply.setAmount(pay.getOrderActualPrice());
        apply.setAddTime(new Date());
        apply.setUpdateTime(new Date());
        apply.setStatus(RefundApplyStatusEnum.VERIFYING.getCode());
        refundApplyMapper.insert(apply);
    }

    /**
     * 更新退款申请
     *
     * @param apply
     * @throws AppException
     */
    @Override
    public void updateRefundApply(RefundApply apply) throws AppException {
        if (apply == null) {
            throw new AppException(ResultEnum.REFUND_INFO_IS_EMTPY);
        }
        apply.setUpdateTime(new Date());
        refundApplyMapper.updateByPrimaryKeySelective(apply);
    }

    /**
     * 更新退款申请状态
     *
     * @param refundDTO
     * @throws AppException
     */
    @Override
    public void updateApplyStatus(RefundDTO refundDTO) throws AppException{
        if (refundDTO.getId() == null || refundDTO.getId() <= 0) {
            throw new AppException(ResultEnum.REFUND_ID_IS_ERROR);
        }
        RefundApply apply = this.queryApplyByID(refundDTO.getId());
        apply.setOrderid(refundDTO.getOrderId());
        apply.setUpdateTime(new Date());
        apply.setOperator(refundDTO.getOperatorId());
        apply.setId(refundDTO.getId());
        apply.setStatus(refundDTO.getStatus());
        if (RefundApplyStatusEnum.AGREE.getCode().equals(refundDTO.getStatus())) {
            this.refund(apply);
            runOrderService.updateOrderStatus(refundDTO.getOrderId(), OrderStatusEnum.REFUND.getCode());
        } else {
            runOrderService.updateOrderStatus(refundDTO.getOrderId(), OrderStatusEnum.REFUND_FAIL.getCode());
        }
        refundDTO.setUid(apply.getUid());
        //保存退款记录
        this.saveRefundRecord(apply, refundDTO);
        refundApplyMapper.updateByPrimaryKeySelective(apply);
    }

    /**
     * 退款操作
     *
     * @param apply
     * @throws AppException
     */
    private synchronized void refund(RefundApply apply) throws AppException {
        String orderId = apply.getOrderid();
        if (orderId == null || "".equals(orderId)) {
            throw new AppException(ResultEnum.ORDER_ID_IS_ERROR);
        }
        RunOrderPay runOrderPay = runOrderPayService.queryPayByOIDAndUID(orderId, apply.getUid());
        double money = runOrderPay.getOrderActualPrice();
        RunUserBalance userBalance = runUserBalanceService.getRunUserBalanceByUID(apply.getUid());
        RunUserBalanceRecord record = new RunUserBalanceRecord();
        record.setUid(apply.getUid());
        record.setOldBalance(userBalance.getUserBalance());
        userBalance.setUserBalance(userBalance.getUserBalance() + money);
        runUserBalanceService.updateRunUserBalance(userBalance);
        record.setNewBalance(userBalance.getUserBalance());
        record.setType(false);
        record.setAmount(money);
        record.setAddTime(new Date());
        record.setNumber(RandomUtil.generateRandomDigitString(18));
        runUserBalanceRecordService.saveRunUserBalanceRecord(record);
    }

    /**
     * 保存退款记录
     *
     * @param apply
     * @param refundDTO
     * @throws AppException
     */
    public void saveRefundRecord(RefundApply apply, RefundDTO refundDTO) throws AppException{
        RefundRecord refundRecord = new RefundRecord();
        refundRecord.setUid(refundDTO.getUid());
        refundRecord.setOrderid(refundDTO.getOrderId());
        refundRecord.setOperator(refundDTO.getOperatorId());
        refundRecord.setReason(apply.getReason());
        refundRecord.setAmount(apply.getAmount());
        refundRecord.setResponsibility(refundDTO.getResponsibility());
        RunOrder order = runOrderService.getRunOrderByOID(refundDTO.getOrderId());
        if (order != null && order.getDid() != null) {
            refundRecord.setDid(order.getDid());
        }
        RunOrderPay orderPay = runOrderPayService.queryPayByOIDAndUID(apply.getOrderid(), apply.getUid());
        if (orderPay != null) {
            refundRecord.setSource(orderPay.getPayType());
        }
        refundRecordService.saveRefund(refundRecord);
    }

    /**
     * 根据申请id删除退款申请
     *
     * @param id
     * @throws AppException
     */
    @Override
    public void deleteRefundApplyByID(Integer id) throws AppException {
        if (id == null || id <= 0) {
            throw new AppException(ResultEnum.REFUND_ID_IS_ERROR);
        }
        refundApplyMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据订单号删除退款申请
     *
     * @param orderId
     * @throws AppException
     */
    @Override
    public void deleteRefundApplyByOrderid(String orderId) throws AppException {
        if (orderId == null || "".equals(orderId)) {
            throw new AppException(ResultEnum.REFUND_ORDERID_IS_ERROR);
        }
        RefundApplyExample example = new RefundApplyExample();
        RefundApplyExample.Criteria criteria = example.createCriteria();
        criteria.andOrderidEqualTo(orderId);
        refundApplyMapper.deleteByExample(example);
    }

    /**
     * 根据用户id批量删除退款申请
     *
     * @param uid
     * @throws AppException
     */
    @Override
    public void deleteAllApplyByUID(Integer uid) throws AppException {
        if (uid == null || uid <= 0) {
            throw new AppException(ResultEnum.USER_ID_IS_ERROR);
        }
        RefundApplyExample example = new RefundApplyExample();
        RefundApplyExample.Criteria criteria = example.createCriteria();
        criteria.andUidEqualTo(uid);
        refundApplyMapper.deleteByExample(example);
    }

    /**
     * 根据id查询退款信息
     *
     * @param id
     * @return
     * @throws AppException
     */
    @Override
    public RefundApplyVO queryApplyByID(Integer id) throws AppException {
        if (id == null || id <= 0) {
            throw new AppException(ResultEnum.REFUND_ID_IS_ERROR);
        }
        return convertApply2VO(refundApplyMapper.selectByPrimaryKey(id));
    }

    /**
     * 根据订单号查询退款信息
     *
     * @param orderId
     * @return
     * @throws AppException
     */
    @Override
    public RefundApplyVO queryApplyByOrderID(String orderId, Integer uid) throws AppException {
        if (orderId == null || "".equals(orderId)) {
            throw new AppException(ResultEnum.REFUND_ORDERID_IS_ERROR);
        }
        RefundApplyExample example = new RefundApplyExample();
        RefundApplyExample.Criteria criteria = example.createCriteria();
        criteria.andOrderidEqualTo(orderId);
        criteria.andUidEqualTo(uid);
        List<RefundApply> list = refundApplyMapper.selectByExample(example);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return convertApply2VO(list.get(0));
    }

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
    @Override
    public PageInfo<RefundApplyVO> pageApplysByUID(Integer uid, Integer status, Integer page, Integer size, String orderField, String orderType) throws AppException {
        if (uid == null || uid <= 0) {
            throw new AppException(ResultEnum.USER_ID_IS_ERROR);
        }
        if (page == null || page <= 0) {
            page = 1;
        }
        if (size == null || size <= 0) {
            size = 10;
        }
        if (orderField == null || "".equals(orderField)){
            orderField = "add_time";
        }
        if (orderType == null || "".equals(orderType)) {
            orderType = "DESC";
        }
        PageHelper.startPage(page, size);
        RefundApplyExample example = new RefundApplyExample();
        RefundApplyExample.Criteria criteria = example.createCriteria();
        criteria.andUidEqualTo(uid);
        if (status != null && status >= 0) {
            criteria.andStatusEqualTo(status);
        }
        example.setOrderByClause(" " + orderField + " " + orderType);
        List<RefundApply> applies = refundApplyMapper.selectByExample(example);
        return new PageInfo<>(convertApplys2VOs(applies));
    }

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
    @Override
    public PageInfo<RefundApplyVO> pageApplys(Integer status, Integer page, Integer size, String orderField, String orderType) throws AppException {
        if (page == null || page <= 0) {
            page = 1;
        }
        if (size == null || size <= 0) {
            size = 10;
        }
        if (orderField == null || "".equals(orderField)){
            orderField = "add_time";
        }
        if (orderType == null || "".equals(orderType)) {
            orderType = "DESC";
        }
        PageHelper.startPage(page, size);
        RefundApplyExample example = new RefundApplyExample();
        RefundApplyExample.Criteria criteria = example.createCriteria();
        if (status != null && status >= 0) {
            criteria.andStatusEqualTo(status);
        }
        example.setOrderByClause(" " + orderField + " " + orderType);
        List<RefundApply> applies = refundApplyMapper.selectByExample(example);
        return new PageInfo<>(convertApplys2VOs(applies));
    }

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
    @Override
    public PageInfo<RefundApplyVO> pageApplysByOperatorId(Integer operatorId, Integer page, Integer size, String orderField, String orderType) throws AppException {
        if (page == null || page <= 0) {
            page = 1;
        }
        if (size == null || size <= 0) {
            size = 10;
        }
        if (orderField == null || "".equals(orderField)){
            orderField = "add_time";
        }
        if (orderType == null || "".equals(orderType)) {
            orderType = "DESC";
        }
        PageHelper.startPage(page, size);
        RefundApplyExample example = new RefundApplyExample();
        RefundApplyExample.Criteria criteria = example.createCriteria();
        criteria.andOperatorEqualTo(operatorId);
        example.setOrderByClause(" " + orderField + " " + orderType);
        List<RefundApply> applies = refundApplyMapper.selectByExample(example);
        return new PageInfo<>(convertApplys2VOs(applies));
    }

    /**
     * 退款申请集合转VOS
     *
     * @param applies
     * @return
     * @throws AppException
     */
    public List<RefundApplyVO> convertApplys2VOs(List<RefundApply> applies) throws AppException{
        if (applies == null || applies.isEmpty()) {
            return new ArrayList<>();
        }
        List<RefundApplyVO> vos = new ArrayList<>(applies.size());
        for (RefundApply apply : applies) {
            if (apply == null) {
                continue;
            }
            RefundApplyVO vo = convertApply2VO(apply);
            if (vo == null) {
                continue;
            }
            vos.add(vo);
        }
        return vos;
    }

    /**
     * 退款申请转VO
     *
     * @param apply
     * @return
     * @throws AppException
     */
    public RefundApplyVO convertApply2VO(RefundApply apply) throws AppException {
        if (apply == null) {
            return null;
        }
        RefundApplyVO vo = new RefundApplyVO();
        vo.setId(apply.getId());
        vo.setUpdateTime(apply.getUpdateTime());
        vo.setUid(apply.getUid());
        vo.setReason(apply.getReason());
        vo.setOrderid(apply.getOrderid());
        vo.setAmount(apply.getAmount());
        vo.setAddTime(apply.getAddTime());
        RunUserInfo info = runUserInfoService.getRunUserInfoById(apply.getUid());
        if (info == null) {
            return null;
        }
        vo.setUserPhone(info.getUserPhone());
        vo.setUserName(info.getUserName());
        vo.setStatus(apply.getStatus());
        vo.setStatusDesc(RefundApplyStatusEnum.getRefundApplyStatusEnum(apply.getStatus()).getDesc());
        vo.setOperator(apply.getOperator());
        RunAdminInfo adminInfo = (RunAdminInfo) runAdminInfoService.getRunAdminInfoByID(apply.getOperator()).getData();
        if (adminInfo != null) {
            vo.setOperatorName(adminInfo.getAdminName());
            vo.setOperatorPhone(adminInfo.getAdminPhone());
        }
        return vo;
    }
}
