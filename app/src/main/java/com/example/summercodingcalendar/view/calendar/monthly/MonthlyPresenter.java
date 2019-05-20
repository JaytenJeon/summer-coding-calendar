package com.example.summercodingcalendar.view.calendar.monthly;

public class MonthlyPresenter implements MonthlyContract.Presenter {
    private MonthlyContract.View view;

    public MonthlyPresenter(MonthlyContract.View view) {
        this.view = view;
    }
}
