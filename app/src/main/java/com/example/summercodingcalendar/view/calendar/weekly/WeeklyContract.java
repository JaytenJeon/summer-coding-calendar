package com.example.summercodingcalendar.view.calendar.weekly;

import com.example.summercodingcalendar.view.calendar.adapter.ScheduleAdapterContract;

import java.util.Date;

public interface WeeklyContract {
    interface View{
        void onDateChanged(Date date);
        void setView();
        void showUndoSnackbar();
    }

    interface Presenter{
        void setWeeklyScheduleAdapterModel(ScheduleAdapterContract.Model model);
        void setWeeklyScheduleAdapterView(ScheduleAdapterContract.View view);
        void loadWeeklySchedule();
        void removeItem(int position);
        void undoRemoveItem();
        void onNextButtonClicked(Date date);
        void onPreviousButtonClicked(Date date);
    }
}
