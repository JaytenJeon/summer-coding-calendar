package com.example.summercodingcalendar.util;

import org.threeten.bp.Instant;
import org.threeten.bp.LocalDate;
import org.threeten.bp.ZoneId;

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

    @InverseMethod("weekNumberTitleToDate")
    public static String dateToWeekNumberTitle(Date oldValue){
        SimpleDateFormat format = new SimpleDateFormat("yyyy년 MM월 W주차");
        try {
            return format.format(oldValue);

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static Date weekNumberTitleToDate(String oldValue){
        SimpleDateFormat format = new SimpleDateFormat("yyyy년 MM월 W주차");
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

    public static LocalDate dateToLocalDate(Date oldValue){
        return Instant.ofEpochMilli(oldValue.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static Date localDateToDate(LocalDate oldValue){
        return java.sql.Date.valueOf(String.valueOf(oldValue));
    }
}
