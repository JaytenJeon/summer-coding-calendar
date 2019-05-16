package com.example.summercodingcalendar.view.calendar.monthly;

public class MonthlyPresenter implements MonthlyContract.Presenter {
    private MonthlyContract.View mView;

    public MonthlyPresenter(MonthlyContract.View view) {
        mView = view;
    }
}
