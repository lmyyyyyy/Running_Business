package com.running.business.util;

import java.security.MessageDigest;

/**
 * 字符串工具类
 *
 */
public class Run_StringUtil {

    /**
     * md5加密
     *
     * @param s
     * @return
     */
    public final static String MD5(String s) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            byte[] btInput = s.getBytes("UTF-8");
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 生成订单号 17位(yyyyMMddHHmmssSSS)+3位随机数
     *
     * @return
     */
    @SuppressWarnings("static-access")
	public static String getOrderId() {
        StringBuffer orderIdstb = new StringBuffer();
        String currTime = TimeUtil.currentDateTimeMillis(); // 当前时间
        // 生成随机数
        RandomUtil random = new RandomUtil();
        orderIdstb = orderIdstb.append(currTime).append(random.generateRandomDigitString(3));
        return orderIdstb.toString();
    }

}
