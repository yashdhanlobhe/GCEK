package com.example.gcek.MainDrawer.publicForum;

import javax.net.ssl.SSLContext;

public class PublicForumItemData {
    String from , title , description , reply ;
    String time;
    public PublicForumItemData() {
    }

    public PublicForumItemData(String from, String title, String description, String reply, String time) {
        this.from = from;
        this.title = title;
        this.description = description;
        this.reply = reply;
        this.time = time;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }


}
