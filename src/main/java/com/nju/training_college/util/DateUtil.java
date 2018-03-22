package com.nju.training_college.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static final int CANCEL_ORDER = 5;

    public static String format(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    public static Date format(String str){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Date getCurrentUtilDate(){
        return new Date(System.currentTimeMillis());
    }

    public static java.sql.Date getCurrentSQLDate(){
        return new java.sql.Date(System.currentTimeMillis());
    }

    public static Timestamp getCurrent(){
        return new Timestamp(System.currentTimeMillis());
    }

    public static int returnPercent(Date date){
        long time = date.getTime();

        long days = (time - System.currentTimeMillis())/(1000*60*60*24) + 1;

        if (days >= 5)
            return 5;

        return (int)days;
    }
}
