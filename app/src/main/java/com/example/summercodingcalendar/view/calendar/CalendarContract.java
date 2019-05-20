package com.example.summercodingcalendar.view.calendar;

public interface CalendarContract {
    interface View{
        void setView();
        void navigateToRegisterActivity();
    }

    interface Presenter{
        void onFabClicked();
    }

}
