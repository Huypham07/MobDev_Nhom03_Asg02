package com.example.asg02.model;

import android.graphics.Bitmap;

import java.io.Serializable;

// Thêm class này tạm để chứa dữ liệu
public class Event implements Serializable {
    private String eventName;
    private String poster;
    private String startDate;
    private String endDate;
    private String eventInfo;

    public Event(){}

    public Event(String eventName, String poster, String startDate, String endDate, String eventInfo) {
        this.eventName = eventName;
        this.poster = poster;
        this.startDate = startDate;
        this.endDate = endDate;
        this.eventInfo = eventInfo;
    }

    public String getEventInfo() {
        return eventInfo;
    }

    public void setEventInfo(String eventInfo) {
        this.eventInfo = eventInfo;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getPoster() {
        return poster;
    }

    public void setPosterPath(String poster) {
        this.poster = poster;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
