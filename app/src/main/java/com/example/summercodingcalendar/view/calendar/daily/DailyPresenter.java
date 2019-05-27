package com.example.summercodingcalendar.view.calendar.daily;

import com.example.summercodingcalendar.data.Schedule;
import com.example.summercodingcalendar.util.RealmHelper;
import com.example.summercodingcalendar.view.calendar.adapter.DailyScheduleListAdapter;
import com.example.summercodingcalendar.view.calendar.adapter.ScheduleAdapterContract;

import java.util.Date;
import java.util.List;

public class DailyPresenter implements DailyContract.Presenter {
    private DailyContract.View view;
    private ScheduleAdapterContract.View adapterView;
    private ScheduleAdapterContract.Model adapterModel;
    public DailyPresenter(DailyContract.View view) {
        this.view = view;
    }

    @Override
    public void setDailyScheduleAdapterModel(ScheduleAdapterContract.Model model) {
        adapterModel = model;
    }

    @Override
    public void setDailyScheduleAdapterView(ScheduleAdapterContract.View view) {
        adapterView = view;
    }

    @Override
    public void loadDailySchedule(Date date) {
        List<Schedule> scheduleList = RealmHelper.getInstance().getSchedulesAt(date);
        adapterModel.addItems(scheduleList);
        adapterView.notifyAdapter();
    }
}
