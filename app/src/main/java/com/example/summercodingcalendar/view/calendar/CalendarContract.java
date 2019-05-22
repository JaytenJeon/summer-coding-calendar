package com.example.summercodingcalendar.view.calendar;

import com.example.summercodingcalendar.view.calendar.adapter.TabPagerAdapterContract;

import java.util.Date;

public interface CalendarContract {
    interface View{
        void setView();
        void navigateToRegisterActivity();
    }

    interface Presenter{
        void setTabPagerAdapterView(TabPagerAdapterContract.View adapterView);
        void setTabPagerAdapterModel(TabPagerAdapterContract.Model adapterModel);
        void setFragments(Date date);
        void onFabClicked();
    }

}
