package com.example.zimny.newsletter;

import java.util.List;

/**
 * Created by ideo7 on 05.06.2017.
 */

public class Newsletters {
    private String status;
    private List<Newsletter> newsletters;
    private int pages;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Newsletter> getNewsletters() {
        return newsletters;
    }

    public void setNewsletters(List<Newsletter> newsletters) {
        this.newsletters = newsletters;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public Newsletters() {
    }

    public Newsletters(String status, List<Newsletter> newsletters, int pages) {
        this.status = status;
        this.newsletters = newsletters;
        this.pages = pages;
    }

    @Override
    public String toString() {
        return "Newsletters{" +
                "status='" + status + '\'' +
                ", newsletters=" + newsletters +
                ", pages=" + pages +
                '}';
    }
}
