package com.example.zimny.newsletter.Class;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ideo7 on 07.06.2017.
 */

public class Baner extends Element{
    @SerializedName("tresc")
    private String tresc;
    @SerializedName("image")
    private Image image;
    @SerializedName("baner_link")
    private String baner_link;

    public String getTresc() {
        return tresc;
    }

    public void setTresc(String tresc) {
        this.tresc = tresc;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getBaner_link() {
        return baner_link;
    }

    public void setBaner_link(String baner_link) {
        this.baner_link = baner_link;
    }



    public Baner() {
    }

    public Baner(int typ, int kotwica, String tresc, Image image, String baner_link) {
        super(typ, kotwica);
        this.tresc = tresc;
        this.image = image;
        this.baner_link = baner_link;
    }

    @Override
    public String toString() {
        return "Baner{" +
                "tresc='" + tresc + '\'' +
                ", image=" + image +
                ", baner_link='" + baner_link + '\'' +
                '}';
    }
}
