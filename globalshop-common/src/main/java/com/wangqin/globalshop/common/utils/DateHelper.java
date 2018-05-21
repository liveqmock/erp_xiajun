package com.wangqin.globalshop.common.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Patrick on 18/12/2017.
 */
public class DateHelper {

    public static Calendar createCalendar(){
        return Calendar.getInstance();
    }
    public static Calendar addDay(Calendar calendar,int days){
        Calendar clone =(Calendar)calendar.clone();
        clone.add(Calendar.DATE,days);
        return clone;
    }
    public static Calendar trans(Date date){
        Calendar calendar = createCalendar();
        calendar.setTime(date);
        return calendar;
    }
}
