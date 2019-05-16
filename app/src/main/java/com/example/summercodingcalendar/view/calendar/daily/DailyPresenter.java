package com.example.summercodingcalendar.view.calendar.daily;

public class DailyPresenter implements DailyContract.Presenter {
    private DailyContract.View mView;

    public DailyPresenter(DailyContract.View view) {
        mView = view;
    }
}
