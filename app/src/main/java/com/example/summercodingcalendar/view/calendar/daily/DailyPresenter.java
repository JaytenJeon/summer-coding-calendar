package com.example.summercodingcalendar.view.calendar.daily;

public class DailyPresenter implements DailyContract.Presenter {
    private DailyContract.View view;

    public DailyPresenter(DailyContract.View view) {
        this.view = view;
    }
}
