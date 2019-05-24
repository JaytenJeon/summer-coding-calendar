package com.example.summercodingcalendar.util;

import com.example.summercodingcalendar.data.Schedule;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class RealmHelper {
    private static  RealmHelper INSTANCE = new RealmHelper();
    private Realm realm;

    private RealmHelper() {
        realm = Realm.getDefaultInstance();
    }

    public static RealmHelper getInstance() {
        return INSTANCE;
    }

    public void addSchedule(Schedule schedule) {
        realm.beginTransaction();
        realm.copyToRealm(schedule);
        realm.commitTransaction();
    }
    public Schedule getFirst(){
        return realm.where(Schedule.class).findFirst();
    }

}
