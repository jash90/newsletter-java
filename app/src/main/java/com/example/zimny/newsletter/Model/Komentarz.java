package com.example.zimny.newsletter.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ideo7 on 14.06.2017.
 */

public class Komentarz {
    @SerializedName("status")
    String status;
    @SerializedName("message")
    String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Komentarz() {
    }

    public Komentarz(String status, String message) {
        this.status = status;
        this.message = message;
    }

    @Override
    public String toString() {
        return "Komentarz{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
