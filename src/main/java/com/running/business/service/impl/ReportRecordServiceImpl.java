package com.running.business.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.running.business.common.ResultEnum;
import com.running.business.controller.RunAdminController;
import com.running.business.enums.ReportLevelEnum;
import com.running.business.enums.UserTypeEnum;
import com.running.business.exception.AppException;
import com.running.business.mapper.ReportRecordMapper;
import com.running.business.pojo.ReportRecord;
import com.running.business.pojo.ReportRecordExample;
import com.running.business.pojo.RunDeliveryInfo;
import com.running.business.pojo.RunUserInfo;
import com.running.business.service.ReportRecordService;
import com.running.business.service.RunDeliveryInfoService;
import com.running.business.service.RunUserInfoService;
import com.running.business.vo.ReportRecordVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author liumingyu
 * @create 2018-02-02 下午2:30
 */
@Service
public class ReportRecordServiceImpl implements ReportRecordService {

    private Logger LOGGER = LoggerFactory.getLogger(RunAdminController.class);

    private static final String LOG_PREFIX = "【投诉记录模块】 ";

    @Autowired
    private ReportRecordMapper reportRecordMapper;

    @Autowired
    private RunUserInfoService runUserInfoService;

    @Autowired
    private RunDeliveryInfoService runDeliveryInfoService;

    /**
     * 验证是否投诉过
     *
     * @param reportRecord
     * @return
     * @throws AppException
     */
    public boolean checkReportRecord(ReportRecord reportRecord) throws AppException {
        if (reportRecord == null) {
            throw new AppException(ResultEnum.REPORT_INFO_IS_EMPTY);
        }
        ReportRecordExample example = new ReportRecordExample();
        ReportRecordExample.Criteria criteria = example.createCriteria();
        criteria.andDidEqualTo(reportRecord.getDid());
        criteria.andUidEqualTo(reportRecord.getUid());
        criteria.andActiveSideEqualTo(reportRecord.getActiveSide());
        List<ReportRecord> reportRecords = reportRecordMapper.selectByExample(example);
        if (reportRecords == null || reportRecords.isEmpty()) {
            return false;
        }
        return true;
    }

    /**
     * 创建投诉记录
     *
     * @param reportRecord
     * @throws AppException
     */
    @Override
    public void saveReportRecord(ReportRecord reportRecord) throws AppException {
        if (checkReportRecord(reportRecord)) {
            throw new AppException(ResultEnum.REPORT_HAVE_BEEN_ERROR);
        }
        if (reportRecord == null) {
            throw new AppException(ResultEnum.REPORT_INFO_IS_EMPTY);
        }
        reportRecord.setAddTime(new Date());
        reportRecordMapper.insert(reportRecord);
    }

    /**
     * 更新投诉记录
     *
     * @param reportRecord
     * @throws AppException
     */
    @Override
    public void updateReportRecord(ReportRecord reportRecord) throws AppException {
        if (reportRecord == null) {
            throw new AppException(ResultEnum.REPORT_INFO_IS_EMPTY);
        }
        reportRecordMapper.updateByPrimaryKeySelective(reportRecord);
    }

    /**
     * 根据id删除投诉记录
     *
     * @param id
     * @throws AppException
     */
    @Override
    public void deleteRecordById(Integer id) throws AppException {
        if (id == null || id <= 0) {
            throw new AppException(ResultEnum.REPORT_ID_IS_ERROR);
        }
        reportRecordMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据id查询投诉记录
     *
     * @param id
     * @return
     * @throws AppException
     */
    @Override
    public ReportRecordVO queryRecordById(Integer id) throws AppException {
        if (id == null || id <= 0) {
            throw new AppException(ResultEnum.REPORT_ID_IS_ERROR);
        }
        return convertRecord2VO(reportRecordMapper.selectByPrimaryKey(id));
    }

    /**
     * 根据用户id分页查询投诉信息
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
    public PageInfo<ReportRecordVO> pageRecordByUID(Integer uid, Integer page, Integer size, String orderField, String orderType) throws AppException {
        if (uid == null || uid <= 0) {
            throw new AppException(ResultEnum.USER_ID_IS_ERROR);
        }
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
        ReportRecordExample example = new ReportRecordExample();
        ReportRecordExample.Criteria criteria = example.createCriteria();
        criteria.andUidEqualTo(uid);
        example.setOrderByClause(" " + orderField + " " + orderType);
        List<ReportRecord> reportRecords = reportRecordMapper.selectByExample(example);
        return new PageInfo<>(convertRecords2VOs(reportRecords));
    }

    /**
     * 根据配送员id分页查询投诉信息
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
    public PageInfo<ReportRecordVO> pageRecordByDID(Integer did, Integer page, Integer size, String orderField, String orderType) throws AppException {
        if (did == null || did <= 0) {
            throw new AppException(ResultEnum.DELIVERY_ID_IS_ERROR);
        }
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
        ReportRecordExample example = new ReportRecordExample();
        ReportRecordExample.Criteria criteria = example.createCriteria();
        criteria.andDidEqualTo(did);
        example.setOrderByClause(" " + orderField + " " + orderType);
        List<ReportRecord> reportRecords = reportRecordMapper.selectByExample(example);
        return new PageInfo<>(convertRecords2VOs(reportRecords));
    }

    /**
     * 根据用户id和配送员ID和主动投诉方查询该用户的投诉和被投诉记录
     *
     * @param uid
     * @param did
     * @param activeSide
     * @param page
     * @param size
     * @param orderField
     * @param orderType
     * @return
     * @throws AppException
     */
    @Override
    public PageInfo<ReportRecordVO> pageRecordByActiveAndDIDOrUID(Integer uid, Integer did, Integer activeSide, Integer page, Integer size, String orderField, String orderType) throws AppException {
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
        ReportRecordExample example = new ReportRecordExample();
        ReportRecordExample.Criteria criteria = example.createCriteria();
        if (uid != null && uid > 0) {
            criteria.andUidEqualTo(uid);
        }
        if (did != null && did > 0) {
            criteria.andDidEqualTo(did);
        }
        if (activeSide != null && activeSide >= 0) {
            criteria.andActiveSideEqualTo(activeSide);
        }
        example.setOrderByClause(" " + orderField + " " + orderType);
        List<ReportRecord> reportRecords = reportRecordMapper.selectByExample(example);
        return new PageInfo<>(convertRecords2VOs(reportRecords));
    }

    /**
     * 根据配送员id和主动投诉方查询该配送员的投诉和被投诉记录
     *
     * @param did
     * @param activeSide
     * @param page
     * @param size
     * @param orderField
     * @param orderType
     * @return
     * @throws AppException
     */
    @Override
    public PageInfo<ReportRecordVO> pageRecordByActiveAndDID(Integer did, Integer activeSide, Integer page, Integer size, String orderField, String orderType) throws AppException {

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
        ReportRecordExample example = new ReportRecordExample();
        ReportRecordExample.Criteria criteria = example.createCriteria();
        if (did != null && did > 0) {
            criteria.andDidEqualTo(did);
        }
        if (activeSide != null && activeSide > 0) {
            criteria.andActiveSideEqualTo(activeSide);
        }
        example.setOrderByClause(" " + orderField + " " + orderType);
        List<ReportRecord> reportRecords = reportRecordMapper.selectByExample(example);
        return new PageInfo<>(convertRecords2VOs(reportRecords));
    }

    /**
     * 投诉记录集合转VO集合
     *
     * @param reportRecords
     * @return
     * @throws AppException
     */
    public List<ReportRecordVO> convertRecords2VOs(List<ReportRecord> reportRecords) throws AppException {
        if (reportRecords == null || reportRecords.isEmpty()) {
            return new ArrayList<>();
        }
        List<ReportRecordVO> reportRecordVOS = new ArrayList<>(reportRecords.size());
        for (ReportRecord reportRecord : reportRecords) {
            if (reportRecord == null) {
                continue;
            }
            ReportRecordVO vo = convertRecord2VO(reportRecord);
            if (vo == null) {
                continue;
            }
            reportRecordVOS.add(vo);
        }
        return reportRecordVOS;
    }

    /**
     * 投诉记录转VO
     *
     * @param reportRecord
     * @return
     * @throws AppException
     */
    public ReportRecordVO convertRecord2VO(ReportRecord reportRecord) throws AppException {
        if (reportRecord == null) {
            return null;
        }
        ReportRecordVO vo = new ReportRecordVO();
        vo.setId(reportRecord.getId());
        vo.setUid(reportRecord.getUid());
        vo.setReason(reportRecord.getReason());
        vo.setDid(reportRecord.getDid());
        vo.setAddTime(reportRecord.getAddTime());
        vo.setLevel(reportRecord.getLevel());
        vo.setLevelDesc(ReportLevelEnum.getReportLevelEnum(reportRecord.getLevel()).getDesc());
        vo.setActiveSide(reportRecord.getActiveSide());
        vo.setActiveSideDesc(UserTypeEnum.getUserTypeEnum(reportRecord.getActiveSide()).getDesc());
        try {
            RunUserInfo info = runUserInfoService.getRunUserInfoById(reportRecord.getUid());
            if (info == null) {
                return vo;
            }
            vo.setUserPhone(info.getUserPhone());
            vo.setUserName(info.getUserName());
        } catch (Exception e) {
            LOGGER.error("{} 查询用户信息异常 error = {}", LOG_PREFIX, e);
        }
        try {
            RunDeliveryInfo runDeliveryInfo = runDeliveryInfoService.getRunDeliveryInfoByID(reportRecord.getDid());
            if (reportRecord == null) {
                return vo;
            }
            vo.setDeliveryPhone(runDeliveryInfo.getPhone());
            vo.setDeliveryName(runDeliveryInfo.getName());
        } catch (Exception e) {
            LOGGER.error("{} 查询配送员信息异常 error = {}", LOG_PREFIX, e);
        }
        return vo;
    }
}
