package com.example.zimny.newsletter.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ideo7 on 07.06.2017.
 */

public class Image {
    @SerializedName("link")
    private String link;
    @SerializedName("width")
    private int width;
    @SerializedName("height")
    private int height;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Image() {
    }

    public Image(String link, int width, int height) {
        this.link = link;
        this.width = width;
        this.height = height;
    }

    @Override
    public String toString() {
        return "Image{" +
                "link='" + link + '\'' +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
