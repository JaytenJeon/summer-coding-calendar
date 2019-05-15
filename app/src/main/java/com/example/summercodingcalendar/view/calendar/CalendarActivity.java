package com.example.summercodingcalendar.view.calendar;

import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.summercodingcalendar.R;
import com.example.summercodingcalendar.databinding.ActivityCalendarBinding;
import com.example.summercodingcalendar.view.calendar.adapter.TabPagerAdapter;
import com.example.summercodingcalendar.view.calendar.daily.DailyFragment;
import com.example.summercodingcalendar.view.calendar.monthly.MonthlyFragment;
import com.example.summercodingcalendar.view.calendar.weekly.WeeklyFragment;

public class CalendarActivity extends AppCompatActivity implements CalendarContract.View, MonthlyFragment.OnFragmentInteractionListener, WeeklyFragment.OnFragmentInteractionListener, DailyFragment.OnFragmentInteractionListener {
    private CalendarPresenter mCalendarPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivityCalendarBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_calendar);
        mCalendarPresenter = new CalendarPresenter(this);
        binding.setFragmentManger(getSupportFragmentManager());
        TabPagerAdapter tabPagerAdapter = new TabPagerAdapter(binding.getFragmentManger());
        tabPagerAdapter.setTabCount(3);

        binding.setTabPagerAdapter(tabPagerAdapter);

        binding.bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                 switch (menuItem.getItemId()) {
                    case R.id.menu_montly:
                        binding.viewPager.setCurrentItem(0);

                        break;
                    case R.id.menu_weekly:
                        binding.viewPager.setCurrentItem(1);
                        break;
                    case R.id.menu_daily:
                        binding.viewPager.setCurrentItem(2);
                        break;
                    }
                    return true;
            }
        });
        binding.viewPager.setAdapter(binding.getTabPagerAdapter());


    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
