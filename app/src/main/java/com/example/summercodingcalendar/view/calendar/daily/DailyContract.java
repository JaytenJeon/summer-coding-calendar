package com.example.summercodingcalendar.view.calendar.daily;

import com.example.summercodingcalendar.data.Schedule;
import com.example.summercodingcalendar.view.calendar.adapter.ScheduleAdapterContract;

import java.util.Date;

public interface DailyContract {
    interface View{
        void onDateChanged(Date date);
        void showUndoSnackbar();
    }

    interface Presenter{
        void setDailyScheduleAdapterModel(ScheduleAdapterContract.Model model);
        void setDailyScheduleAdapterView(ScheduleAdapterContract.View view);
        void loadDailySchedule(Date date);
        void removeItem(int position);
        void undoRemoveItem();
    }

}
