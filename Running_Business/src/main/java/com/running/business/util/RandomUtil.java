package com.running.business.util;

import java.security.SecureRandom;
import java.util.Random;

/**
 * 此功能的使用说明： 此功能可供其它项目产生随机数提供服务或参考。
 * 使用java.util.Random每次调用会产生相同的结果，而使用java.security.SecureRandom初始化速度又较慢，
 * 所以，首次产生随机数时会较慢。
 */
public class RandomUtil {

	/**
	 * 产生指定长度的无规律数字字符串
	 * 
	 * @param aLength
	 *            生成的随机数的长度
	 * @return 生成的随机字符串 throws 卡号生成异常
	 */
	public static String generateRandomDigitString(int aLength) {
		SecureRandom tRandom = new SecureRandom();
		long tLong;
		String aString = "";

		tRandom.nextLong();
		tLong = Math.abs(tRandom.nextLong());
		aString = (String.valueOf(tLong)).trim();
		while (aString.length() < aLength) {
			tLong = Math.abs(tRandom.nextLong());
			aString += (String.valueOf(tLong)).trim();
		}
		aString = aString.substring(0, aLength);

		return aString;
	}

	/**
	 * 产生随机字符串，长度由参数指定。
	 * 
	 * @param length
	 *            产生的字符串的长度
	 * @return 已产生的字符串
	 */
	public static String getRandString(int length) {
		String charList = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String rev = "";
		Random f = new Random();
		for (int i = 0; i < length; i++) {
			rev += charList.charAt(Math.abs(f.nextInt()) % charList.length());
		}
		return rev;
	}

	/**
	 * @Title: getRandomString
	 * @Description: 随机产生不含'0'和'O'字母数字组合字符串，长度由参数指定。
	 * @author he_jc
	 * @since 2014-9-3
	 * @param length
	 * @return
	 */
	public static String getRandomString(int length) {
		String charList1 = "2345678923456789abcdefefghigkmnpqrstuvwxyzABCDEFGHIJKLMNPQRSTUVWXYZ";
		StringBuffer rev = new StringBuffer();
		Random f = new Random();
		int num = 0;
		int cha = 0;
		int count = 0;
		for (; count < length;) {
			int ran = f.nextInt(65);
			if (ran < 16 && num < 2) {
				rev.append(charList1.charAt(ran));
				num++;
				count++;
			} else if (ran >= 16 && cha < 2) {
				rev.append(charList1.charAt(ran));
				cha++;
				count++;
			}
		}

		return rev.toString();
	}

	/**
	 * 将字符串中的字母转换成大写
	 * 
	 * @param aString
	 *            待处理的字符串
	 * 
	 * @return 字母转换成大写的字符串
	 */
	public static String toUpperCase(String aString) {
		return aString.toUpperCase();
	}

	/**
	 * 将字符串中的字母转换成小写
	 * 
	 * @param aString
	 *            待处理的字符串
	 * @return 字母转换成小写的字符串
	 */
	public static String toLowerCase(String aString) {
		return aString.toLowerCase();
	}

	/**
	 * 将字符串中的非字符转换成字母
	 * 
	 * @param aString
	 *            待处理的字符串
	 * 
	 * @return 转换后的字符串
	 */
	public static String toLetterOrDigit(String aString) {
		StringBuffer tString = new StringBuffer();
		for (int i = 0; i < aString.length(); i++) {
			if (Character.isLetterOrDigit(aString.charAt(i))) {
				tString.append(aString.charAt(i));
			} else {
				tString.append("F");
			}
		}
		return tString.toString();
	}

	/**
	 * 将字符串中的非字母转换成字母
	 * 
	 * @param aString
	 *            待处理的字符串
	 * 
	 * @return 转换后的字符串
	 */
	public static String toLetter(String aString) {
		StringBuffer tString = new StringBuffer();
		for (int i = 0; i < aString.length(); i++) {
			if (Character.isLetter(aString.charAt(i))) {
				tString.append(aString.charAt(i));
			} else if (Character.isDigit(aString.charAt(i))) {
				switch (aString.charAt(i)) {
				case '0': {
					tString.append("A");
					break;
				}
				case '1': {
					tString.append("B");
					break;
				}
				case '2': {
					tString.append("C");
					break;
				}
				case '3': {
					tString.append("D");
					break;
				}
				case '4': {
					tString.append("E");
					break;
				}
				case '5': {
					tString.append("F");
					break;
				}
				case '6': {
					tString.append("G");
					break;
				}
				case '7': {
					tString.append("H");
					break;
				}
				case '8': {
					tString.append("I");
					break;
				}
				case '9': {
					tString.append("J");
					break;
				}
				}
			} else {
				tString.append("M");
			}
		}
		return tString.toString();
	}

	public static void main(String[] args) {
		for (int i = 0; i < 100; i++)
			System.out.println(RandomUtil.generateRandomDigitString(3));
	}
}