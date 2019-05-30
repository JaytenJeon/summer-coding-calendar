package com.example.summercodingcalendar.util;

import com.example.summercodingcalendar.data.Schedule;
import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.util.Date;
import java.util.List;

interface CalendarHelperInterface {
    Date getCurrentDate();
    Date getNextDate();
    Date getPreviousDate();
    Date getNextWeek();
    Date getPreviousWeek();
    void setDate(Date date);
    CalendarDay getCurrentCalendarDay();
    CalendarDay dateToCalendarDay(Date date);
    List<Date> getStartDateAndEndDate();
    List<CalendarDay> schedulesToCalendarDays(List<Schedule> schedules);
}
