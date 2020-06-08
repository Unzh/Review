package com.review.demo.Calendar;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Title : CalendarUtil.java
 * Package : com.review.demo.Calendar
 * Description : <p>
 * 日期工具类
 * </p>
 * Create on : 2020/6/3
 *
 */
public class CalendarUtil {


    public static int judgeDay(TimeZone zone,
                               Locale aLocale) {
        /**
         * 不传参则按照当前的时区和地址来获取日期
         * Calendar calendar = Calendar.getInstance();
         */
        Calendar cal = Calendar.getInstance(zone, aLocale);
        return cal.get(Calendar.DAY_OF_WEEK);
    }

    public static void main(String[] args) {
        //CTT代表上海
        TimeZone timeZone = TimeZone.getTimeZone("CTT");
        int rest = CalendarUtil.judgeDay(timeZone, Locale.CHINESE);
        System.out.println(getWeek(rest));
    }

    public static String getWeek(int day) {
        String result = "";
        switch (day) {
            case 1:
                result = "SUNDAY";
                break;
            case 2:
                result = "MONDAY";
                break;
            case 3:
                result = "TUESDAY";
                break;
            case 4:
                result = "WEDNESDAY";
                break;
            case 5:
                result = "THURSDAY";
                break;
            case 6:
                result = "FRIDAY";
                break;
            case 7:
                result = "SATURDAY";
                break;
        }
        return result;
    }
}
