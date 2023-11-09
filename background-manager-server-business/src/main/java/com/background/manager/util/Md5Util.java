package com.background.manager.util;

import java.security.MessageDigest;

/**
 * @Desc
 * @Author xhs
 * @Date 2020/12/15 23:02
 * @Version Jdk 1.8
 */
public class Md5Util {

    /**
     * MD5
     */
    private static final String hexDigIts[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};
    /**
     * 编码
     */
    private static final String charsetname = "utf-8";

    /**
     * Md5加密
     *
     * @param pwd
     * @return
     */
    public static String MD5Encode(String pwd) {
        String resultString = null;
        try {
            resultString = new String(pwd);
            MessageDigest md = MessageDigest.getInstance("MD5");
            if (null == charsetname || "".equals(charsetname)) {
                resultString = byteArrayToHexString(md.digest(resultString
                        .getBytes()));
            } else {
                resultString = byteArrayToHexString(md.digest(resultString
                        .getBytes(charsetname)));
            }
        } catch (Exception e) {
        }
        return resultString;
    }

    public static String byteArrayToHexString(byte b[]) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    public static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
            n += 256;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigIts[d1] + hexDigIts[d2];
    }

    public static void main(String[] args) {
        System.out.println(MD5Encode("12345"));
    }
}
