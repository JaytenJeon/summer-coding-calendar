package com.example.summercodingcalendar.util;

import android.util.Log;

import com.example.summercodingcalendar.data.Schedule;

import java.util.Date;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class RealmHelper {
    private static  RealmHelper INSTANCE = new RealmHelper();
    private Realm realm;

    private RealmHelper() {
        realm = Realm.getInstance(new RealmConfiguration.Builder()
                .schemaVersion(1)
                .migration(new Migration())
                .build()
        );
    }

    public static RealmHelper getInstance() {
        return INSTANCE;
    }

    public void addSchedule(Schedule schedule) {
        realm.beginTransaction();
        realm.copyToRealm(schedule);
        realm.commitTransaction();
    }

    public List<Schedule> getAllSchedules( ){
        List<Schedule> schedules = realm.where(Schedule.class)
                .equalTo("isDeleted", false)
                .findAll();
        return schedules;
    }

    public List<Schedule> getSchedulesAt(Date date){
        date = Converter.stringToDate(Converter.dateToString(date));
        List<Schedule> schedules = realm.where(Schedule.class)
                .equalTo("date", date)
                .equalTo("isDeleted", false)
                .findAll();
        return schedules;
    }

    public List<Schedule> getSchedulesForWeek(List<Date> dates){
        Date startDate = Converter.stringToDate(Converter.dateToString(dates.get(0)));
        Date endDate = Converter.stringToDate(Converter.dateToString(dates.get(1)));

        List<Schedule> schedules = realm.where(Schedule.class)
                .equalTo("isDeleted", false)
                .greaterThanOrEqualTo("date", startDate)
                .lessThanOrEqualTo("date", endDate)
                .findAll();
        return schedules;
    }

    public void removeSchedule(Schedule schedule){
        realm.beginTransaction();
        schedule.setDeleted(true);
        realm.commitTransaction();
    }

    public void undoSchedule(Schedule schedule){
        realm.beginTransaction();
        schedule.setDeleted(false);
        realm.commitTransaction();
    }
}
