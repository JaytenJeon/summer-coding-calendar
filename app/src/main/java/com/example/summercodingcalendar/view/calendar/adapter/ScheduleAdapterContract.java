package com.example.summercodingcalendar.view.calendar.adapter;

import com.example.summercodingcalendar.data.Schedule;

import java.util.List;

public interface ScheduleAdapterContract {
    interface View{
        void notifyAdapter();
        void notifyRemoveItem(int position);
        void notifyAddItem(int position);

    }
    interface Model{
        void addItems(List<Schedule> schedules);
        Schedule removeItem(int position);
        void addItem(Schedule schedule, int position);
    }
}
