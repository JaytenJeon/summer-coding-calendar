package com.example.summercodingcalendar.view.splash;

public interface SplashContract {
    interface View{
        void navigateToCalendarActivity();
    }

    interface Presenter{
        void onStart();
    }
}
