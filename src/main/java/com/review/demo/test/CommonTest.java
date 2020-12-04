package com.review.demo.test;

import com.review.demo.Excel.model.User;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CommonTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonTest.class);

    public static Date strToDate(String strDate, String strFormat)
    {
        // 如果入参为null则直接返回null
        if (StringUtils.isEmpty(strDate) || StringUtils.isEmpty(strFormat))
        {
            return null;
        }

        SimpleDateFormat df = null;
        Date date = null;
        try
        {
            // 使用指定的格式创建日期时间格式
            df = new SimpleDateFormat(strFormat);
        }
        catch (IllegalArgumentException e)
        {
            LOGGER.error(
                    "date format error,use default,current format=" + strFormat, e);
            df = new SimpleDateFormat("yyyyMMddHHmmss");
        }

        try
        {
            // 解析指定的字符串
            date = df.parse(strDate);
        }
        catch (ParseException e)
        {
            LOGGER.error(
                    "parse failure,date=" + strDate + "format=" + strFormat, e);

            // 如果解析失败则使用当前日期
            date = new Date();
        }

        return date;
    }

    public static void main(String[] args) {
        /*Date currentDate = strToDate("20201124000000","yyyyMMddHHmmss");
        String endDate = format(getOneSecondsTime(currentDate), "yyyyMMddHHmmss");
        System.out.println(endDate);*/
        /*result:
        for (int i = 0; ; i++) {
            for (int j = 0; j<5; j++) {
                System.out.println("i|"+i+",j|"+j);
                if (i==2) {
                    break result;
                }
            }
        }
        System.out.println("**********break**********");*/
        /*String s1 = "Programming";
        String s2 = new String("Programming");
        String s3 = "Program";
        String s4 = "ming";
        String s5 = "Program" + "ming";
        String s6 = s3 + s4;
        System.out.println(s1 == s2);//false
        System.out.println(s1 == s5);//true
        System.out.println(s1 == s6);//false
        System.out.println(s1 == s6.intern());//true
        System.out.println(s2 == s2.intern());//false*/

        System.out.println(reverse("1ab:seyx"));
    }

    public static String format(Date date, String formatStr)
    {
        if (date == null)
        {
            return null;
        }

        SimpleDateFormat sf = new SimpleDateFormat(formatStr);
        return sf.format(date);
    }

    /**
     * 获取前一秒的时间
     * @param date
     * @return
     */
    public static Date getOneSecondsTime(Date date) {
        Calendar c = new GregorianCalendar();
        c.setTime(date);//设置参数时间
        c.add(Calendar.SECOND,-1);//减一秒
        return c.getTime();
    }


    /**
     * String 字符串倒排
     * @param originStr
     * @return
     */
    public static String reverse(String originStr) {
        if (originStr == null || originStr.length() <= 1) {
            return originStr;
        }
        return reverse(originStr.substring(1)) + originStr.charAt(0);
    }

}
