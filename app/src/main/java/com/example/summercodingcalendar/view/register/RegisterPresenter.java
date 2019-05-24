package com.example.summercodingcalendar.view.register;

import android.text.TextWatcher;
import com.example.summercodingcalendar.data.Schedule;
import com.example.summercodingcalendar.util.RealmHelper;


import java.util.Date;

public class RegisterPresenter implements RegisterContract.Presenter {  
    private RegisterContract.View view;
    public TextWatcher textWatcher;
    private Schedule schedule;
    private RealmHelper realmHelper = RealmHelper.getInstance();
    public RegisterPresenter(RegisterContract.View view) {
        this.view = view;

    }

    @Override
    public void onRegisterButtonClicked(Date date, String title, String body) {
        if(view.validateData()){
            schedule = new Schedule(date, title, body);
            realmHelper.addSchedule(schedule);
            view.showToast("일정 등록에 성공했습니다.");
            view.navigateToDaily(date);
            return;
        }
        view.showToast("일정 등록에 실패했습니다.");


    }

    @Override
    public void onEditTextDateClicked() {
        view.showDatePickerDialog();
    }

    @Override
    public void afterDateTextChanged() {
        view.validateDate();
    }

    @Override
    public void afterTitleTextChanged() {
        view.validateTitle();
    }


}
