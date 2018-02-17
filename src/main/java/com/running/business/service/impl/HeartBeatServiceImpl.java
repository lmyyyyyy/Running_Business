package com.running.business.service.impl;

import com.running.business.common.Config;
import com.running.business.common.ResultEnum;
import com.running.business.enums.UserTypeEnum;
import com.running.business.exception.AppException;
import com.running.business.mapper.HeartBeatMapper;
import com.running.business.mapper.JedisClient;
import com.running.business.pojo.HeartBeat;
import com.running.business.pojo.HeartBeatExample;
import com.running.business.service.HeartBeatService;
import com.running.business.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author liumingyu
 * @create 2018-01-24 下午2:49
 */
@Service
public class HeartBeatServiceImpl implements HeartBeatService {

    private static final Logger LOGGER = LoggerFactory.getLogger(HeartBeatServiceImpl.class);
    private static final String LOG_PREFIX = "【心跳检测Service】 ";

    @Autowired
    private HeartBeatMapper heartBeatMapper;

    @Autowired
    private JedisClient jedisClient;

    /**
     * 用户心跳
     *
     * @param heartBeat
     * @throws AppException
     */
    @Override
    public HeartBeat save(HeartBeat heartBeat) throws AppException {
        if (heartBeat == null) {
            LOGGER.error("{} 保存-心跳参数异常", LOG_PREFIX);
            throw new AppException(ResultEnum.HEARTBEAT_INFO_EMPTY);
        }
        HeartBeat beat = this.saveOrUpdateHeartBeat(heartBeat);
        boolean flag;
        if (UserTypeEnum.USER.getCode().equals(heartBeat.getUserType())) {
            flag = jedisClient.sismember(Config.LOGIN_USER_IDS_KEY, String.valueOf(heartBeat.getUid()));
            if (flag) {
                return beat;
            }
            jedisClient.sadd(Config.LOGIN_USER_IDS_KEY, String.valueOf(heartBeat.getUid()));
        } else if (UserTypeEnum.DELIVERY.getCode().equals(heartBeat.getUserType())) {
            flag = jedisClient.sismember(Config.LOGIN_DELIVERY_IDS_KEY, String.valueOf(heartBeat.getUid()));
            if (flag) {
                return beat;
            }
            jedisClient.sadd(Config.LOGIN_DELIVERY_IDS_KEY, String.valueOf(heartBeat.getUid()));
        } else {
            flag = jedisClient.sismember(Config.LOGIN_ADMIN_IDS_KEY, String.valueOf(heartBeat.getUid()));
            if (flag) {
                return beat;
            }
            jedisClient.sadd(Config.LOGIN_ADMIN_IDS_KEY, String.valueOf(heartBeat.getUid()));
        }
        return beat;
    }

    /**
     * 保存心跳
     *
     * @param heartBeat
     * @throws AppException
     */
    @Override
    public void saveHeartBeat(HeartBeat heartBeat) throws AppException {
        if (heartBeat == null) {
            LOGGER.error("{} 保存-心跳参数异常", LOG_PREFIX);
            throw new AppException(ResultEnum.HEARTBEAT_INFO_EMPTY);
        }
        heartBeatMapper.insert(heartBeat);
    }

    /**
     * 保存或更新心跳
     *
     * @param heartBeat
     * @throws AppException
     */
    @Override
    public HeartBeat saveOrUpdateHeartBeat(HeartBeat heartBeat) throws AppException {
        if (heartBeat == null) {
            LOGGER.error("{} 保存或更新-心跳参数异常", LOG_PREFIX);
            throw new AppException(ResultEnum.HEARTBEAT_INFO_EMPTY);
        }
        List<HeartBeat> list = this.queryHeartBeatByUserTypeAndUID(heartBeat.getUserType(), heartBeat.getUid());
        if (list == null || list.size() == 0) {
            HeartBeat beat = new HeartBeat();
            beat.setUid(heartBeat.getUid());
            beat.setIp(heartBeat.getIp());
            beat.setUserInfo(heartBeat.getUserInfo());
            beat.setAddTime(new Date());
            beat.setUpdateTime(new Date());
            beat.setUserType(heartBeat.getUserType());
            beat.setSessionKey(heartBeat.getSessionKey());
            this.saveHeartBeat(beat);
            return beat;
        }
        HeartBeat beat = list.get(0);
        beat.setUid(heartBeat.getUid());
        beat.setUserType(heartBeat.getUserType());
        beat.setIp(heartBeat.getIp());
        beat.setSessionKey(heartBeat.getSessionKey());
        beat.setUpdateTime(new Date());
        beat.setUserInfo(heartBeat.getUserInfo());
        this.updateHeartBeat(beat);
        return beat;
    }

    /**
     * 更新心跳
     *
     * @param heartBeat
     * @throws AppException
     */
    @Override
    public void updateHeartBeat(HeartBeat heartBeat) throws AppException {
        if (heartBeat == null) {
            LOGGER.error("{} 更新-心跳参数异常", LOG_PREFIX);
            throw new AppException(ResultEnum.HEARTBEAT_INFO_EMPTY);
        }
        heartBeatMapper.updateByPrimaryKeySelective(heartBeat);
    }

    /**
     * 根据id删除心跳
     *
     * @param id
     * @throws AppException
     */
    @Override
    public void deleteHeartBeatById(Integer id) throws AppException {
        if (id == null || id <= 0) {
            LOGGER.error("{} 删除心跳异常 id参数异常", LOG_PREFIX);
            throw new AppException(ResultEnum.HEARTBEAT_ID_ERROR);
        }
        heartBeatMapper.deleteByPrimaryKey(id);
    }

    /**
     * 删除该用户id的所有心跳
     *
     * @param userType
     * @param uid
     * @throws AppException
     */
    @Override
    public void deleteHeartBeatByUserTypeAndUID(Integer userType, Integer uid) throws AppException {
        if (uid == null || uid <= 0) {
            LOGGER.error("{} 删除心跳异常 用户id参数异常");
            throw new AppException(ResultEnum.HEARTBEAT_USER_ID_EROOR);
        }
        HeartBeatExample example = new HeartBeatExample();
        HeartBeatExample.Criteria criteria = example.createCriteria();
        criteria.andUidEqualTo(uid);
        criteria.andUserTypeEqualTo(userType);
        heartBeatMapper.deleteByExample(example);
    }

    /**
     * 删除从开始时间的所有心跳
     *
     * @param beginTime
     * @throws AppException
     */
    @Override
    public void deleteHeartBeatByBeginTime(Date beginTime) throws AppException {
        if (beginTime == null) {
            LOGGER.error("{} 时间参数异常");
            throw new AppException(ResultEnum.HEARTBEAT_DATE_ERROR);
        }
        HeartBeatExample example = new HeartBeatExample();
        HeartBeatExample.Criteria criteria = example.createCriteria();
        criteria.andUpdateTimeGreaterThan(beginTime);
        heartBeatMapper.deleteByExample(example);
    }

    /**
     * 删除结束时间之前的所有心跳
     *
     * @param endTime
     * @throws AppException
     */
    @Override
    public void deleteHeartBeatByEndTime(Date endTime) throws AppException {
        if (endTime == null) {
            LOGGER.error("{} 时间参数异常");
            throw new AppException(ResultEnum.HEARTBEAT_DATE_ERROR);
        }
        HeartBeatExample example = new HeartBeatExample();
        HeartBeatExample.Criteria criteria = example.createCriteria();
        criteria.andUpdateTimeLessThan(endTime);
        heartBeatMapper.deleteByExample(example);
    }

    /**
     * 删除开始时间到结束时间范围内的所有心跳
     *
     * @param beginTime
     * @param endTime
     * @throws AppException
     */
    @Override
    public void deleteHeartBeatByRange(Date beginTime, Date endTime) throws AppException {
        if (beginTime == null || endTime == null) {
            LOGGER.error("{} 时间参数异常");
            throw new AppException(ResultEnum.HEARTBEAT_DATE_ERROR);
        }
        HeartBeatExample example = new HeartBeatExample();
        HeartBeatExample.Criteria criteria = example.createCriteria();
        criteria.andUpdateTimeBetween(beginTime, endTime);
        heartBeatMapper.deleteByExample(example);
    }

    /**
     * 根据id查询心跳
     *
     * @param id
     * @return
     * @throws AppException
     */
    @Override
    public HeartBeat queryHeartBeatById(Integer id) throws AppException {
        if (id == null || id <= 0) {
            LOGGER.error("{} 查询心跳异常 id参数异常", LOG_PREFIX);
            throw new AppException(ResultEnum.HEARTBEAT_ID_ERROR);
        }
        return heartBeatMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询该用户id的所有心跳
     *
     * @param uid
     * @return
     * @throws AppException
     */
    @Override
    public List<HeartBeat> queryHeartBeatByUID(Integer uid) throws AppException {
        if (uid == null || uid <= 0) {
            LOGGER.error("{} 查询心跳异常 用户id参数异常", LOG_PREFIX);
            throw new AppException(ResultEnum.HEARTBEAT_USER_ID_EROOR);
        }
        HeartBeatExample example = new HeartBeatExample();
        HeartBeatExample.Criteria criteria = example.createCriteria();
        criteria.andUidEqualTo(uid);
        example.setOrderByClause(" update_time desc");
        return heartBeatMapper.selectByExample(example);
    }

    /**
     * 查询从开始时间的所有心跳
     *
     * @param beginTime
     * @return
     * @throws AppException
     */
    @Override
    public List<HeartBeat> queryHeartBeatByBeginTime(Date beginTime) throws AppException {
        if (beginTime == null) {
            LOGGER.error("{} 时间参数异常");
            throw new AppException(ResultEnum.HEARTBEAT_DATE_ERROR);
        }
        HeartBeatExample example = new HeartBeatExample();
        HeartBeatExample.Criteria criteria = example.createCriteria();
        criteria.andUpdateTimeGreaterThan(beginTime);
        example.setOrderByClause(" update_time desc");
        return heartBeatMapper.selectByExample(example);
    }

    /**
     * 查询截止到结束时间的所有心跳
     *
     * @param endTime
     * @return
     * @throws AppException
     */
    @Override
    public List<HeartBeat> queryHeartBeatByEndTime(Date endTime) throws AppException {
        if (endTime == null) {
            LOGGER.error("{} 时间参数异常");
            throw new AppException(ResultEnum.HEARTBEAT_DATE_ERROR);
        }
        HeartBeatExample example = new HeartBeatExample();
        HeartBeatExample.Criteria criteria = example.createCriteria();
        criteria.andUpdateTimeLessThan(endTime);
        example.setOrderByClause(" update_time desc");
        return heartBeatMapper.selectByExample(example);
    }

    /**
     * 查询从开始到结束时间范围内的所有心跳
     *
     * @param beginTime
     * @param endTime
     * @return
     * @throws AppException
     */
    @Override
    public List<HeartBeat> queryHeartBeatByRange(Date beginTime, Date endTime) throws AppException {
        if (beginTime == null || endTime == null) {
            LOGGER.error("{} 时间参数异常");
            throw new AppException(ResultEnum.HEARTBEAT_DATE_ERROR);
        }
        HeartBeatExample example = new HeartBeatExample();
        HeartBeatExample.Criteria criteria = example.createCriteria();
        criteria.andUpdateTimeBetween(beginTime, endTime);
        example.setOrderByClause(" update_time desc");
        return heartBeatMapper.selectByExample(example);
    }

    /**
     * 查询距当前时间多少秒内的所有心跳
     *
     * @param seconds
     * @return
     * @throws AppException
     */
    @Override
    public List<HeartBeat> queryHeartBeatBySeconds(Long seconds) throws AppException {
        if (seconds == null || seconds < 0) {
            LOGGER.error("{} 秒数异常", LOG_PREFIX);
            throw new AppException(ResultEnum.HEARTBEAT_SECOND_ERROR);
        }
        Long endTime = System.currentTimeMillis();
        Long startTime = endTime - seconds;
        Date end = DateUtil.ms2Date(endTime);
        Date start = DateUtil.ms2Date(startTime);
        HeartBeatExample example = new HeartBeatExample();
        HeartBeatExample.Criteria criteria = example.createCriteria();
        criteria.andUpdateTimeBetween(start, end);
        example.setOrderByClause(" update_time desc");
        return heartBeatMapper.selectByExample(example);
    }

    /**
     * 根据用户类型查询所有用户心跳
     *
     * @param userType
     * @return
     * @throws AppException
     */
    @Override
    public List<HeartBeat> queryHeartBeatByUserType(Integer userType) throws AppException {
        if (userType == null || userType < 0) {
            LOGGER.error("{} 查询心跳异常 用户类型异常 {}", LOG_PREFIX, userType);
            throw new AppException(ResultEnum.HEARTBEAT_USER_TYPE_ERROR);
        }
        HeartBeatExample example = new HeartBeatExample();
        HeartBeatExample.Criteria criteria = example.createCriteria();
        criteria.andUserTypeEqualTo(userType);
        example.setOrderByClause(" update_time desc");
        return heartBeatMapper.selectByExample(example);
    }

    /**
     * 根据用户类型和用户id查询所有心跳
     *
     * @param userType
     * @param uid
     * @return
     * @throws AppException
     */
    @Override
    public List<HeartBeat> queryHeartBeatByUserTypeAndUID(Integer userType, Integer uid) throws AppException {
        if (userType == null || userType < 0) {
            LOGGER.error("{} 查询心跳异常 用户类型异常 {}", LOG_PREFIX, userType);
            throw new AppException(ResultEnum.HEARTBEAT_USER_TYPE_ERROR);
        }
        if (uid == null || uid <= 0) {
            LOGGER.error("{} 查询心跳异常 用户id参数异常", LOG_PREFIX);
            throw new AppException(ResultEnum.HEARTBEAT_USER_ID_EROOR);
        }
        HeartBeatExample example = new HeartBeatExample();
        HeartBeatExample.Criteria criteria = example.createCriteria();
        criteria.andUserTypeEqualTo(userType);
        criteria.andUidEqualTo(uid);
        example.setOrderByClause(" update_time desc");
        return heartBeatMapper.selectByExample(example);
    }

    /**
     * 根据用户类型查询距当前时间多少秒内的所有心跳
     *
     * @param userType
     * @param seconds
     * @return
     * @throws AppException
     */
    @Override
    public List<HeartBeat> queryHeartBeatByUserTypeAndSeconds(Integer userType, Long seconds) throws AppException {
        if (userType == null || userType < 0) {
            LOGGER.error("{} 查询心跳异常 用户类型异常 {}", LOG_PREFIX, userType);
            throw new AppException(ResultEnum.HEARTBEAT_USER_TYPE_ERROR);
        }
        if (seconds == null || seconds < 0) {
            LOGGER.error("{} 秒数异常", LOG_PREFIX);
            throw new AppException(ResultEnum.HEARTBEAT_SECOND_ERROR);
        }
        Long endTime = System.currentTimeMillis();
        Long startTime = endTime - seconds;
        Date end = DateUtil.ms2Date(endTime);
        Date start = DateUtil.ms2Date(startTime);
        HeartBeatExample example = new HeartBeatExample();
        HeartBeatExample.Criteria criteria = example.createCriteria();
        criteria.andUpdateTimeBetween(start, end);
        criteria.andUserTypeEqualTo(userType);
        example.setOrderByClause(" update_time desc");
        return heartBeatMapper.selectByExample(example);
    }
}
