package com.example.zimny.newsletter.Class;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ideo7 on 07.06.2017.
 */

public class NewsletterContent {
    @SerializedName("status")
    private String status;
    @SerializedName("data")
    private Data data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public NewsletterContent() {
    }

    public NewsletterContent(String status, Data data) {
        this.status = status;
        this.data = data;
    }

    @Override
    public String toString() {
        return "NewsletterContent{" +
                "status='" + status + '\'' +
                ", data=" + data +
                '}';
    }
}
