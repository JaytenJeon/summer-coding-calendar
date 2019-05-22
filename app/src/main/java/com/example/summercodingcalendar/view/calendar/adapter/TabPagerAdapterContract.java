package com.example.summercodingcalendar.view.calendar.adapter;

import androidx.fragment.app.Fragment;

public interface TabPagerAdapterContract {
    interface View{
        void notifyAdapter();
    }

    interface Model{
        void addFragment(Fragment fragment);
        int getCount();
    }

}
