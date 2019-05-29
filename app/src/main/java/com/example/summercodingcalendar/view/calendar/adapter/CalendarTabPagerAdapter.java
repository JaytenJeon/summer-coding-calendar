package com.example.summercodingcalendar.view.calendar.adapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.summercodingcalendar.view.calendar.daily.DailyFragment;
import com.example.summercodingcalendar.view.calendar.monthly.MonthlyFragment;
import com.example.summercodingcalendar.view.calendar.weekly.WeeklyFragment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CalendarTabPagerAdapter extends FragmentPagerAdapter
        implements TabPagerAdapterContract.View, TabPagerAdapterContract.Model{
    private List<Fragment> mFragments = new ArrayList<>();

    public CalendarTabPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {

        return mFragments.get(i);

    }

    @Override
    public int getCount() {
        return this.mFragments.size();
    }

    @Override
    public void addFragment(Fragment fragment) {
        mFragments.add(fragment);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mFragments.get(position).getArguments().getString("title", "No name");
    }

    @Override
    public void notifyAdapter() {
        notifyDataSetChanged();
    }
}
