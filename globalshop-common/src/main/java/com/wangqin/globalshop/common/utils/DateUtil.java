package com.wangqin.globalshop.common.utils;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

public class DateUtil {
    
	public static final String SIMPLE_DATE_PARTEN = "yyyy-MM-dd";
	public static final String DATE_PARTEN = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_PARTEN_Chinese = "yyyy年MM月dd日HH:mm";
	public static final String DATE_PARTEN_MILLI = "yyyy-MM-dd HH:mm:ss.SSS";
	public static final String DATE_PARTEN_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
	public static final String DATE_PARTEN_YYMMDD = "yyMMdd";
	public static final String DATE_PARTEN_YYMMDDHHMMSS = "yyMMddHHmmss";

	public static boolean isToday(Date date) {
		if (date != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			Calendar today = Calendar.getInstance();
			if (calendar.get(Calendar.YEAR) == today.get(Calendar.YEAR)
					&& calendar.get(Calendar.MONTH) == today
							.get(Calendar.MONTH)
					&& calendar.get(Calendar.DAY_OF_MONTH) == today
							.get(Calendar.DAY_OF_MONTH)) {
				return true;
			}
		}
		return false;
	}

	public static boolean isAfterToday(Date date) {
		if (date != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);

			Calendar today = Calendar.getInstance();
			today.set(Calendar.HOUR_OF_DAY, 23);
			today.set(Calendar.MINUTE, 59);
			today.set(Calendar.SECOND, 59);

			return calendar.after(today);
		}
		return false;
	}

	public static Date parseDate(String dateStr) {
		if (dateStr != null) {
			try{
				if (dateStr.length() <= 10) {
					return getSimpleFormat().parse(dateStr);
				} else {
					return getFormat().parse(dateStr);
				}
			}catch (Exception e){
				return null;
			}

		}
		return null;
	}

	public static String formatDate(Long date) {
		if (date == null) {
			return null;
		}
		return formatDate(new Date(date));
	}

	public static String formatDate(Date date) {
		if (date == null) {
			return null;
		}
		try {
			return getFormat().format(date);
		} catch (Exception e) {
			return null;
		}
	}
	

	public static String formatChineseDate(Date date) {
		if (date == null) {
			return null;
		}
		try {
			return getChineseFormat().format(date);
		} catch (Exception e) {
			return null;
		}
	}

	public static String formatSimpleDate(Date date) {
		if (date == null) {
			return null;
		}
		try {
			return getSimpleFormat().format(date);
		} catch (Exception e) {
			return null;
		}
	}

	public static String formatMillisecondDate(Date date) {
		if (date == null) {
			return null;
		}

		try {
			return getMilliFormat().format(date);
		} catch (Exception e) {
			return null;
		}

	}

	private static ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<SimpleDateFormat>() {
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat(DATE_PARTEN);
		}
	};

	private static ThreadLocal<SimpleDateFormat> threadLocal_simple = new ThreadLocal<SimpleDateFormat>() {
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat(SIMPLE_DATE_PARTEN);
		}
	};
	private static ThreadLocal<SimpleDateFormat> threadLocal_milli = new ThreadLocal<SimpleDateFormat>() {
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat(DATE_PARTEN_MILLI);
		}
	};
	private static ThreadLocal<SimpleDateFormat> threadLocal_chinese = new ThreadLocal<SimpleDateFormat>() {
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat(DATE_PARTEN_Chinese);
		}
	};

	private static DateFormat getFormat() {
		return threadLocal.get();
	}

	private static DateFormat getSimpleFormat() {
		return threadLocal_simple.get();
	}

	private static DateFormat getMilliFormat() {
		return threadLocal_milli.get();
	}

	private static DateFormat getChineseFormat() {
		return threadLocal_chinese.get();
	}

	public static Date getDate(Long millisecond) {
		return millisecond != null && millisecond > 0 ? new Date(millisecond)
				: null;
	}

	public static Date getDay(Integer dayNumOffet) {
		if (dayNumOffet == null) {
			return null;
		}
		return getDay(Calendar.getInstance().getTime(), dayNumOffet);
	}

	public static Date getDay(Date theDate, int dayNumOffet) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(theDate);

		calendar.add(Calendar.DAY_OF_MONTH, dayNumOffet);
		Date date = calendar.getTime();

		return date;
	}

	public static Date convertStr2Date(String str, String format) throws ParseException {
		if (StringUtils.isEmpty(str)) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.parse(str);
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public static final String ymdFormat(Date date) {
		if (date == null) {
			return "";
		}

		DateFormat ymdFormat = new SimpleDateFormat("yyyy-MM-dd");
		return ymdFormat.format(date);
	}

	public static final String hmsFormat(Date date) {
		if (date == null) {
			return "";
		}

		DateFormat hmsFormat = new SimpleDateFormat("HH:mm");
		return hmsFormat.format(date);
	}

	public static String formatDate(Date showTime, String dateformat) {
	    if (showTime == null) {
            return "";
        }
		SimpleDateFormat sdf = new SimpleDateFormat(dateformat);
		return sdf.format(showTime);
	}

	public static int getField(Date date, int field) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(field);
	}

	/**
	 * 指定日期00:00:00
	 * @param date	指定日期
	 * @param dayAmount	日期偏移量
     * @return
     */
	public static Date getDateLeft(Date date, int dayAmount){
		if(date == null){
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, dayAmount);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 指定日期23:59:59
	 * @param date	指定日期
	 * @param dayAmount	日期偏移量
	 * @return
	 */
	public static Date getDateRight(Date date, int dayAmount){
		if(date == null){
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, dayAmount);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();
	}
	
	/**
	 * 
	 * @param date  基准日期
	 * @param type	计算类型，比如根据小时来计算，就是 Calendar.HOUR_OF_DAY
	 * @param hourAmount  计算量
	 * @return
	 */
	public static Date getDateByCalculate(Date date, int type, int hourAmount) {
		java.util.Calendar Cal=java.util.Calendar.getInstance();    
		Cal.setTime(date);
		Cal.add(type, hourAmount);
		return Cal.getTime();
	}
	
	/**
     * 判断time是否在from，to之内
     * @param time 指定日期
     * @param from 开始日期
     * @param to   结束日期
     * @return
     */
    public static boolean belongCalendar(Date time, Date from, Date to) {
        Calendar date = Calendar.getInstance();
        date.setTime(time);

        Calendar after = Calendar.getInstance();
        after.setTime(from);

        Calendar before = Calendar.getInstance();
        before.setTime(to);

        return date.after(after) && date.before(before);
    }
}
