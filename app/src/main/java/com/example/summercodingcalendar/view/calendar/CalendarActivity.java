package com.example.summercodingcalendar.view.calendar;

import android.content.Intent;
import androidx.databinding.DataBindingUtil;
import android.net.Uri;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.summercodingcalendar.R;
import com.example.summercodingcalendar.databinding.ActivityCalendarBinding;
import com.example.summercodingcalendar.view.calendar.adapter.TabPagerAdapter;
import com.example.summercodingcalendar.view.calendar.daily.DailyFragment;
import com.example.summercodingcalendar.view.calendar.monthly.MonthlyFragment;
import com.example.summercodingcalendar.view.calendar.weekly.WeeklyFragment;
import com.example.summercodingcalendar.view.register.RegisterActivity;

public class CalendarActivity extends AppCompatActivity implements CalendarContract.View, MonthlyFragment.OnFragmentInteractionListener, WeeklyFragment.OnFragmentInteractionListener, DailyFragment.OnFragmentInteractionListener {
    private ActivityCalendarBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_calendar);
        setView();


    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void setView() {

        setSupportActionBar(mBinding.toolbar);
        mBinding.setFragmentManger(getSupportFragmentManager());
        TabPagerAdapter tabPagerAdapter = new TabPagerAdapter(mBinding.getFragmentManger());
        tabPagerAdapter.setTabCount(3);

        mBinding.setTabPagerAdapter(tabPagerAdapter);
        mBinding.viewPager.setAdapter(mBinding.getTabPagerAdapter());
        mBinding.tabLayout.setupWithViewPager(mBinding.viewPager);
        mBinding.setPresenter(new CalendarPresenter(this));
    }

    @Override
    public void navigateToRegisterActivity() {
        startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
    }
}
