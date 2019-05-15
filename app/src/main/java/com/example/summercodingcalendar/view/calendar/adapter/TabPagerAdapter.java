package com.example.summercodingcalendar.view.calendar.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.summercodingcalendar.view.calendar.daily.DailyFragment;
import com.example.summercodingcalendar.view.calendar.monthly.MonthlyFragment;
import com.example.summercodingcalendar.view.calendar.weekly.WeeklyFragment;

public class TabPagerAdapter extends FragmentPagerAdapter {
    private int mTabCount;

    public TabPagerAdapter(FragmentManager fm) {
        super(fm);
        mTabCount = 0;
    }

    @Override
    public Fragment getItem(int i) {
        switch(i){
            case 0:
                return MonthlyFragment.newInstance();
            case 1:
                return WeeklyFragment.newInstance();
            case 2:
                return DailyFragment.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return this.mTabCount;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch(position){
            case 0:
                return "Monthly";
            case 1:
                return "Weekly";
            case 2:
                return "Daily";
            default:
                return null;
        }
    }

    public int getTabCount() {
        return mTabCount;
    }

    public void setTabCount(int tabCount) {
        mTabCount = tabCount;
    }
}
