package com.example.summercodingcalendar.view.calendar.adapter;

import com.example.summercodingcalendar.data.Schedule;

import java.util.List;

public interface ScheduleAdapterContract {
    interface View{
        void notifyAdapter();
    }
    interface Model{
        void addItems(List<Schedule> schedules);
    }
}
