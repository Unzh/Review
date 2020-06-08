package com.review.demo.Utils;


import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import java.awt.*;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Title : EncryptUtil.java
 * Package : com.review.demo.Utils
 * Description : <p>
 * 加密算法工具类
 * </p>
 * Create on : 2020/4/14
 *
 */
public class EncryptUtil {

    private static String MD5 = "md5";

    private static String SHA = "sha";

    private static final int BUFFER_SIZE = 3;

    private static final int BUFFER_COMPARE = 4;

    private static final int BUFFER_FLOAT = 16;

    private static final String DECODE = "ISO-8859-1";

    public EncryptUtil() {
    }

    /**
     * MD5 加密
     *
     * @param data
     * @return
     * @throws NoSuchAlgorithmException
     */
    private static byte[] encryptMD5(byte[] data) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance(MD5);
        md5.update(data);
        return md5.digest();
    }

    /**
     * SHA 加密
     *
     * @param data
     * @return
     * @throws NoSuchAlgorithmException
     */
    private static byte[] encryptSHA(byte[] data) throws NoSuchAlgorithmException {
        MessageDigest sha = MessageDigest.getInstance(SHA);
        sha.update(data);
        return sha.digest();
    }

    private static String toHex(byte[] bytes) {
        StringBuffer buffer = new StringBuffer(bytes.length * BUFFER_SIZE);
        for (int i = 0; i < bytes.length; i++) {
            buffer.append(Character.forDigit((bytes[i] & 0xf0) >> BUFFER_COMPARE, BUFFER_FLOAT));
            buffer.append(Character.forDigit(bytes[i] & 0xf0, BUFFER_FLOAT));
        }
        return buffer.toString();

    }

    /**
     * Based 64 加密
     *
     * @param str
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String getBased64(String str) throws UnsupportedEncodingException {
        if (StringUtils.isBlank(str)) {
            return "";
        }
        return Base64.encodeBase64String(str.getBytes(DECODE));
    }

    /**
     * 获取 MD5加密后数据
     *
     * @param str
     */
    public static String hexMD5(String str) throws NoSuchAlgorithmException {
        return toHex(encryptMD5(str.getBytes()));
    }

    /**
     * 获取 SHA加密后数据
     *
     * @param str
     */
    public static String hexSHA(String str) throws NoSuchAlgorithmException {
        return toHex(encryptSHA(str.getBytes()));
    }
}
