package com.example.admin.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.Base64;

// Thêm class này tạm để chứa dữ liệu
public class Event implements Serializable {
    private int id;
    private String eventName;
    private String posterUri;
    private String startDate;
    private String endDate;
    private String eventInfo;

    public Event(){}

    public Event(String eventName, String posterUri, String startDate, String endDate, String eventInfo) {
        this.eventName = eventName;
        this.posterUri = posterUri;
        this.startDate = startDate;
        this.endDate = endDate;
        this.eventInfo = eventInfo;
        id = hashCode();
    }

    public int getId() {
        return id;
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
        return posterUri;
    }

    public void setPoster(String poster) {
        this.posterUri = poster;
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

    public void setValue(String eventName, String posterUri, String startDate, String endDate, String eventInfo) {
        this.eventName = eventName;
        this.posterUri = posterUri;
        this.startDate = startDate;
        this.endDate = endDate;
        this.eventInfo = eventInfo;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public static String encodeImage(Bitmap bitmap) {
        int previewWidth = 400;
        int previewHeight = bitmap.getHeight() * previewWidth / bitmap.getWidth();
        Bitmap previewBitmap = Bitmap.createScaledBitmap(bitmap, previewWidth, previewHeight, false);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        previewBitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] bytes = byteArrayOutputStream.toByteArray();
        return Base64.getEncoder().encodeToString(bytes);
    }

    public static Bitmap decodeBitmap(String uri) {
        byte[] bytes = Base64.getDecoder().decode(uri);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }
}
