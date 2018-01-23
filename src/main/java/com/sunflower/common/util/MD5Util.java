package com.sunflower.common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密工具类
 * 
 * 类名称：MD5Util 创建时间：Jan 20, 2018
 * 
 * @version 1.0.0
 * 
 */
public class MD5Util {
	private static char[] DigitLower = { '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
	private static char[] DigitUpper = { '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

	public static String getMD5Lower(String srcStr)
			throws NoSuchAlgorithmException, NullPointerException {
		String sign = "lower";
		return processStr(srcStr, sign);
	}

	private static String processStr(String srcStr, String sign)
			throws NoSuchAlgorithmException, NullPointerException {
		if (srcStr == null) {
			throw new NullPointerException("加密字符串为空");
		}
		String algorithm = "MD5";

		String result = "";

		MessageDigest digest = MessageDigest.getInstance(algorithm);
		digest.update(srcStr.getBytes());

		byte[] byteRes = digest.digest();

		int length = byteRes.length;
		for (int i = 0; i < length; i++) {
			result = result + byteHEX(byteRes[i], sign);
		}
		return result;
	}

	private static String byteHEX(byte bt, String sign) {
		char[] temp = null;
		if (sign.equalsIgnoreCase("lower")) {
			temp = DigitLower;
		} else if (sign.equalsIgnoreCase("upper")) {
			temp = DigitUpper;
		} else {
			throw new NullPointerException("缺少加密必要的条件");
		}
		char[] ob = new char[2];

		ob[0] = temp[(bt >>> 4 & 0xF)];

		ob[1] = temp[(bt & 0xF)];

		return new String(ob);
	}

	public static void main(String[] args) throws NoSuchAlgorithmException,
			NullPointerException {
		System.out.println(getMD5Lower("admin1"));
	}
}
