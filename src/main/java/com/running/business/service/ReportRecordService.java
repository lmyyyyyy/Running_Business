package com.running.business.service;

import com.github.pagehelper.PageInfo;
import com.running.business.exception.AppException;
import com.running.business.pojo.ReportRecord;
import com.running.business.vo.ReportRecordVO;

/**
 * @author liumingyu
 * @create 2018-02-02 下午2:30
 */
public interface ReportRecordService {

    /**
     * 创建投诉记录
     *
     * @param reportRecord
     * @throws AppException
     */
    void saveReportRecord(ReportRecord reportRecord) throws AppException;

    /**
     * 更新投诉记录
     *
     * @param reportRecord
     * @throws AppException
     */
    void updateReportRecord(ReportRecord reportRecord) throws AppException;

    /**
     * 根据id删除投诉记录
     *
     * @param id
     * @throws AppException
     */
    void deleteRecordById(Integer id) throws AppException;

    /**
     * 根据id查询投诉记录
     *
     * @param id
     * @return
     * @throws AppException
     */
    ReportRecordVO queryRecordById(Integer id) throws AppException;

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
    PageInfo<ReportRecordVO> pageRecordByUID(Integer uid, Integer page, Integer size, String orderField, String orderType) throws AppException;

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
    PageInfo<ReportRecordVO> pageRecordByDID(Integer did, Integer page, Integer size, String orderField, String orderType) throws AppException;

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
    PageInfo<ReportRecordVO> pageRecordByActiveAndDIDOrUID(Integer uid, Integer did, Integer activeSide, Integer page, Integer size, String orderField, String orderType) throws AppException;

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
    PageInfo<ReportRecordVO> pageRecordByActiveAndDID(Integer did, Integer activeSide, Integer page, Integer size, String orderField, String orderType) throws AppException;
}
