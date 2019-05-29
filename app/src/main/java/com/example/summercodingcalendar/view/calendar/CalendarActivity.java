package com.example.summercodingcalendar.view.calendar;

import android.content.Intent;
import androidx.databinding.DataBindingUtil;
import android.net.Uri;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.summercodingcalendar.R;
import com.example.summercodingcalendar.databinding.ActivityCalendarBinding;
import com.example.summercodingcalendar.util.CalendarHelper;
import com.example.summercodingcalendar.util.Converter;
import com.example.summercodingcalendar.view.calendar.adapter.CalendarTabPagerAdapter;
import com.example.summercodingcalendar.view.calendar.daily.DailyFragment;
import com.example.summercodingcalendar.view.calendar.monthly.MonthlyFragment;
import com.example.summercodingcalendar.view.calendar.weekly.WeeklyFragment;
import com.example.summercodingcalendar.view.register.RegisterActivity;

import java.util.Calendar;
import java.util.Date;

public class CalendarActivity extends AppCompatActivity implements CalendarContract.View, MonthlyFragment.OnCalendarViewDateSelectedListener, WeeklyFragment.OnFragmentInteractionListener {
    private ActivityCalendarBinding mBinding;
    private CalendarHelper mCalendarHelper = CalendarHelper.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_calendar);
        setView();
    }

    @Override
    public void setView() {
        setSupportActionBar(mBinding.toolbar);
        mBinding.setSelectedDate(mCalendarHelper.getCurrentDate());
        mBinding.setFragmentManger(getSupportFragmentManager());
        mBinding.setCalendarTabPagerAdapter(new CalendarTabPagerAdapter(mBinding.getFragmentManger()));
        mBinding.viewPager.setAdapter(mBinding.getCalendarTabPagerAdapter());
        mBinding.tabLayout.setupWithViewPager(mBinding.viewPager);
        mBinding.setPresenter(new CalendarPresenter(this));
        mBinding.getPresenter().setTabPagerAdapterView(mBinding.getCalendarTabPagerAdapter());
        mBinding.getPresenter().setTabPagerAdapterModel(mBinding.getCalendarTabPagerAdapter());
        mBinding.getPresenter().setFragments(mBinding.getSelectedDate());
        mBinding.viewPager.setCurrentItem(getIntent().getIntExtra("selectedTab",0));
    }

    @Override
    public void navigateToRegisterActivity() {
        Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(intent);
    }

    @Override
    public void onDateSelected(Date date) {
        mBinding.setSelectedDate(date);
        DailyFragment dailyFragment = (DailyFragment) mBinding.getCalendarTabPagerAdapter().getItem(2);
        dailyFragment.onDateChanged(date);
        mBinding.viewPager.setCurrentItem(2);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
