package com.review.demo.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.review.demo.fastjson.model.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;

/***
 * FastJson 保留时间格式
 * 对象转Map
 *
 * @Author ZH
 * @version 1.0.0
 * @Date 2020/12/2
 */
public class FastJsonUtil {

    private static String dateFormat = "yyyy-MM-dd HH:mm:ss.SSS";

    public static Map<String, Object> ObjToMap(User user) {
        String result = JSON.toJSONStringWithDateFormat(user, dateFormat, SerializerFeature.WriteDateUseDateFormat);
        return (Map<String, Object>) JSONObject.parse(result);
    }

    public static void main(String[] args) throws ParseException {
        User user = new User();
        user.setId("111");
        user.setUserId("user111");
        user.setType("type111");
        user.setStatus(111);
        user.setTotalAmount(11122);
        user.setCreateTime(new Date());
        System.out.println(ObjToMap(user));
    }
}
