package com.running.business.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.running.business.common.ResultEnum;
import com.running.business.enums.PaySourceTypeEnum;
import com.running.business.enums.UserTypeEnum;
import com.running.business.exception.AppException;
import com.running.business.mapper.RefundRecordMapper;
import com.running.business.pojo.RefundRecord;
import com.running.business.pojo.RefundRecordExample;
import com.running.business.pojo.RunAdminInfo;
import com.running.business.pojo.RunDeliveryInfo;
import com.running.business.pojo.RunOrderPay;
import com.running.business.pojo.RunUserInfo;
import com.running.business.service.RefundRecordService;
import com.running.business.service.RunAdminInfoService;
import com.running.business.service.RunDeliveryInfoService;
import com.running.business.service.RunOrderPayService;
import com.running.business.service.RunUserInfoService;
import com.running.business.vo.RefundRecordVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author liumingyu
 * @create 2018-02-15 上午10:38
 */
@Service
public class RefundRecordServiceImpl implements RefundRecordService {

    @Autowired
    private RefundRecordMapper refundRecordMapper;

    @Autowired
    private RunUserInfoService runUserInfoService;

    @Autowired
    private RunDeliveryInfoService runDeliveryInfoService;

    @Autowired
    private RunAdminInfoService runAdminInfoService;

    @Autowired
    private RunOrderPayService runOrderPayService;

    /**
     * 保存退款记录
     *
     * @param refundRecord
     * @throws AppException
     */
    @Override
    public void saveRefund(RefundRecord refundRecord) throws AppException {
        if (refundRecord == null) {
            throw new AppException(ResultEnum.REFUND_RECORD_IS_NULL);
        }
        refundRecord.setAddTime(new Date());
        refundRecordMapper.insert(refundRecord);
    }

    /**
     * 更新退款记录
     *
     * @param refundRecord
     * @throws AppException
     */
    @Override
    public void updateRefund(RefundRecord refundRecord) throws AppException {
        if (refundRecord == null) {
            throw new AppException(ResultEnum.REFUND_RECORD_IS_NULL);
        }
        refundRecordMapper.updateByPrimaryKeySelective(refundRecord);
    }

    /**
     * 根据主键id删除退款记录
     *
     * @param id
     * @throws AppException
     */
    @Override
    public void deleteRefundById(Integer id) throws AppException {
        if (id == null || id < 0) {
            throw new AppException(ResultEnum.REFUND_RECORD_ID_ERROR);
        }
        refundRecordMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据用户id删除所有退款记录
     *
     * @param uid
     * @throws AppException
     */
    @Override
    public void deleteRefundByUID(Integer uid) throws AppException {
        RefundRecordExample example = new RefundRecordExample();
        RefundRecordExample.Criteria criteria = example.createCriteria();
        criteria.andUidEqualTo(uid);
        refundRecordMapper.deleteByExample(example);
    }

    /**
     * 根据订单id删除退款记录
     *
     * @param orderId
     * @throws AppException
     */
    @Override
    public void deleteRefundByOID(String orderId) throws AppException {
        if (orderId == null || "".equals(orderId)) {
            throw new AppException(ResultEnum.ORDER_ID_IS_ERROR);
        }
        RefundRecordExample example = new RefundRecordExample();
        RefundRecordExample.Criteria criteria = example.createCriteria();
        criteria.andOrderidEqualTo(orderId);
        refundRecordMapper.deleteByExample(example);
    }

    /**
     * 根据配送员id删除退款记录
     *
     * @param did
     * @throws AppException
     */
    @Override
    public void deleteRefundByDID(Integer did) throws AppException {
        RefundRecordExample example = new RefundRecordExample();
        RefundRecordExample.Criteria criteria = example.createCriteria();
        criteria.andDidEqualTo(did);
        refundRecordMapper.deleteByExample(example);
    }

    /**
     * 根据主键id查询退款记录
     *
     * @param id
     * @return
     * @throws AppException
     */
    @Override
    public RefundRecordVO queryRefundByID(Integer id) throws AppException {
        if (id == null || id < 0) {
            throw new AppException(ResultEnum.REFUND_RECORD_ID_ERROR);
        }
        RefundRecord refundRecord = refundRecordMapper.selectByPrimaryKey(id);
        return this.convertRefundRecord2VO(refundRecord);
    }

    /**
     * 根据订单id获取退款记录
     *
     * @param orderId
     * @return
     * @throws AppException
     */
    @Override
    public RefundRecordVO queryRefundByOID(String orderId) throws AppException {
        if (orderId == null || "".equals(orderId)) {
            throw new AppException(ResultEnum.ORDER_ID_IS_ERROR);
        }
        RefundRecordExample example = new RefundRecordExample();
        RefundRecordExample.Criteria criteria = example.createCriteria();
        criteria.andOrderidEqualTo(orderId);
        List<RefundRecord> refundRecords = refundRecordMapper.selectByExample(example);
        if (refundRecords == null || refundRecords.isEmpty()) {
            return null;
        }
        return convertRefundRecord2VO(refundRecords.get(0));
    }

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
    @Override
    public PageInfo<RefundRecordVO> pageRefundByUID(Integer uid, Integer page, Integer size, String orderField, String orderType) throws AppException {
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
        RefundRecordExample example = new RefundRecordExample();
        RefundRecordExample.Criteria criteria = example.createCriteria();
        criteria.andUidEqualTo(uid);
        example.setOrderByClause(" " + orderField + " " + orderType);
        List<RefundRecord> refundRecords = refundRecordMapper.selectByExample(example);
        return new PageInfo<>(this.convertRefundRecords2VOs(refundRecords));
    }

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
    @Override
    public PageInfo<RefundRecordVO> pageRefundByDID(Integer did, Integer page, Integer size, String orderField, String orderType) throws AppException {
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
        RefundRecordExample example = new RefundRecordExample();
        RefundRecordExample.Criteria criteria = example.createCriteria();
        criteria.andDidEqualTo(did);
        example.setOrderByClause(" " + orderField + " " + orderType);
        List<RefundRecord> refundRecords = refundRecordMapper.selectByExample(example);
        return new PageInfo<>(this.convertRefundRecords2VOs(refundRecords));
    }

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
    @Override
    public PageInfo<RefundRecordVO> pageRefundByOperatorId(Integer operatorId, Integer page, Integer size, String orderField, String orderType) throws AppException {
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
        RefundRecordExample example = new RefundRecordExample();
        RefundRecordExample.Criteria criteria = example.createCriteria();
        criteria.andOperatorEqualTo(operatorId);
        example.setOrderByClause(" " + orderField + " " + orderType);
        List<RefundRecord> refundRecords = refundRecordMapper.selectByExample(example);
        return new PageInfo<>(this.convertRefundRecords2VOs(refundRecords));
    }

    /**
     * 退款记录集合转VO
     *
     * @param refundRecords
     * @return
     * @throws AppException
     */
    public List<RefundRecordVO> convertRefundRecords2VOs(List<RefundRecord> refundRecords) throws AppException {
        if (refundRecords == null || refundRecords.isEmpty()) {
            return new ArrayList<>();
        }
        List<RefundRecordVO> vos = new ArrayList<>(refundRecords.size());
        for (RefundRecord refundRecord : refundRecords) {
            if (refundRecord == null) {
                continue;
            }
            RefundRecordVO vo = convertRefundRecord2VO(refundRecord);
            if (vo == null) {
                continue;
            }
            vos.add(vo);
        }
        return vos;
    }

    /**
     * 退款记录转VO
     *
     * @param refundRecord
     * @return
     * @throws AppException
     */
    public RefundRecordVO convertRefundRecord2VO(RefundRecord refundRecord) throws AppException {
        if (refundRecord == null) {
            return null;
        }
        RefundRecordVO vo = new RefundRecordVO();
        vo.setId(refundRecord.getId());
        vo.setOrderid(refundRecord.getOrderid());
        vo.setReason(refundRecord.getReason());
        vo.setAmount(refundRecord.getAmount());
        vo.setAddTime(refundRecord.getAddTime());
        vo.setSource(refundRecord.getSource());
        vo.setResponsibility(refundRecord.getResponsibility());
        vo.setOperator(refundRecord.getOperator());
        if (refundRecord.getDid() != null && refundRecord.getDid() > 0) {
            RunDeliveryInfo deliveryInfo = runDeliveryInfoService.getRunDeliveryInfoByID(refundRecord.getDid());
            if (deliveryInfo != null) {
                vo.setDid(refundRecord.getDid());
                vo.setDeliveryName(deliveryInfo.getName());
                vo.setDeliveryPhone(deliveryInfo.getPhone());
            }
        }
        if (refundRecord.getUid() != null && refundRecord.getUid() > 0) {
            RunUserInfo info = runUserInfoService.getRunUserInfoById(refundRecord.getUid());
            if (info != null) {
                vo.setUid(refundRecord.getUid());
                vo.setUserName(info.getUserName());
                vo.setUserPhone(info.getUserPhone());
            }
        }
        if (refundRecord.getOperator() != null && refundRecord.getOperator() > 0) {
            RunAdminInfo info = (RunAdminInfo) runAdminInfoService.getRunAdminInfoByID(refundRecord.getOperator()).getData();
            if (info != null) {
                vo.setOperatorName(info.getAdminName());
                vo.setOperatorPhone(info.getAdminPhone());
            }
        } else {
            vo.setOperatorName("系统");
        }
        vo.setResponsibilityDesc(UserTypeEnum.getUserTypeEnum(refundRecord.getResponsibility()).getDesc());
        vo.setSourceDesc(PaySourceTypeEnum.getOrderPayTypeEnum(refundRecord.getSource()).getDesc());
        return vo;
    }
}
