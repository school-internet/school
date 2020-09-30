package com.school.internet.utils;



import com.baomidou.mybatisplus.core.toolkit.StringUtils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by njwb on 2019/9/4.
 */
public class DateTimeUtils {
    public static final String LONG_TIME_FORMAT_WITH_SEC = "yyyy-MM-dd HH:mm:ss";
    public static final String LONG_TIME_FORMAT = "yyyy-MM-dd";

    public static final String LONG_MONTH_FORMAT = "yyyy-MM";

    public static final String TIME_FORMAT_WITH_SEC = "yyyyMMddHHmmss";
    public static final String TIME_FORMAT_WITH_MIN = "yyyyMMddHHmm";
    public static final String TIME_FORMAT_WITH_HOUR = "yyyyMMddHH";
    public static final String TIME_FORMAT_WITH_DAY = "yyyyMMdd";

    public static long dateTimeToMin(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(TIME_FORMAT_WITH_MIN);
        String str = sdf.format(date);
        return Long.parseLong(str);
    }

    public static long dateToUnixTime(String time, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return date2UnixTime(sdf.parse(time));
        }
        catch(Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static long dateTimeToHour(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(TIME_FORMAT_WITH_HOUR);
        String str = sdf.format(date);
        return Long.parseLong(str);
    }

    public static long dateTimeToDay(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(TIME_FORMAT_WITH_DAY);
        String str = sdf.format(date);
        return Long.parseLong(str);
    }

    public static String curDateTime(String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date());
    }

    public static String dateTime2Str(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static Date dateAddDay(Date date, int day) {
        if(date == null) {
            return null;
        }
        Calendar cl = Calendar.getInstance();
        cl.setTime(date);
        cl.add(Calendar.DATE, day);
        return cl.getTime();
    }

    public static long curTimeToUnixTime() {
        return new Date().getTime()/1000;
    }

    public static long date2UnixTime(Date date) {
        return date.getTime()/1000;
    }

    public static Date unixTime2Date(long seconds) {
        return new Date(seconds * 1000);
    }

    public static String unixTime2Date(long seconds, String format) {
        Date date = new Date(seconds * 1000);
        if(format == null) {
            format = LONG_TIME_FORMAT_WITH_SEC;
        }
        return dateTime2Str(date, format);
    }

    public static long longDate2Unix(String dateTime) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(LONG_TIME_FORMAT_WITH_SEC);
            Date d = sdf.parse(dateTime);
            return date2UnixTime(d);
        }
        catch(Exception e) {
            return 0;
        }
    }

    public static  String formatTime(){
        SimpleDateFormat sdf = new SimpleDateFormat(LONG_TIME_FORMAT_WITH_SEC);
        return sdf.format(new Date());
    }

    public static  String formatDate(){
        SimpleDateFormat sdf = new SimpleDateFormat(LONG_TIME_FORMAT);
        return sdf.format(new Date());
    }

    public static  String formatMonth(){
        SimpleDateFormat sdf = new SimpleDateFormat(LONG_MONTH_FORMAT);
        return sdf.format(new Date());
    }

    public static   String formatTimeNew(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        return LocalDateTime.now().format(formatter);
    }


    public static long longDate2Unix(String dateTime, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            Date d = sdf.parse(dateTime);
            return date2UnixTime(d);
        }
        catch(Exception e) {
            return 0;
        }
    }

    public static Date nextDateWithoutHms(int day) {
        int curDay = curDay();
        Calendar cl = Calendar.getInstance();
        cl.setTime(new Date());
        if(curDay >= day) {   //next month day
            int month = cl.get(Calendar.MONTH);
            cl.set(Calendar.MONTH, month + 1);
            cl.set(Calendar.DAY_OF_MONTH, day);
        }
        else {
            cl.set(Calendar.DAY_OF_MONTH, day);
        }

        cl.set(Calendar.HOUR_OF_DAY, 0);
        cl.set(Calendar.MINUTE, 0);
        cl.set(Calendar.SECOND, 0);
        return cl.getTime();
    }

    public static Date dateAddMonth(Date date, int month) {
        Calendar cl = Calendar.getInstance();
        cl.setTime(date);
        cl.set(Calendar.MONTH, month + month);
        return cl.getTime();
    }

    public static int curHour() {
        Calendar cl = Calendar.getInstance();
        cl.setTime(new Date());
        return cl.get(Calendar.HOUR_OF_DAY);
    }

    public static int curDay() {
        Calendar cl = Calendar.getInstance();
        cl.setTime(new Date());
        return cl.get(Calendar.DAY_OF_MONTH);
    }

    public static int curMonth() {
        Calendar cl = Calendar.getInstance();
        cl.setTime(new Date());
        return cl.get(Calendar.MONTH);
    }

    public static int curMin() {
        Calendar cl = Calendar.getInstance();
        cl.setTime(new Date());
        return cl.get(Calendar.MINUTE);
    }

    public static int curSecond() {
        Calendar cl = Calendar.getInstance();
        cl.setTime(new Date());
        return cl.get(Calendar.SECOND);
    }

    public static int curYear() {
        Calendar cl = Calendar.getInstance();
        cl.setTime(new Date());
        return cl.get(Calendar.YEAR);
    }


    //**mysql里的两个时间在天数上是否相等
    public static boolean unixTimeEqDay(long second1, long second2) {
        Date d1 = unixTime2Date(second1);
        Date d2 = unixTime2Date(second2);
        return (dateTimeToDay(d1) == dateTimeToDay(d2));
    }

    //是否在48小时之内
    public static boolean unixTimeIn48Hour(long startDate, long curDate) {
        Date d1 = unixTime2Date(startDate);
        Calendar cl = Calendar.getInstance();
        cl.setTime(d1);
        cl.add(Calendar.HOUR, 48);
        long max = cl.getTime().getTime();
        if(curDate * 1000 > max) {
            return false;
        }
        return true;
    }

    public static String curDate() {
        SimpleDateFormat sdf = new SimpleDateFormat(TIME_FORMAT_WITH_SEC);
        return sdf.format(new Date());
    }

    public static String curLongDate() {
        SimpleDateFormat sdf = new SimpleDateFormat(LONG_TIME_FORMAT);
        return sdf.format(new Date());
    }

    public static int towDaysDiff(Date start, Date end, boolean abs) {
        if(start == null || end == null) {
            return -1;
        }

        long day1 = start.getTime();
        long day2 = end.getTime();
        long diff = (day2 - day1)/(1000 * 3600 * 24);

        if(abs) {
            return Math.abs(Integer.parseInt(String.valueOf(diff)));
        }
        else {
            return Integer.parseInt(String.valueOf(diff));
        }
    }

    public static String dateAddDay(String date, int day, String format) {
        if(StringUtils.isBlank(format)) {
            format = LONG_TIME_FORMAT;
        }

        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            Date d = sdf.parse(date);
            Date d2 = dateAddDay(d, day);
            return sdf.format(d2);
        }
        catch(Exception e) {
            return date;
        }
    }

    public static Date str2Date(String date, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.parse(date);
        }
        catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Date jnStr2Date(String str) {
        if(StringUtils.isBlank(str) || "null".equalsIgnoreCase(str)) {
            return null;
        }

        str = str.replaceAll("T", " ");
        return str2Date(str, LONG_TIME_FORMAT_WITH_SEC);
    }

    public static  String getYearAndMonth(){
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH )+1;
        return year+"-"+month;
    }

    public static long jnStr2UnixDate(String str) {
        Date d = jnStr2Date(str);
        if(d == null) {
            return 0;
        }
        return date2UnixTime(d);
    }

    public static void main(String [] args) {
        System.out.println(formatTime());
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");

            System.out.print("222="+sdf.format(sdf.parse("2020-5-5")));
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(unixTimeIn48Hour(1465870210, curTimeToUnixTime()));

        System.out.println(getYearAndMonth());

        System.out.println(dateTime2Str(nextDateWithoutHms(1), LONG_TIME_FORMAT));
        System.out.println(dateTime2Str(nextDateWithoutHms(2), LONG_TIME_FORMAT));
        System.out.println(dateTime2Str(nextDateWithoutHms(3), LONG_TIME_FORMAT));
        System.out.println(dateTime2Str(nextDateWithoutHms(4), LONG_TIME_FORMAT));
        System.out.println(dateTime2Str(nextDateWithoutHms(5), LONG_TIME_FORMAT));
    }

}
