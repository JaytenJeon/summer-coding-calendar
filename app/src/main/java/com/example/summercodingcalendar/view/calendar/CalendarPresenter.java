package com.example.summercodingcalendar.view.calendar;

public class CalendarPresenter implements CalendarContract.Presenter {
    private CalendarContract.View view;

    public CalendarPresenter(CalendarContract.View view) {
        this.view = view;
    }
}
