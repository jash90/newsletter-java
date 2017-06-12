package com.example.zimny.newsletter.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by ideo7 on 05.06.2017.
 */

public class Newsletters {
    @SerializedName("status")
    private String status;
    @SerializedName("data")
    private ArrayList<Newsletter> data = new ArrayList<>();
    @SerializedName("pages")
    private int pages;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<Newsletter> getData() {
        return data;
    }

    public void setData(ArrayList<Newsletter> data) {
        this.data = data;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public Newsletters() {
    }

    public Newsletters(String status, ArrayList<Newsletter> newsletters, int pages) {
        this.status = status;
        this.data = newsletters;
        this.pages = pages;
    }

    @Override
    public String toString() {
        return "Newsletters{" +
                "status='" + status + '\'' +
                ", data=" + data +
                ", pages=" + pages +
                '}';
    }
}
