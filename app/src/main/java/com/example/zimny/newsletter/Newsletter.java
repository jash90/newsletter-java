package com.example.zimny.newsletter;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Created by ideo7 on 05.06.2017.
 */

public class Newsletter {
    private int id;
    private String name;
    private Timestamp date_send;
    private Time time_send;
    private static int lastId = 0;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getDate_send() {
        return date_send;
    }

    public void setDate_send(Timestamp date_send) {
        this.date_send = date_send;
    }

    public Time getTime_send() {
        return time_send;
    }

    public void setTime_send(Time time_send) {
        this.time_send = time_send;
    }

    public static int getLastId() {
        return lastId;
    }

    public static void setLastId(int lastId) {
        Newsletter.lastId = lastId;
    }

    public Newsletter() {
    }

    public Newsletter(int id, String name, Timestamp date_send, Time time_send) {
        this.id = id;
        this.name = name;
        this.date_send = date_send;
        this.time_send = time_send;
    }

    @Override
    public String toString() {
        return "Newsletter{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date_send=" + date_send +
                ", time_send=" + time_send +
                '}';
    }


}
