package com.example.summercodingcalendar.view.calendar;

import com.example.summercodingcalendar.view.calendar.adapter.TabPagerAdapterContract;
import com.example.summercodingcalendar.view.calendar.daily.DailyFragment;
import com.example.summercodingcalendar.view.calendar.monthly.MonthlyFragment;
import com.example.summercodingcalendar.view.calendar.weekly.WeeklyFragment;

public class CalendarPresenter implements CalendarContract.Presenter {
    private CalendarContract.View view;
    private TabPagerAdapterContract.View adapterView;
    private TabPagerAdapterContract.Model adapterModel;

    public CalendarPresenter(CalendarContract.View view) {
        this.view = view;
    }

    @Override
    public void setTabPagerAdapterView(TabPagerAdapterContract.View adapterView) {
        this.adapterView = adapterView;
    }

    @Override
    public void setTabPagerAdapterModel(TabPagerAdapterContract.Model adapterModel) {
        this.adapterModel = adapterModel;
    }

    @Override
    public void setFragments() {
        adapterModel.addFragment(MonthlyFragment.newInstance("Monthly"));
        adapterModel.addFragment(WeeklyFragment.newInstance("Weekly"));
        adapterModel.addFragment(DailyFragment.newInstance("Daily"));
        adapterView.notifyAdapter();
    }

    @Override
    public void onFabClicked() {
        view.navigateToRegisterActivity();
    }
}
