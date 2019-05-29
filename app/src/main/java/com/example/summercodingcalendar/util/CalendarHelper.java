package com.example.summercodingcalendar.util;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import org.threeten.bp.LocalDate;

import java.util.Calendar;
import java.util.Date;

public class CalendarHelper implements CalendarHelperInterface {
    private static CalendarHelper INSTANCE = new CalendarHelper();
    private Calendar mCalendar;
    private CalendarHelper() {
        mCalendar = Calendar.getInstance();
    }

    public static CalendarHelper getInstance(){
        return INSTANCE;
    }


    @Override
    public Date getCurrentDate() {
        return mCalendar.getTime();
    }

    @Override
    public Date getNextDate() {
        mCalendar.add(Calendar.DATE, +1);
        return mCalendar.getTime();
    }

    @Override
    public Date getPreviousDate() {
        mCalendar.add(Calendar.DATE, -1);
        return mCalendar.getTime();
    }

    @Override
    public void setDate(Date date) {
        mCalendar.setTime(date);
    }

    @Override
    public CalendarDay getCurrentCalendarDay() {
        return dateToCalendarDay(getCurrentDate());
    }

    @Override
    public CalendarDay dateToCalendarDay(Date date) {
        return CalendarDay.from(Converter.dateToLocalDate(date));
    }
}
