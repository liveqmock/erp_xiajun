package com.wangqin.globalshop.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimeUtil {

    protected static Logger    log                             = LoggerFactory.getLogger(TimeUtil.class);

    /** yyyy-MM-dd HH:mm:ss */
    public static String       DEFAULT_DATE_FORMAT             = "yyyy-MM-dd HH:mm:ss";
    /** yyyy-MM-dd */
    public static String       DEFAULT_DAY_FORMAT              = "yyyy-MM-dd";
    /** yyyyMMddHHmmss */
    public static String       DEFAULT_DATE_NO_SEPRATOR_FORMAT = "yyyyMMddHHmmss";
    /** yyyyMMdd */
    public static final String DEFAULT_DAY_NO_SEPRATOR_FORMAT  = "yyyyMMdd";
    /** dd/MM/yyyy */
    public static final String DEFAULT_SLASH_FORMAT            = "dd/MM/yyyy";

    /**
     * 指定日期格式，转化时间字符串为Date对象
     * 
     * @param pattern
     * @param dateString
     * @return
     */
    public static Date parseDate(String pattern, String dateString) {
        DateFormat df = new SimpleDateFormat(pattern);
        try {
            return df.parse(dateString);
        } catch (ParseException e) {
            log.error("time format error", e);
            return null;
        }
    }

    /**
     * 把yyyy-MM-dd格式的时间字符串转化时间为Date对象
     * 
     * @param pattern
     * @param dateString
     * @return
     */
    public static Date parseDateToDay(String dateString) {
        if (StringUtil.isBlank(dateString)) {
            return null;
        }
        return parseDate(DEFAULT_DAY_FORMAT, dateString);

    }

    /**
     * 指定日期格式，获取当前时间的Date对象
     * 
     * @param pattern
     * @param dateString
     * @return
     */
    public static Date getCurrentDate(String pattern) {
        if (StringUtil.isBlank(pattern)) {
            return null;
        }
        DateFormat df = new SimpleDateFormat(pattern);
        try {
            return df.parse(new Date().toString());
        } catch (ParseException e) {
            log.error("time format error", e);
            return null;
        }
    }

    /**
     * 返回当前时间的 yyyy-MM-dd HH:mm:ss字符串
     * 
     * @return
     */
    public static String getCurrentDateDefaultString() {
        return getCurrentDateString(DEFAULT_DATE_FORMAT);
    }

    /**
     * 返回当前时间的指定格式的字符串
     * 
     * @return
     */
    public static String getCurrentDateString(String pattern) {
        if (StringUtil.isBlank(pattern)) {
            return null;
        }
        DateFormat df = new SimpleDateFormat(pattern);
        return df.format(new Date());
    }

    /**
     * 返回指定时间的yyyy-MM-dd HH:mm:ss格式字符串
     * 
     * @return
     */
    public static String getDateDefaultString(Date date) {
        if (date == null) {
            return null;
        }
        return getDateString(date, DEFAULT_DATE_FORMAT);
    }

    /**
     * 返回指定时间的指定格式的字符串
     * 
     * @param date
     * @param pattern
     * @return
     */
    public static String getDateString(Date date, String pattern) {
        if (date == null || StringUtil.isBlank(pattern)) {
            return null;
        }
        DateFormat df = new SimpleDateFormat(pattern);
        return df.format(date);
    }

    /**
     * 返回指定时间的指定格式的字符串
     * 
     * @param date
     * @param pattern
     * @return
     */
    public static String getDateString(Date date, String pattern, String timeZone) {
        if (date == null || StringUtil.isEmpty(pattern)) {
            return null;
        }
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        dateFormat.setTimeZone(TimeZone.getTimeZone(timeZone));
        return dateFormat.format(date);
    }

    /**
     * 比较日期是否大于当前日期
     */
    public static boolean afterNow(Date date) {
        if (date == null) {
            return false;
        }

        Calendar nowCar = Calendar.getInstance();
        Calendar car = Calendar.getInstance();

        car.setTime(date);

        return car.after(nowCar);
    }

    /**
     * 获取系统当前日期(精确到毫秒)，格式：yyyyMMddHHmmss
     * 
     * @return
     */
    public static String getDateStringWithNoSeparator() {
        Date date = new Date();
        DateFormat df = new SimpleDateFormat(DEFAULT_DATE_NO_SEPRATOR_FORMAT);
        return df.format(date);
    }

    /**
     * 获取系统当期年月日(精确到天)，格式：yyyyMMdd
     * 
     * @return
     */
    public static String getDate() {
        Date date = new Date();
        DateFormat df = new SimpleDateFormat(DEFAULT_DAY_NO_SEPRATOR_FORMAT);
        return df.format(date);
    }

    /**
     * 将短时间格式字符串转换为时间 yyyy-MM-dd
     * 
     * @param strDate
     * @return
     */
    public static Date strToDate(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

    public static String getHourAndMinute(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        return dateFormat.format(date);

    }

    public static String getHourAndMinute(Date date, String timeZoneID) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        dateFormat.setTimeZone(TimeZone.getTimeZone(timeZoneID));
        return dateFormat.format(date);

    }

    public static String getDate(String dateformat, Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(dateformat);
        return dateFormat.format(date);
    }

    public static int getWeekByDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
        if (weekDay == Calendar.MONDAY) {
            return 1;
        } else if (weekDay == Calendar.TUESDAY) {
            return 2;
        } else if (weekDay == Calendar.WEDNESDAY) {
            return 3;
        } else if (weekDay == Calendar.THURSDAY) {
            return 4;
        } else if (weekDay == Calendar.FRIDAY) {
            return 5;
        } else if (weekDay == Calendar.SATURDAY) {
            return 6;
        } else if (weekDay == Calendar.SUNDAY) {
            return 7;
        }
        return weekDay;
    }
    
    public static String getWeekChineseByDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
        if (weekDay == Calendar.MONDAY) {
            return "星期一";
        } else if (weekDay == Calendar.TUESDAY) {
            return "星期二";
        } else if (weekDay == Calendar.WEDNESDAY) {
            return "星期三";
        } else if (weekDay == Calendar.THURSDAY) {
            return "星期四";
        } else if (weekDay == Calendar.FRIDAY) {
            return "星期五";
        } else if (weekDay == Calendar.SATURDAY) {
            return "星期六";
        } else if (weekDay == Calendar.SUNDAY) {
            return "星期日";
        }
        return "未知";
    }

    public static int getWeekByDate(Date date, String timeZoneID) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.setTimeZone(TimeZone.getTimeZone(timeZoneID));
        int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
        if (weekDay == Calendar.MONDAY) {
            return 1;
        } else if (weekDay == Calendar.TUESDAY) {
            return 2;
        } else if (weekDay == Calendar.WEDNESDAY) {
            return 3;
        } else if (weekDay == Calendar.THURSDAY) {
            return 4;
        } else if (weekDay == Calendar.FRIDAY) {
            return 5;
        } else if (weekDay == Calendar.SATURDAY) {
            return 6;
        } else if (weekDay == Calendar.SUNDAY) {
            return 7;
        }
        return weekDay;
    }

    public static long getTimeMill(Date date, String timeZoneID) {
        return date.getTime() - TimeUtil.getDate(date, timeZoneID).getTime();
    }

    public static Date getDate(Date date, String timeZoneID) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if (!StringUtil.isEmpty(timeZoneID)) {
            dateFormat.setTimeZone(TimeZone.getTimeZone(timeZoneID));
        }
        String dateStr = dateFormat.format(date);
        try {
            return dateFormat.parse(dateStr);
        } catch (ParseException e) {
            log.error("parse error", e);
            return null;
        }
    }

    public static long getTimeMill(Date date) {
        return date.getTime() - TimeUtil.getDate(date, null).getTime();
    }

    public static int getMonthMaxDay(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        int day = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        return day;
    }

    public static int getMonthMaxDay(Date date, String timeZoneID) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.setTimeZone(TimeZone.getTimeZone(timeZoneID));
        int day = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        return day;
    }

    public static long getDateMill(Date date) {
        return date.getTime() - getTimeMill(date);
    }

    public static long getDateMill(Date date, String timeZoneID) {
        return TimeUtil.getDate(date, timeZoneID).getTime();
    }

    /**
     * 取得日期时间里的日期
     * 
     * @param dateTime
     * @return
     */
    public static Date getDate(Date dateTime) {
        long nowMill = dateTime.getTime();
        long dateMill = nowMill - TimeUtil.getTimeMill(dateTime);
        return new Date(dateMill);
    }

    /**
     * Calculate date
     * 
     * @param date
     * @param interval
     * @param calType "Y":year "M":month "D":day
     * @return
     */
    public static Date calDate(Date date, int interval, String calType) {
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(date);

        if ("Y".equals(calType)) {
            rightNow.add(Calendar.YEAR, interval);
            return rightNow.getTime();
        }

        if ("M".equals(calType)) {
            rightNow.add(Calendar.MONTH, interval);
            return rightNow.getTime();
        }

        if ("D".equals(calType)) {
            rightNow.add(Calendar.DAY_OF_YEAR, interval);
            return rightNow.getTime();
        }
        return date;
    }

    /**
     * @return yyyy-MM-dd 00:00:00
     */
    public static Date getToday() {
        String date = getCurrentDateDefaultString();
        date = date.substring(0, 10) + " 00:00:00";
        return parseDateToDay(date);
    }

    /**
     * 将时间转换为指定时区的时间
     * 
     * @param date
     * @param timeZoneId 值为 CMT-8,CMT+3等
     * @return
     */
    public static Date transferByTimeZone(Date date, String timeZoneId) {
        if (date == null || StringUtil.isBlank(timeZoneId)) {
            return null;
        }
        try {
            int diffTime = TimeZone.getDefault().getRawOffset() - TimeZone.getTimeZone(timeZoneId).getRawOffset();
            long nowTime = date.getTime();
            long newNowTime = nowTime - diffTime;
            return new Date(newNowTime);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 取得时间里面的月份
     * 
     * @param date
     * @return
     */
    public static int getDateMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 取得时间里面的月份
     * 
     * @param date
     * @return
     */
    public static int getDateMonth(Date date, String timeZoneID) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.setTimeZone(TimeZone.getTimeZone(timeZoneID));
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 取得日期的天
     * 
     * @param date
     * @return
     */
    public static int getDateDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DATE);
    }

    /**
     * 取得日期的天
     * 
     * @param date
     * @return
     */
    public static int getDateDay(Date date, String timeZoneID) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.setTimeZone(TimeZone.getTimeZone(timeZoneID));
        return calendar.get(Calendar.DATE);
    }

    /**
     * 获得某年某月的第几个星期几的 日期
     * 
     * @param year
     * @param month
     * @param weekInMonth
     * @param dayInWeek
     * @return
     */
    public static Date getDate(int year, int month, int weekInMonth, int dayInWeek) {
        Calendar date = Calendar.getInstance();
        date.clear();
        date.set(Calendar.YEAR, year);
        date.set(Calendar.MONTH, month - 1);
        date.set(Calendar.DAY_OF_WEEK_IN_MONTH, weekInMonth);
        date.set(Calendar.DAY_OF_WEEK, dayInWeek + 1);
        return date.getTime();
    }

    /**
     * 获得某年某月的第几个星期几的 日期
     * 
     * @param year
     * @param month
     * @param weekInMonth
     * @param dayInWeek
     * @return
     */
    public static Date getDate(int year, int month, int weekInMonth, int dayInWeek, String timeZoneID) {
        Calendar date = Calendar.getInstance();
        date.clear();
        date.setTimeZone(TimeZone.getTimeZone(timeZoneID));
        date.set(Calendar.YEAR, year);
        date.set(Calendar.MONTH, month - 1);
        date.set(Calendar.DAY_OF_WEEK_IN_MONTH, weekInMonth);
        date.set(Calendar.DAY_OF_WEEK, dayInWeek + 1);
        return date.getTime();
    }

    /**
     * 取得日期里面的 年份
     * 
     * @param date
     * @return
     */
    public static int getDateYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 取得日期里面的 年份
     * 
     * @param date
     * @return
     */
    public static int getDateYear(Date date, String timeZoneID) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 取得某年某月的最后一个星期几的 日期
     * 
     * @param year
     * @param month
     * @param dayInWeek
     * @return
     */
    public static Date getLastWeekDayDate(int year, int month, Integer dayInWeek) {
        Date fourthDate = getDate(year, month, 4, dayInWeek);
        int fourthDateDay = TimeUtil.getDateDay(fourthDate);
        int maxDateDay = TimeUtil.getMonthMaxDay(fourthDate);
        if ((fourthDateDay + 7) <= maxDateDay) {
            return new Date(fourthDate.getTime() + 7 * 24 * 60 * 60 * 1000l);
        } else {
            return fourthDate;
        }
    }

    /**
     * 取得某年某月的最后一个星期几的 日期
     * 
     * @param year
     * @param month
     * @param dayInWeek
     * @return
     */
    public static Date getLastWeekDayDate(int year, int month, Integer dayInWeek, String timeZoneID) {
        Date fourthDate = getDate(year, month, 4, dayInWeek);
        int fourthDateDay = TimeUtil.getDateDay(fourthDate, timeZoneID);
        int maxDateDay = TimeUtil.getMonthMaxDay(fourthDate, timeZoneID);
        if ((fourthDateDay + 7) <= maxDateDay) {
            return new Date(fourthDate.getTime() + 7 * 24 * 60 * 60 * 1000l);
        } else {
            return fourthDate;
        }
    }

    public static Date parseDate(String pattern, String dateTimeStr, String timeZoneID) throws ParseException {

        DateFormat dateFormat = new SimpleDateFormat(pattern);
        dateFormat.setTimeZone(TimeZone.getTimeZone(timeZoneID));
        return dateFormat.parse(dateTimeStr);
    }

    public static String convertWeekNumWithLanguage(int weekNum, String language) {
        switch (weekNum) {
            case 1:
                if ("EN".equals(language)) {
                    return "Monday";
                } else if ("CN".equals(language)) {
                    return "周一";
                }
                break;
            case 2:
                if ("EN".equals(language)) {
                    return "Tuesday";
                } else if ("CN".equals(language)) {
                    return "周二";
                }
                break;
            case 3:
                if ("EN".equals(language)) {
                    return "Wednesday";
                } else if ("CN".equals(language)) {
                    return "周三";
                }
                break;
            case 4:
                if ("EN".equals(language)) {
                    return "Thursday";
                } else if ("CN".equals(language)) {
                    return "周四";
                }
                break;
            case 5:
                if ("EN".equals(language)) {
                    return "Friday";
                } else if ("CN".equals(language)) {
                    return "周五";
                }
                break;
            case 6:
                if ("EN".equals(language)) {
                    return "Saturday";
                } else if ("CN".equals(language)) {
                    return "周六";
                }
                break;
            case 7:
                if ("EN".equals(language)) {
                    return "Sunday";
                } else if ("CN".equals(language)) {
                    return "周日";
                }
                break;
        }
        return "";
    }

    public static List<String> getTimeDurations() {
        List<String> durationArray = new ArrayList<String>();
        float duration = 0.5f;
        for (; duration <= 24; duration += 0.5) {
            if (duration - (int) (duration) > 0) {
                durationArray.add(duration + "");
            } else {
                durationArray.add((int) (duration) + "");
            }

        }
        return durationArray;
    }

    // 获得当天0点时间
    public static Date getTimesmorning() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    // 获得当天24点时间
    public static Date getTimesnight() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 24);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    // 获得本周一0点时间
    public static Date getTimesWeekmorning() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return cal.getTime();
    }

    // 获得本周日24点时间
    public static Date getTimesWeeknight() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getTimesWeekmorning());
        cal.add(Calendar.DAY_OF_WEEK, 7);
        return cal.getTime();
    }

    // 获得本月第一天0点时间
    public static Date getTimesMonthmorning() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }

    // 获得本月最后一天24点时间
    public static Date getTimesMonthnight() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY, 24);
        return cal.getTime();
    }
}
