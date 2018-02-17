package com.running.business.common;

/**
 * 例如：无效用户可以定义为 [10010001]
 * 前四位数为系统模块编号，后4位为错误代码 ,唯一。
 */
public enum ResultEnum {

    SUCCESS("200", "成功"),
    PERMISSION_ERROR("403", "您没有权限进行操作～"),
    ERROR("500", "服务器异常"),

    // 用户相关异常
    TELTPHONE_USED("1001001", "用户已注册"),
    TELTPHONE_NOT_REG("1001002", "账号或密码错误"),
    PWD_ERROR("1001003", "密码错误"),
    QUERY_ERROR("1001004", "错误的查询"),
    PURL_NULL("1001005", "头像不能为空"),
    PURL_TYPE_WORNG("1001006", "不支持的类型"),
    PURL_TOO_BIG("1001007", "图片过大"),
    NOT_MSG("1001008", "我也是有底线的"),
    DEL_ERROR("1001009", "删除失败"),
    USER_INFO_ISEMPTY("1001010", "用户信息为空"),
    USER_PHONE_REGEX_IS_NOT("1001011", "用户手机格式错误"),
    USER_PASSWORD_LEN("1001012", "密码长度不能低于6位，不能大于18位"),
    USER_PASSWORD_OLD_AND_NEW_IS_SAME("1001013", "新密码不能与旧密码相同"),
    USER_ID_IS_ERROR("1001014", "用户ID异常"),
    USER_IS_DELETE("1001015", "用户已被删除"),

    //用户优惠券相关异常
    USER_COUPON_INFO_EMTPY("1002001", "优惠券信息为空"),
    USER_COUPON_ID_IS_ERROR("1002002", "优惠券ID异常"),
    USER_COUPON_IS_EXPIRE("1002003", "优惠券已过期"),

    //用户偏好相关异常
    USER_PREFERENCE_INFO_EMTPY("1003001", "偏好不能为空"),
    USER_PREFERENCE_ID_ERROR("1003002", "偏好ID异常"),

    //用户余额相关异常
    USER_BALANCE_INFO_EMPTY("1004001", "用户余额信息为空"),
    USER_BALANCE_LESS("1004002", "用户余额不足"),

    //用户余额记录相关异常
    USER_BALANCE_RECORD_INFO_EMPTY("1005001", "用户余额记录信息为空"),
    USER_BALANCE_RECORD_NUMBER_ERROR("1005002", "交易流水号异常"),

    //用户地址相关异常
    USER_ADDRESS_INFO_EMTPY("1006001", "用户地址不能为空"),


    // 配送员相关异常
    TELTPHONE_DELIVERY("2001001", "用户已注册"),
    TELTPHONE_DELIVERY_ERROR("2001002", "账号或密码错误"),
    DELIVERY_PASSWORD_LEN("2001003", "密码长度不能低于6位，不能大于18位"),
    DELIVERY_PHONE_REGEX_IS_ERROR("2001004", "手机格式错误"),
    DELIVERY_INFO_ISEMPTY("2001005", "用户信息为空"),
    DELIVERY_CHECKING("2001006", "账号正在审核中,请耐心等待"),
    DELIVERY_PHOTO_TOO_BIG("2001007", "图片过大"),
    DELIVERY_REVIEWPHOTO_IS_NOT_PASS("2001008", "照片审核未通过"),
    DELIVERY_CARD_REGEX_IS_NOT_PASS("2001009", "身份证格式不正确"),
    DELIVERY_IS_NOT_AVAILABLE("2001010", "账号已被禁用"),
    DELIVERY_CREDITS_IS_TOO_LOW("2001011", "账号信用额度过低,已被禁用,如有疑问请忍着"),
    DELIVERY_REGISTER_SUCCESS("2001012", "账号注册成功,请填写真实的信息"),
    DELIVERY_CARD_CHECK_ERROR("2001013", "身份证验证异常"),
    DELIVERY_ADDRESS_IS_EMPTY("2001014", "配送员地址为空"),
    DELIVERY_OLD_AND_NEW_IS_SAME("2001015", "新密码不能与旧密码相同"),
    DELIVERY_IS_DELETE("2001016", "配送员已被删除"),
    DELIVERY_ID_IS_ERROR("2001017", "配送员id异常"),

    //配送员配送距离相关异常
    DELIVERY_DISTANCE_INFO_EMPTY("2002001", "配送距离不能为空"),
    DELIVERY_DISTANCE_ID_ERROR("2002002", "配送距离ID异常"),
    DELIVERY_DISTANCE_SET_ERROR("2002003", "配送距离设置异常"),

    //配送员余额相关异常
    DELIVERY_BALANCE_INFO_EMPTY("2003001", "配送员余额不能为空"),
    DELIVERY_BALANCE_LESS("2003002", "余额不足"),

    //配送员余额记录相关异常
    DELIVERY_BALANCE_RECORD_EMPTY("2004001", "配送员余额记录为空"),

    //管理员相关
    ADMIN_ID_ERROR("3003001", "管理员ID异常"),
    ADMIN_INFO_IS_EMPTY("3003002", "管理员信息为空"),
    ADMIN_PASSWORD_REGEX_ERROR("3003003", "管理员密码格式错误"),
    ADMIN_PASSWORD_NEW_AND_OLD_IS_SAME("3003004", "新密码不能与旧密码相同"),
    ADMIN_IS_DELETE("3001005", "管理员已被删除"),

    //订单相关异常
    ORDER_GET_ERROR("4001001", "订单获取异常"),
    ORDER_PARAMETER_IS_EMPTY("4001002", "订单参数为空"),
    ORDER_GENERATOR_FAIL("4001003", "订单号生成失败"),
    ORDER_ID_IS_ERROR("4001004", "订单号异常"),
    ORDER_STATUS_IS_ERROR("4001005", "订单状态异常"),

    ORDER_PAY_INFO_EMPTY("4002001", "订单支付信息为空"),

    //心跳相关异常
    HEARTBEAT_INFO_EMPTY("5001001", "心跳为空"),
    HEARTBEAT_ID_ERROR("5001002", "心跳ID参数异常"),
    HEARTBEAT_USER_ID_EROOR("5001003", "心跳用户ID参数异常"),
    HEARTBEAT_DATE_ERROR("5001004", "时间参数异常"),
    HEARTBEAT_USER_TYPE_ERROR("5001005", "心跳用户类型异常"),
    HEARTBEAT_SECOND_ERROR("5001006", "秒数异常"),

    //滚动/推送消息异常
    INFO_IS_EMPTY("6001001", "消息不能为空"),
    INFO_ID_IS_ERROR("6001002", "消息ID不能为空"),

    //退款申请异常
    REFUND_INFO_IS_EMTPY("7001001", "退款消息不能为空"),
    REFUND_ID_IS_ERROR("7001002", "退款ID异常"),
    REFUND_ORDERID_IS_ERROR("7001003", "退款订单号异常"),
    REFUND_REASON_IS_EMPTY("7001004", "退款理由不能为空"),
    REFUND_APPLY_FAIL("7001005", "退款申请失败"),
    REFUND_APPLY_TIME_ERROR("7001006", "订单超过七天,不能申请退款"),
    REFUND_HAS_BEEN_APPLIED("7001007", "订单不能重复发起退款"),
    REFUND_APPLY_SUCCESS("7001008", "订单退款成功,退款金额在3-7个工作日内原路返回"),
    REFUND_APPLY_VERIFYING("7001009", "退款申请审核中..."),

    //退款记录
    REFUND_RECORD_IS_NULL("7002001", "退款记录不能为空"),
    REFUND_RECORD_ID_ERROR("7002002", "退款记录id异常"),

    //投诉异常
    REPORT_INFO_IS_EMPTY("8001001", "投诉信息为空"),
    REPORT_ID_IS_ERROR("8001002", "投诉ID异常"),
    REPORT_REASON_IS_EMPTY("8001003", "投诉理由不能为空"),
    REPORT_LEVEL_IS_ERROR("8001004", "投诉等级异常"),
    REPORT_ACTIVE_ERROR("8001005", "投诉主动方异常"),
    REPORT_HAVE_BEEN_ERROR("8001006", "不能重复发起投诉"),

    // 系统异常
    INPUT_ERROR("99980000", "参数非法"),
    INNER_ERROR("99980001", "系统异常"),
    TOKEN_IS_ILLICIT("99980002", "Token验证非法"),
    SESSION_IS_OUT_TIME("99980003", "会话超时"),
    COOKIE_IS_OUT_TIME("99980005", "cookie失效"),
    Number_THAN_BIG("99980004", "系统数据量过大"),
    NOT_NAME_TOKEN("99980005", "不知名的token"),
    NOT_HAVE_THIS_BIZID("99980006", "业务线初始化失败"),
    ACTION_CONTEXT("99980007", "ActionContext对象为空"),
    JSON_TO_ENTITY_ERROR("99980008", "json串转实体异常"),

    ;

    private String code;

    private String msg;

    ResultEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static ResultEnum getResultEnum(String theCode) {
        for (ResultEnum resultEnum : values()) {
            if (resultEnum.getCode().equals(theCode)) {
                return resultEnum;
            }
        }
        return null;
    }

}
