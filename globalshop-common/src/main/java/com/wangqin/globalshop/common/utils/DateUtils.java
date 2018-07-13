package com.wangqin.globalshop.common.utils;

import java.text.ParseException;
import java.util.Date;

public class DateUtils {

    public static final String formateStr19 = "yyyy-MM-dd HH:mm:ss";

    public static final String formateStr21 = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";


    /**
     * yyyy-MM-dd
     * @param dateStr
     * @return
     */
    public static Date dateBeginning(String dateStr){
        Date date = null;
        try {
            date  = org.apache.commons.lang3.time.DateUtils.parseDate(dateStr + " 00:00:00", formateStr19);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static Date dateEnd(String dateStr){
        Date date = null;
        try {
            date  = org.apache.commons.lang3.time.DateUtils.parseDate(dateStr + " 23:59:59", formateStr19);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }




}
