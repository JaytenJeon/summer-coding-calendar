package com.example.summercodingcalendar.view.calendar.weekly;

public class WeeklyPresenter implements WeeklyContract.Presenter {
    private WeeklyContract.View mView;

    public WeeklyPresenter(WeeklyContract.View view) {
        mView = view;
    }
}
