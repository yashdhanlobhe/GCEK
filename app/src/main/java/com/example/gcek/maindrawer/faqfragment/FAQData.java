package com.example.gcek.maindrawer.faqfragment;

public class FAQData {
    String title;
    String description;
    String date ;
    String GcekId;
    String Reply;

    public FAQData(String title, String description, String date, String GcekId, String Reply) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.GcekId = GcekId;
        this.Reply = Reply;
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

    public String getData() {
        return date;
    }

    public void setData(String data) {
        date = data;
    }

    public String getGcekId() {
        return GcekId;
    }

    public void setGcekId(String gcekId) {
        GcekId = gcekId;
    }

    public String getReply() {
        return Reply;
    }

    public void setReply(String reply) {
        Reply = reply;
    }

    public FAQData() {
    }
}
