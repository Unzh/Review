package com.review.demo.Utils;

/**
 * 位运算符的运用
 */
public class CalculateUtil {

    /**
     * 利用位运算来计算扩容得值
     *
     * @param minimumValue
     * @param maximumValue
     * @return
     */
    protected static int calculateDigits(int minimumValue, int maximumValue) {
        int digit = 0;
        int value = 1;
        while (value < minimumValue && value < maximumValue) {
            value <<= 1;
            digit++;
        }
        return digit;
    }
}
