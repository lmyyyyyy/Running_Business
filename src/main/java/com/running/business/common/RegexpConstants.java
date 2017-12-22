package com.running.business.common;

public class RegexpConstants {
	//手机号正则
    public static final String phone_regrxp = "^1(3[0-9]|4[57]|5[0-35-9]|7[01678]|8[0-9])\\d{8}$";
    //只能输入01和02
    public static final String useremsg_type_regrxp = "[0][1-2]";
    //只能输入0和1
    public static final String course_optype_regrxp = "[0-1]";
    //只能输入2和3
    public static final String course_ctype_regrxp = "[2-3]";
    //只能输入1到4
    public static final String course_sktype_regrxp = "[1-4]";
    //只能输入1和2
    public static final String userfouce_optype_regrxp = "[1-2]";
}
