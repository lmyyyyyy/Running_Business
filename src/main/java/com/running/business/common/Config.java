package com.running.business.common;

/**
 * @author liumingyu
 * @create 2018-01-13 下午6:55
 */
public class Config {

    //用户session在redis存储的key
    public static final String REDIS_USER_SESSION_KEY = "REDIS_USER_SESSION";
    //session key
    public static final String SESSION_USERNAME = "SESSION_USERNAME";
    //websocket
    public static final String WEBSOCKET_USERNAME = "WEBSOCKET_USERNAME";
    //用户session的超时时间
    public static final Integer SSO_SESSION_EXPIRE = 1800;
    //ThreadLocal副本key
    public static final String USER_KEY = "run_user";
    //用户Cookie key
    public static final String TOKEN_COOKIE = "RUN_TOKEN";
    //用户前缀
    public static final String USER_TOKEN_HEAD = "RU";
    //配送员前缀
    public static final String DELIVERY_TOKEN_HEAD = "RD";
    //管理员前缀
    public static final String ADMIN_TOKEN_HEAD = "RA";
    //所有登录用户的id缓存key，为了心跳检测
    public static final String LOGIN_USER_IDS_KEY = "run_user_ids_key";
    public static final String LOGIN_USER_IDS_ERROR_KEY = "run_user_ids_error_key";
    //所有登录的配送员id缓存key，为了心跳检测
    public static final String LOGIN_DELIVERY_IDS_ERROR_KEY = "run_delivery_ids_error_key";
    public static final String LOGIN_DELIVERY_IDS_KEY = "run_delivery_ids_key";
    //所有登录的管理员id缓存key，为了心跳检测
    public static final String LOGIN_ADMIN_IDS_ERROR_KEY = "run_admin_ids_error_key";
    public static final String LOGIN_ADMIN_IDS_KEY = "run_admin_ids_key";
    //距现在该时间内的心跳（秒）
    public static final Integer HEART_BEAT_FRONT_TIME = 60;

    //拼接多长时间内的订单信息（秒）
    public static final Integer ROLLING_INFO_TIME = 8640;

    //是否打印日志
    public static final Boolean METHOD_LOG_PRINT_SWITCH = true;
    //是否记录sql
    public static final Boolean RECORD_SQL_SWITCH = true;
    //日志是否入库
    public static final Boolean Log_TO_DB = true;
    //mapper是否独立存储
    public static final Boolean MAPPER_LOG_SINGLE_TO_DB = true;
    //是否清除service栈
    public static final Boolean IS_CLEAR_SERVICE_LOG_LIST = true;
    //是否递归记录一个线程中的所有方法
    public static final Boolean SERVICE_LOG_RECURSION_SWITCH = true;

    //当配送员未登录情况下，当前配送员位置距源地址的默认距离 单位：m
    public static final Long ORDER_SOURCE_ADDRESS_DISTANCE = 2000L;
    //当配送员未登录情况下，订单的默认距离 单位：m
    public static final Long ORDER_DISTANCE = 2000L;
    //当配送员未登录情况下，当前配送员位置距目标地址的默认距离 单位：m
    public static final Long ORDER_TARGET_ADDRESS_DISTANCE = 2000L;

}
