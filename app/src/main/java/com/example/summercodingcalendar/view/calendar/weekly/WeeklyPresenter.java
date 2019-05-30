package com.example.summercodingcalendar.view.calendar.weekly;

import com.example.summercodingcalendar.data.Schedule;
import com.example.summercodingcalendar.util.CalendarHelper;
import com.example.summercodingcalendar.util.RealmHelper;
import com.example.summercodingcalendar.view.calendar.adapter.ScheduleAdapterContract;

import java.util.Date;
import java.util.List;

public class WeeklyPresenter implements WeeklyContract.Presenter {
    private WeeklyContract.View view;
    private CalendarHelper mCalendarHelper = CalendarHelper.getInstance();
    private ScheduleAdapterContract.View adapterView;
    private ScheduleAdapterContract.Model adapterModel;
    private RealmHelper mRealmHelper = RealmHelper.getInstance();
    private Schedule removedSchedule;
    private int removedSchedulePosition;

    public WeeklyPresenter(WeeklyContract.View view) {
        this.view = view;
    }

    @Override
    public void setWeeklyScheduleAdapterModel(ScheduleAdapterContract.Model model) {
        adapterModel = model;
    }

    @Override
    public void setWeeklyScheduleAdapterView(ScheduleAdapterContract.View view) {
        adapterView = view;
    }

    @Override
    public void loadWeeklySchedule() {
        List<Date> dates = mCalendarHelper.getStartDateAndEndDate();
        List<Schedule> scheduleList = mRealmHelper.getSchedulesForWeek(dates);
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

    @Override
    public void onNextButtonClicked(Date date) {
        view.onDateChanged(mCalendarHelper.getNextWeek());
        view.setView();
    }

    @Override
    public void onPreviousButtonClicked(Date date) {
        view.onDateChanged(mCalendarHelper.getPreviousWeek());
        view.setView();
    }
}
