package com.example.summercodingcalendar.view.register;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

import com.example.summercodingcalendar.data.Schedule;
import com.example.summercodingcalendar.util.Converter;

import java.util.Date;

import io.realm.Realm;

public class RegisterPresenter implements RegisterContract.Presenter {
    private RegisterContract.View view;
    public TextWatcher textWatcher;
    private Schedule schedule;
//    private RealmHelper realmHelper = RealmHelper.getInstance();
    public RegisterPresenter(RegisterContract.View view) {
        this.view = view;

    }

    @Override
    public void onRegisterButtonClicked(Date date, String title, String body) {
        if(view.validateData()){
//            schedule.setDate(date);
//            schedule.setTitle(title);
//            schedule.setBody(body);
//            realmHelper.addScheduel(date, title, body);
            view.showTempToast(Converter.dateToString(date)+"\n"+title+"\n"+body);
        }

    }

    @Override
    public void onEditTextDateClicked() {
        view.showDatePickerDialog();
    }

    @Override
    public void afterTextChanged() {
        view.validateData();
    }


}
