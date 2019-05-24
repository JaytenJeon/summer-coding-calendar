package com.example.summercodingcalendar.view.calendar.monthly;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

import androidx.annotation.NonNull;

public class MonthlyPresenter implements MonthlyContract.Presenter, OnDateSelectedListener {
    private MonthlyContract.View view;

    public MonthlyPresenter(MonthlyContract.View view) {
        this.view = view;
    }

    @Override
    public void onDateSelected(@NonNull MaterialCalendarView materialCalendarView, @NonNull CalendarDay calendarDay, boolean b) {
        view.changeDay(java.sql.Date.valueOf(String.valueOf(calendarDay.getDate())));
    }
}
