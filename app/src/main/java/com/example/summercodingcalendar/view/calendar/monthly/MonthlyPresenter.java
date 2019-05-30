package com.example.summercodingcalendar.view.calendar.monthly;

import com.example.summercodingcalendar.data.Schedule;
import com.example.summercodingcalendar.util.CalendarHelper;
import com.example.summercodingcalendar.util.Converter;
import com.example.summercodingcalendar.util.RealmHelper;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.List;

import androidx.annotation.NonNull;

public class MonthlyPresenter implements MonthlyContract.Presenter, OnDateSelectedListener {
    private MonthlyContract.View view;
    private RealmHelper mRealmHelper = RealmHelper.getInstance();
    private CalendarHelper mCalendarHelper = CalendarHelper.getInstance();
    public MonthlyPresenter(MonthlyContract.View view) {
        this.view = view;
    }

    @Override
    public void onDateSelected(@NonNull MaterialCalendarView materialCalendarView, @NonNull CalendarDay calendarDay, boolean b) {
        view.changeDay(Converter.localDateToDate(calendarDay.getDate()));
    }

    @Override
    public void onCreate() {
        List<Schedule> schedules =  mRealmHelper.getAllSchedules();
        List<CalendarDay> calendarDays = mCalendarHelper.schedulesToCalendarDays(schedules);
        view.setMaterialCalendar(calendarDays);
    }
}
