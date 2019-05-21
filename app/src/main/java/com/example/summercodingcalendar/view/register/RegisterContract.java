package com.example.summercodingcalendar.view.register;

import java.util.Date;

public interface RegisterContract {
    interface View{
        boolean validateData();
        void showDatePickerDialog();
        void showTempToast(String text);
    }

    interface Presenter{
        void onRegisterButtonClicked(Date date, String title, String body);
        void onEditTextDateClicked();
        void afterTextChanged();
    }

}
