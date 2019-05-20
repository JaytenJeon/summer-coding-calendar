package com.example.summercodingcalendar.view.calendar.weekly;

public class WeeklyPresenter implements WeeklyContract.Presenter {
    private WeeklyContract.View view;

    public WeeklyPresenter(WeeklyContract.View view) {
        this.view = view;
    }
}
