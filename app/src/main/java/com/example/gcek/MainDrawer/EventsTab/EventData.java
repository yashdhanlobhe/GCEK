package com.example.gcek.MainDrawer.EventsTab;

import android.icu.text.CaseMap;

public class EventData {
    String title , uri;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public EventData(String title, String uri) {

        this.title = title;
        this.uri = uri;
    }

    public EventData() {
    }

    @Override
    public String toString() {
        return "EventData{" +
                "uri='" + uri + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
