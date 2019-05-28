package com.example.summercodingcalendar.data;

import java.util.Date;

import io.realm.RealmObject;

public class Schedule extends RealmObject {
    private Date date;
    private String title;
    private String body;
    private boolean isDeleted;

    public Schedule() {
    }

    public Schedule(Date date, String title, String body) {
        this.date = date;
        this.title = title;
        this.body =  body != null ? body.trim() : "";
        this.isDeleted = false;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
