package com.example.summercodingcalendar.view.register;

import java.util.Date;

public interface RegisterContract {
    interface View{
        boolean validateData();
        boolean validateDate();
        boolean validateTitle();
        void showDatePickerDialog();
        void showToast(String text);
        void navigateToDaily(Date date);
    }

    interface Presenter{
        void onRegisterButtonClicked(Date date, String title, String body);
        void onEditTextDateClicked();
        void afterDateTextChanged();
        void afterTitleTextChanged();
    }

}
