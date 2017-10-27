package com.running.business.common;

/**
 * 例如：无效用户可以定义为 [10010001]
 * 前四位数为系统模块编号，后4位为错误代码 ,唯一。
 */
public enum ResultEnum {
	
    // 用户相关异常
    TELTPHONE_USED("1001001", "用户已注册"),
    TELTPHONE_NOT_REG("1001002", "账号或密码错误"),
    PWD_ERROR("1001003","密码错误"),
    QUERY_ERROR("1001004", "错误的查询"),
    PURL_NULL("1001005", "头像不能为空"),
    PURL_TYPE_WORNG("1001006", "不支持的类型"),
    PURL_TOO_BIG("1001007", "图片过大"),
    NOT_MSG("1001008", "我也是有底线的"),
    DEL_ERROR("1001009", "删除失败"),
    // 系统异常
    INPUT_ERROR("99980000", "参数非法"),
    INNER_ERROR("99980001", "系统异常"),
    TOKEN_IS_ILLICIT("99980002", "Token验证非法"),
    SESSION_IS_OUT_TIME("99980003", "会话超时");

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
