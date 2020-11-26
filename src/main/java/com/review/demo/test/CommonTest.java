package com.review.demo.test;

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
        Date currentDate = strToDate("20201124000000","yyyyMMddHHmmss");
        String endDate = format(getOneSecondsTime(currentDate), "yyyyMMddHHmmss");
        System.out.println(endDate);
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

}
