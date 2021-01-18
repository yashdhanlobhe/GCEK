package com.example.gcek.maindrawer.hometab;

import android.net.Uri;

public class PosterData {
    private String id;
    private String noticeURI;
    private String title;

    public PosterData(String id, String noticeURI, String title) {
        this.id = id;
        this.noticeURI = noticeURI;
        this.title = title;
    }

    public PosterData() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNoticeURI() {
        return noticeURI;
    }

    public void setNoticeURI(String noticeURI) {
        this.noticeURI = noticeURI;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
