package com.example.zimny.newsletter.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ideo7 on 07.06.2017.
 */

public class Baner  {
    @SerializedName("tresc")
    private String tresc;
    @SerializedName("image")
    private Image image;
    @SerializedName("baner_link")
    private String baner_link;
    private int typ;
    @SerializedName("kotwica")
    private int kotwica;

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

    public int getTyp() {
        return typ;
    }

    public void setTyp(int typ) {
        this.typ = typ;
    }

    public int getKotwica() {
        return kotwica;
    }

    public void setKotwica(int kotwica) {
        this.kotwica = kotwica;
    }

    public Baner() {
    }

    public Baner(String tresc, Image image, String baner_link, int typ, int kotwica) {
        this.tresc = tresc;
        this.image = image;
        this.baner_link = baner_link;
        this.typ = typ;
        this.kotwica = kotwica;
    }

    @Override
    public String toString() {
        return "Baner{" +
                "tresc='" + tresc + '\'' +
                ", image=" + image +
                ", baner_link='" + baner_link + '\'' +
                ", typ=" + typ +
                ", kotwica=" + kotwica +
                '}';
    }
}
