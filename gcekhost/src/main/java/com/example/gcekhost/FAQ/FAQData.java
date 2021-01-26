package com.example.gcekhost.FAQ;

public class FAQData {
    String title;
    String description;
    String date ;
    String GcekId;
    String Reply;

    @Override
    public String toString() {
        return "FAQData{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", date='" + date + '\'' +
                ", GcekId='" + GcekId + '\'' +
                ", Reply='" + Reply + '\'' +
                '}';
    }

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

    public String getDate() {
        return date;
    }

    public void setDate(String data) {
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
