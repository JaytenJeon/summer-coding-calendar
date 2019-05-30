package com.example.summercodingcalendar.view.calendar.monthly;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.Date;
import java.util.List;

public interface MonthlyContract {
    interface View{
        void setView();
        void changeDay(Date date);
        void setMaterialCalendar(List<CalendarDay> events);
    }

    interface Presenter{
        void onCreate();
    }
}
