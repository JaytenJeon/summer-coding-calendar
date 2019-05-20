package com.example.summercodingcalendar.view.splash;

import android.content.Intent;
import androidx.databinding.DataBindingUtil;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.summercodingcalendar.R;
import com.example.summercodingcalendar.databinding.ActivitySplashBinding;
import com.example.summercodingcalendar.view.calendar.CalendarActivity;

public class SplashActivity extends AppCompatActivity implements SplashContract.View {
    private SplashPresenter mSplashPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySplashBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_splash);
        mSplashPresenter = new SplashPresenter(this);
        mSplashPresenter.onStart();
    }

    @Override
    public void navigateToCalendarActivity() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), CalendarActivity.class));
                finish();
            }
        }, 1300);
    }
}
