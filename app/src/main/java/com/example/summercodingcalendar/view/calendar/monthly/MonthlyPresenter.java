package com.example.summercodingcalendar.view.calendar.monthly;

import com.example.summercodingcalendar.util.Converter;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import androidx.annotation.NonNull;

public class MonthlyPresenter implements MonthlyContract.Presenter, OnDateSelectedListener {
    private MonthlyContract.View view;

    public MonthlyPresenter(MonthlyContract.View view) {
        this.view = view;
    }

    @Override
    public void onDateSelected(@NonNull MaterialCalendarView materialCalendarView, @NonNull CalendarDay calendarDay, boolean b) {
        view.changeDay(Converter.localDateToDate(calendarDay.getDate()));
    }
}
