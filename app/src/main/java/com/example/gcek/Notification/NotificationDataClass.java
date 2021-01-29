package com.example.gcek.Notification;

public class NotificationDataClass {
    public String title;
    public String description;
    public String id;
    public String noticeURI;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public NotificationDataClass() {
    }

    public String getNoticeURI() {
        return noticeURI;
    }

    public void setNoticeURI(String noticeURI) {
        this.noticeURI = noticeURI;
    }

    public NotificationDataClass(String title, String description, String id , String noticeURI) {
        this.title = title;
        this.description = description;
        this.id = id;
        this.noticeURI=noticeURI;
    }
}
