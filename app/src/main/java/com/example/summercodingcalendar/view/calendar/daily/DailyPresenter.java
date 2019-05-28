package com.example.summercodingcalendar.view.calendar.daily;

import com.example.summercodingcalendar.data.Schedule;
import com.example.summercodingcalendar.util.RealmHelper;
import com.example.summercodingcalendar.view.calendar.adapter.DailyScheduleListAdapter;
import com.example.summercodingcalendar.view.calendar.adapter.ScheduleAdapterContract;

import java.util.Date;
import java.util.List;

public class DailyPresenter implements DailyContract.Presenter {
    private DailyContract.View view;
    private RealmHelper mRealmHelper = RealmHelper.getInstance();
    private ScheduleAdapterContract.View adapterView;
    private ScheduleAdapterContract.Model adapterModel;
    private Schedule removedSchedule;
    private int removedSchedulePosition;
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
        List<Schedule> scheduleList = mRealmHelper.getSchedulesAt(date);
        adapterModel.addItems(scheduleList);
        adapterView.notifyAdapter();
    }

    @Override
    public void removeItem(int position) {
        removedSchedule = adapterModel.removeItem(position);
        removedSchedulePosition = position;
        adapterView.notifyRemoveItem(position);
        mRealmHelper.removeSchedule(removedSchedule);
        view.showUndoSnackbar();
    }

    @Override
    public void undoRemoveItem() {
        adapterModel.addItem(removedSchedule, removedSchedulePosition);
        adapterView.notifyAddItem(removedSchedulePosition);
        mRealmHelper.undoSchedule(removedSchedule);
    }
}
