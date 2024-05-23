package com.example.user.model;

public class Notification {
    private int id;
    private String userId;
    private String content;
    private long timestamp;
    private int status;
    public static final int STATUS_UNREAD = 0;
    public static final int STATUS_READ = 1;

    public Notification() {
    }

    public Notification(String userId, String content, long timestamp, int status) {
        this.userId = userId;
        this.content = content;
        this.timestamp = timestamp;
        this.status = status;
        this.id = hashCode();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
