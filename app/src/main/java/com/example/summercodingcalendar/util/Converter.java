package com.example.summercodingcalendar.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import androidx.databinding.InverseMethod;

public class Converter {
    @InverseMethod("stringToDate")
    public static String dateToString(Date oldValue) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy년 MM월 dd일");
        try {
            return format.format(oldValue);

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static Date stringToDate(String oldValue) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy년 MM월 dd일");
        try {
            return format.parse(oldValue);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date longToDate(long oloValue){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(oloValue);
        Date newValue = calendar.getTime();

        return newValue;
    }
}
