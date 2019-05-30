package com.example.summercodingcalendar.util;

import com.example.summercodingcalendar.data.Schedule;
import com.prolificinteractive.materialcalendarview.CalendarDay;

import org.threeten.bp.LocalDate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
    public Date getNextWeek() {
        mCalendar.add(Calendar.WEEK_OF_MONTH, +1);
        return mCalendar.getTime();
    }

    @Override
    public Date getPreviousWeek() {
        mCalendar.add(Calendar.WEEK_OF_MONTH, -1);
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

    @Override
    public List<Date> getStartDateAndEndDate() {
        List<Date> dates = new ArrayList<>();
        int currentDayOfWeek = mCalendar.get(Calendar.DAY_OF_WEEK);
        mCalendar.set(Calendar.DAY_OF_WEEK, 1);
        Date startDate = mCalendar.getTime();
        mCalendar.set(Calendar.DAY_OF_WEEK, 7);
        Date endDate = mCalendar.getTime();
        mCalendar.set(Calendar.DAY_OF_WEEK, currentDayOfWeek);
        dates.add(startDate);
        dates.add(endDate);
        return dates;
    }

    @Override
    public List<CalendarDay> schedulesToCalendarDays(List<Schedule> schedules) {
        List<CalendarDay> calendarDays = new ArrayList<>();
        for (Schedule schedule:schedules) {
            calendarDays.add(dateToCalendarDay(schedule.getDate()));
        }

        return calendarDays;
    }


}
