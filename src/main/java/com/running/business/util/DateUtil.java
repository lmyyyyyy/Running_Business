package com.running.business.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 转日期
 */
public class DateUtil {
    /**
     * 变量：日期格式化类型 - 格式:yyyy/MM/dd
     */
    public static final int DEFAULT = 0;
    public static final int YM = 1;

    /**
     * 变量：日期格式化类型 - 格式:yyyy-MM-dd
     */
    public static final int YMR_SLASH = 11;

    /**
     * 变量：日期格式化类型 - 格式:yyyyMMdd
     */
    public static final int NO_SLASH = 2;

    /**
     * 变量：日期格式化类型 - 格式:yyyyMM
     */
    public static final int YM_NO_SLASH = 3;

    /**
     * 变量：日期格式化类型 - 格式:yyyy/MM/dd HH:mm:ss
     */
    public static final int DATE_TIME = 4;

    /**
     * 变量：日期格式化类型 - 格式:yyyyMMddHHmmss
     */
    public static final int DATE_TIME_NO_SLASH = 5;

    /**
     * 变量：日期格式化类型 - 格式:yyyy/MM/dd HH:mm
     */
    public static final int DATE_HM = 6;

    /**
     * 变量：日期格式化类型 - 格式:HH:mm:ss
     */
    public static final int TIME = 7;

    /**
     * 变量：日期格式化类型 - 格式:HH:mm
     */
    public static final int HM = 8;

    /**
     * 变量：日期格式化类型 - 格式:HHmmss
     */
    public static final int LONG_TIME = 9;
    /**
     * 变量：日期格式化类型 - 格式:HHmm
     */

    public static final int SHORT_TIME = 10;

    /**
     * 变量：日期格式化类型 - 格式:yyyy-MM-dd HH:mm:ss
     */
    public static final int DATE_TIME_LINE = 12;

    public static String dateToStr(Date date, int type) {
        switch (type) {
            case DEFAULT:
                return dateToStr(date);
            case YM:
                return dateToStr(date, "yyyy/MM");
            case NO_SLASH:
                return dateToStr(date, "yyyyMMdd");
            case YMR_SLASH:
                return dateToStr(date, "yyyy-MM-dd");
            case YM_NO_SLASH:
                return dateToStr(date, "yyyyMM");
            case DATE_TIME:
                return dateToStr(date, "yyyy/MM/dd HH:mm:ss");
            case DATE_TIME_NO_SLASH:
                return dateToStr(date, "yyyyMMddHHmmss");
            case DATE_HM:
                return dateToStr(date, "yyyy/MM/dd HH:mm");
            case TIME:
                return dateToStr(date, "HH:mm:ss");
            case HM:
                return dateToStr(date, "HH:mm");
            case LONG_TIME:
                return dateToStr(date, "HHmmss");
            case SHORT_TIME:
                return dateToStr(date, "HHmm");
            case DATE_TIME_LINE:
                return dateToStr(date, "yyyy-MM-dd HH:mm:ss");
            default:
                throw new IllegalArgumentException("Type undefined : " + type);
        }
    }

    public static String dateToStr(Date date, String pattern) {
        if (date == null || date.equals(""))
            return null;
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.format(date);
    }

    public static String dateToStr(Date date) {
        return dateToStr(date, "yyyy/MM/dd");
    }

    public static Date ms2Date(Long ms) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = format.format(ms);
        Date d = null;
        try {
            d = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d;
    }

    public static Long date2MS(Date date) {
        return date.getTime();
    }

    /**
     * 由过去的某一时间,计算距离当前的时间
     */
    public static String calculateTime(Date time) {
        long nowTime = System.currentTimeMillis();  //获取当前时间的毫秒数
        String msg = null;

        /*SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//指定时间格式
        Date setTime = null;  //指定时间
        try {
            setTime = sdf.parse(time);  //将字符串转换为指定的时间格式
        } catch (ParseException e) {

            e.printStackTrace();
        }
        */
        long reset = time.getTime();   //获取指定时间的毫秒数
        long dateDiff = nowTime - reset;

        if (dateDiff < 0) {
            msg = "输入的时间不对";
        } else {

            long dateTemp1 = dateDiff / 1000; //秒
            long dateTemp2 = dateTemp1 / 60; //分钟
            long dateTemp3 = dateTemp2 / 60; //小时
            long dateTemp4 = dateTemp3 / 24; //天数
            long dateTemp5 = dateTemp4 / 30; //月数
            long dateTemp6 = dateTemp5 / 12; //年数

            if (dateTemp6 > 0) {
                msg = dateTemp6 + "年前";

            } else if (dateTemp5 > 0) {
                msg = dateTemp5 + "个月前";

            } else if (dateTemp4 > 0) {
                msg = dateTemp4 + "天前";

            } else if (dateTemp3 > 0) {
                msg = dateTemp3 + "小时前";

            } else if (dateTemp2 > 0) {
                msg = dateTemp2 + "分钟前";

            } else if (dateTemp1 > 0) {
                msg = "刚刚";

            }
        }
        return msg;
    }

}
