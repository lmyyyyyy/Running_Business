package com.running.business.service;

import com.running.business.exception.AppException;
import com.running.business.pojo.HeartBeat;

import java.util.Date;
import java.util.List;

/**
 * @author liumingyu
 * @create 2018-01-24 下午2:35
 */
public interface HeartBeatService {

    /**
     * 用户心跳
     *
     * @param heartBeat
     * @throws AppException
     */
    HeartBeat save(HeartBeat heartBeat) throws AppException;

    /**
     * 保存心跳
     *
     * @param heartBeat
     * @throws AppException
     */
    void saveHeartBeat(HeartBeat heartBeat) throws AppException;

    /**
     * 保存或更新心跳
     *
     * @param heartBeat
     * @throws AppException
     */
    HeartBeat saveOrUpdateHeartBeat(HeartBeat heartBeat) throws AppException;

    /**
     * 更新心跳
     *
     * @param heartBeat
     * @throws AppException
     */
    void updateHeartBeat(HeartBeat heartBeat) throws AppException;

    /**
     * 根据id删除心跳
     *
     * @param id
     * @throws AppException
     */
    void deleteHeartBeatById(Integer id) throws AppException;

    /**
     * 删除该用户id的所有心跳
     *
     * @param userType
     * @param uid
     * @throws AppException
     */
    void deleteHeartBeatByUserTypeAndUID(Integer userType, Integer uid) throws AppException;

    /**
     * 删除从开始时间的所有心跳
     *
     * @param beginTime
     * @throws AppException
     */
    void deleteHeartBeatByBeginTime(Date beginTime) throws AppException;

    /**
     * 删除结束时间之前的所有心跳
     *
     * @param endTime
     * @throws AppException
     */
    void deleteHeartBeatByEndTime(Date endTime) throws AppException;

    /**
     * 删除开始时间到结束时间范围内的所有心跳
     *
     * @param beginTime
     * @param endTime
     * @throws AppException
     */
    void deleteHeartBeatByRange(Date beginTime, Date endTime) throws AppException;

    /**
     * 根据id查询心跳
     *
     * @param id
     * @return
     * @throws AppException
     */
    HeartBeat queryHeartBeatById(Integer id) throws AppException;

    /**
     * 查询该用户id的所有心跳
     *
     * @param uid
     * @return
     * @throws AppException
     */
    List<HeartBeat> queryHeartBeatByUID(Integer uid) throws AppException;

    /**
     * 查询从开始时间的所有心跳
     *
     * @param beginTime
     * @return
     * @throws AppException
     */
    List<HeartBeat> queryHeartBeatByBeginTime(Date beginTime) throws AppException;

    /**
     * 查询截止到结束时间的所有心跳
     *
     * @param endTime
     * @return
     * @throws AppException
     */
    List<HeartBeat> queryHeartBeatByEndTime(Date endTime) throws AppException;

    /**
     * 查询从开始到结束时间范围内的所有心跳
     *
     * @param beginTime
     * @param endTime
     * @return
     * @throws AppException
     */
    List<HeartBeat> queryHeartBeatByRange(Date beginTime, Date endTime) throws AppException;

    /**
     * 查询距当前时间多少秒内的所有心跳
     *
     * @param seconds
     * @return
     * @throws AppException
     */
    List<HeartBeat> queryHeartBeatBySeconds(Long seconds) throws AppException;

    /**
     * 根据用户类型查询所有用户心跳
     *
     * @param userType
     * @return
     * @throws AppException
     */
    List<HeartBeat> queryHeartBeatByUserType(Integer userType) throws AppException;

    /**
     * 根据用户类型和用户id查询所有心跳
     *
     * @param userType
     * @param uid
     * @return
     * @throws AppException
     */
    List<HeartBeat> queryHeartBeatByUserTypeAndUID(Integer userType, Integer uid) throws AppException;

    /**
     * 根据用户类型查询距当前时间多少秒内的所有心跳
     *
     * @param userType
     * @param seconds
     * @return
     * @throws AppException
     */
    List<HeartBeat> queryHeartBeatByUserTypeAndSeconds(Integer userType, Long seconds) throws AppException;
}

