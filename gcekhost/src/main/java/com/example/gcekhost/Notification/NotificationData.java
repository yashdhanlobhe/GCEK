package com.example.gcekhost.Notification;

public class NotificationData {
    public String title;
    public String description;
    public String id;

    public NotificationData() {
    }

    public NotificationData(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
