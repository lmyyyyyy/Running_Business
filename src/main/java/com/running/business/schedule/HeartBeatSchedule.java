package com.running.business.schedule;

import com.running.business.common.Config;
import com.running.business.enums.UserTypeEnum;
import com.running.business.exception.AppException;
import com.running.business.mapper.JedisClient;
import com.running.business.pojo.HeartBeat;
import com.running.business.pojo.RunUser;
import com.running.business.service.HeartBeatService;
import com.running.business.service.RunAdminService;
import com.running.business.service.RunDeliveryuserService;
import com.running.business.service.RunUserService;
import com.running.business.util.RegexUtils;
import com.running.business.util.TimeUtil;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * 心跳定时任务
 *
 * @author liumingyu
 * @create 2018-01-13 下午9:47
 */
@Component
public class HeartBeatSchedule {

    private static final Logger LOGGER = LoggerFactory.getLogger(HeartBeatSchedule.class);

    private static final String LOG_PREFIX = "【心跳定时任务】 ";

    @Autowired
    private JedisClient jedisClient;

    @Autowired
    private HeartBeatService heartBeatService;

    @Autowired
    private RunUserService runUserService;

    @Autowired
    private RunDeliveryuserService runDeliveryuserService;

    @Autowired
    private RunAdminService runAdminService;

    @Scheduled(cron = "0/30 * *  * * ? ")
    public void heartBeatSchedule() {
        Long startTime = System.currentTimeMillis();
        System.out.println("心跳检测:老汉推车--" + TimeUtil.getCurrDateTime());
        List<HeartBeat> userHeartBeats = heartBeatService.queryHeartBeatByUserTypeAndSeconds(UserTypeEnum.USER.getCode(), Config.HEART_BEAT_FRONT_TIME * 1000L);
        List<HeartBeat> deliveryHeartBeats = heartBeatService.queryHeartBeatByUserTypeAndSeconds(UserTypeEnum.DELIVERY.getCode(), Config.HEART_BEAT_FRONT_TIME * 1000L);
        List<HeartBeat> adminHeartBeats = heartBeatService.queryHeartBeatByUserTypeAndSeconds(UserTypeEnum.ADMIN.getCode(), Config.HEART_BEAT_FRONT_TIME * 1000L);

        LOGGER.info("{} 库中距现在{}秒内活跃的人员集合 用户:{},配送员:{},管理员:{}", LOG_PREFIX, Config.HEART_BEAT_FRONT_TIME, userHeartBeats, deliveryHeartBeats, adminHeartBeats);

        Set<String> userIdList = userHeartBeats.stream().map(heartbeat -> String.valueOf(heartbeat.getUid())).collect(Collectors.toSet());
        Set<String> deliveryIdList = deliveryHeartBeats.stream().map(heartBeat -> String.valueOf(heartBeat.getUid())).collect(Collectors.toSet());
        Set<String> adminIdList = adminHeartBeats.stream().map(heartBeat -> String.valueOf(heartBeat.getUid())).collect(Collectors.toSet());

        LOGGER.info("{} 将对象集合转成ID集合 用户:{},配送员:{},管理员:{}", LOG_PREFIX, userIdList, deliveryIdList, adminIdList);

        Set<String> userIds = jedisClient.smembers(Config.LOGIN_USER_IDS_KEY);
        Set<String> deliveryIds = jedisClient.smembers(Config.LOGIN_DELIVERY_IDS_KEY);
        Set<String> adminIds = jedisClient.smembers(Config.LOGIN_ADMIN_IDS_KEY);
        if (userIds.isEmpty() && deliveryIds.isEmpty() && adminIds.isEmpty()) {
            LOGGER.info("{} 目前没有登录状态的人员 任务结束", LOG_PREFIX);
            return;
        }
        LOGGER.info("{} 所有最近登录的人员id集合的缓存 用户:{},配送员:{},管理员:{}", LOG_PREFIX, userIds, deliveryIds, adminIds);

        Set<String> userSubtractStrs = distinctSet(userIdList, userIds);
        Set<String> deliverySubtractStrs = distinctSet(deliveryIdList, deliveryIds);
        Set<String> adminSubtractStrs = distinctSet(adminIdList, adminIds);
        if (userSubtractStrs.isEmpty() && deliverySubtractStrs.isEmpty() && adminSubtractStrs.isEmpty()) {
            LOGGER.info("{} 目前没有异常退出的用户 任务结束", LOG_PREFIX);
            return;
        }
        LOGGER.info("{} 取缓存中的集合与库中集合的差值 {}", LOG_PREFIX, userSubtractStrs);

        Set<Integer> userSubtractList = str2Int(userSubtractStrs);
        Set<Integer> deliverySubtractList = str2Int(deliverySubtractStrs);
        Set<Integer> adminSubtractList = str2Int(adminSubtractStrs);
        if (userSubtractList.isEmpty() && deliverySubtractList.isEmpty() && adminSubtractList.isEmpty()) {
            LOGGER.info("{} 目前没有异常退出的用户 任务结束", LOG_PREFIX);
            return;
        }

        Set<Integer> userErrorList = filterLoginUserIds(userSubtractList);
        Set<Integer> deliveryErrorList = filterLoginDeliveryIds(deliverySubtractList);
        Set<Integer> adminErrorList = filterLoginAdminIds(adminSubtractList);
        if (userErrorList.isEmpty() && deliveryErrorList.isEmpty() && adminErrorList.isEmpty()) {
            LOGGER.info("{} 目前没有异常退出的人员 任务结束", LOG_PREFIX);
            return;
        }
        LOGGER.info("{} 去除列表中已经正常退出的人员剩下的集合 用户:{},配送员:{},管理员:{}", LOG_PREFIX, userErrorList, deliveryErrorList, adminErrorList);

        //将异常退出人员加到异常队列
        if (userErrorList != null && userErrorList.size() != 0) {
            String[] userErrors = set2Arr(userErrorList);
            jedisClient.sadds(Config.LOGIN_USER_IDS_ERROR_KEY, userErrors);
            asyncInvokeUser(userErrorList);
        }
        if (deliveryErrorList != null && deliveryErrorList.size() != 0) {
            String[] deliveryErrors = set2Arr(deliveryErrorList);
            jedisClient.sadds(Config.LOGIN_DELIVERY_IDS_ERROR_KEY, deliveryErrors);
            asyncInvokeDelivery(deliveryErrorList);
        }
        if (adminErrorList != null && adminErrorList.size() != 0) {
            String[] adminErrors = set2Arr(adminErrorList);
            jedisClient.sadds(Config.LOGIN_ADMIN_IDS_ERROR_KEY, adminErrors);
            asyncInvokeAdmin(adminErrorList);
        }
        LOGGER.info("{} 将异常退出的人员集合存入异常缓存队列 用户:{},配送员:{},管理员:{}", LOG_PREFIX, userErrorList, deliveryErrorList, adminErrorList);
        LOGGER.info("{} 任务执行结束，耗时{}毫秒", LOG_PREFIX, System.currentTimeMillis() - startTime);
    }

    /**
     * 异步更新用户状态，清除异常缓存队列
     *
     * @param errorList
     */
    @Async
    public void asyncInvokeUser(Set<Integer> errorList) {
        if (errorList == null || errorList.size() == 0) {
            LOGGER.info("{} 目前没有异常退出的用户 用户异步任务结束", LOG_PREFIX);
            return;
        }
        runUserService.updateUserListStatus(errorList);
        //清除正常队列
        jedisClient.srems(Config.LOGIN_USER_IDS_KEY, set2Arr(errorList));
        //清除异常队列
        jedisClient.srems(Config.LOGIN_USER_IDS_ERROR_KEY, set2Arr(errorList));
        LOGGER.info("{} 异步更新用户状态，清除异常缓存队列完毕 {}", LOG_PREFIX, errorList);
    }
    @Async
    public void asyncInvokeDelivery(Set<Integer> errorList) {
        if (errorList == null || errorList.size() == 0) {
            LOGGER.info("{} 目前没有异常退出的配送员 配送员异步任务结束", LOG_PREFIX);
            return;
        }
        runDeliveryuserService.updateDeliveryListStatus(errorList);
        //清除正常队列
        jedisClient.srems(Config.LOGIN_DELIVERY_IDS_KEY, set2Arr(errorList));
        //清除异常队列
        jedisClient.srems(Config.LOGIN_DELIVERY_IDS_ERROR_KEY, set2Arr(errorList));
        LOGGER.info("{} 异步更新配送员状态，清除异常缓存队列完毕 {}", LOG_PREFIX, errorList);
    }
    @Async
    public void asyncInvokeAdmin(Set<Integer> errorList) {
        if (errorList == null || errorList.size() == 0) {
            LOGGER.info("{} 目前没有异常退出的管理员 管理员异步任务结束", LOG_PREFIX);
            return;
        }
        runAdminService.updateAdminListStatus(errorList);
        //清除正常队列
        jedisClient.srems(Config.LOGIN_ADMIN_IDS_KEY, set2Arr(errorList));
        //清除异常队列
        jedisClient.srems(Config.LOGIN_ADMIN_IDS_ERROR_KEY, set2Arr(errorList));
        LOGGER.info("{} 异步更新管理员状态，清除异常缓存队列完毕 {}", LOG_PREFIX, errorList);
    }

    /**
     * String转int
     * @param set
     * @return
     * @throws AppException
     */
    public Set<Integer> str2Int(Set<String> set) throws AppException {
        if (set == null || set.size() == 0) {
            return new HashSet<>();
        }
        Set<Integer> result = new HashSet<>();
        for (String str : set) {
            if (str == null || "".equals(str)) {
                continue;
            }
            if (RegexUtils.checkDigit(str)) {
                result.add(Integer.valueOf(str));
            }
        }
        return result;
    }

    /**
     * 根据id集合查询出当前在线状态的ID集合(去除正常退出的人员)
     *
     * @param set
     * @return
     * @throws AppException
     */
    public Set<Integer> filterLoginUserIds(Set<Integer> set) throws AppException {
        if (set == null || set.size() == 0) {
            return new HashSet<>();
        }
        return runUserService.queryUsersByIds(set);
    }
    public Set<Integer> filterLoginDeliveryIds(Set<Integer> set) throws AppException {
        if (set == null || set.size() == 0) {
            return new HashSet<>();
        }
        return runDeliveryuserService.queryDeliverysByIds(set);
    }
    public Set<Integer> filterLoginAdminIds(Set<Integer> set) throws AppException {
        if (set == null || set.size() == 0) {
            return new HashSet<>();
        }
        return runAdminService.queryAdminsByIds(set);
    }

    /**
     * 取差集
     *
     * @param set1
     * @param set2
     * @return
     * @throws AppException
     */
    public Set<String> distinctSet(Set<String> set1, Set<String> set2) throws AppException {
        return set2.stream().filter(t -> !set1.contains(t)).collect(Collectors.toSet());
    }

    /**
     * set集合转字符串数组
     *
     * @param set
     * @return
     * @throws AppException
     */
    public String[] set2Arr(Set<Integer> set) throws AppException {
        String[] arr = new String[set.size()];
        int count = 0;
        for (Integer id : set) {
            if (id == null || id < 0) {
                continue;
            }
            arr[count++] = String.valueOf(id);
        }
        return arr;
    }
}
