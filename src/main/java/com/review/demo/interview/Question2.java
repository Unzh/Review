package com.review.demo.interview;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 面试题二：有一个字符串它的构成是词+空格的组合，如“北京 杭州 杭州 北京”，
 * 要求输入一个匹配模式（简单的以字符来写）， 比如 aabb, 来判断该字符串是否符合该模式， 举个例子：
 * 1.  pattern = "abba", str="北京 杭州 杭州 北京" 返回 ture
 * 2.  pattern = "aabb", str="北京 杭州 杭州 北京" 返回 false
 * 3.  pattern = "abc", str="北京 杭州 杭州 南京" 返回 false
 * 4.  pattern = "acac", str="北京 杭州 北京 广州" 返回 false
 */
public class Question2 {

    private static final Map<String, String> mapCache = new HashMap<>();

    public boolean match(String pattern, String str) {
        if (StringUtils.isBlank(pattern) || StringUtils.isBlank(str)) {
            return false;
        }
        char[] chars = pattern.toCharArray();
        String[] strList = str.split(" ");
        if (chars.length != strList.length) {
            return false;
        }
        mapCache.put(String.valueOf(chars[0]), strList[0]);
        for (int i = 1; i < chars.length; i++) {
            String temp = mapCache.get(String.valueOf(chars[i]));
            if (temp != null) {
                if (!temp.equals(strList[i])) {
                    return false;
                }
            } else {
                mapCache.put(String.valueOf(chars[i]), strList[i]);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new Question2().match("abba","北京 杭州 杭州 北京"));
        System.out.println(new Question2().match("aabb","北京 杭州 杭州 北京"));
        System.out.println(new Question2().match("abcc","北京 杭州 南京 南京"));
    }


}
