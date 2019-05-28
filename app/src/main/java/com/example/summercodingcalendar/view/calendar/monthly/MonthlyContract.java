package com.example.summercodingcalendar.view.calendar.monthly;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.Date;

public interface MonthlyContract {
    interface View{
        void setView();
        void changeDay(Date date);
    }

    interface Presenter{
    }
}
