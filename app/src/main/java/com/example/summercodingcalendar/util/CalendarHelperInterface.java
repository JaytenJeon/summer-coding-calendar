package com.example.summercodingcalendar.util;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.util.Date;

interface CalendarHelperInterface {
    Date getCurrentDate();
    Date getNextDate();
    Date getPreviousDate();
    void setDate(Date date);
    CalendarDay getCurrentCalendarDay();
    CalendarDay dateToCalendarDay(Date date);

}
