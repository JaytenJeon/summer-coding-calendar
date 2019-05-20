package com.example.summercodingcalendar.view.splash;

public class SplashPresenter implements SplashContract.Presenter {

    private SplashContract.View view;

    public SplashPresenter(SplashContract.View view) {
        this.view = view;
    }

    @Override
    public void onStart() {
        view.navigateToCalendarActivity();
    }
}
