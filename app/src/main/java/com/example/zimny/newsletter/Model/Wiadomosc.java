package com.example.zimny.newsletter.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ideo7 on 07.06.2017.
 */

public class Wiadomosc {
    @SerializedName("link")
    private String link;
    @SerializedName("tresc")
    private String tresc;
    @SerializedName("tytul")
    private String tytul;
    @SerializedName("typ")
    private int typ;
    @SerializedName("kotwica")
    private int kotwica;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTresc() {
        return tresc;
    }

    public void setTresc(String tresc) {
        this.tresc = tresc;
    }

    public String getTytul() {
        return tytul;
    }

    public void setTytul(String tytul) {
        this.tytul = tytul;
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

    public Wiadomosc() {
    }

    public Wiadomosc(String link, String tresc, String tytul, int typ, int kotwica) {
        this.link = link;
        this.tresc = tresc;
        this.tytul = tytul;
        this.typ = typ;
        this.kotwica = kotwica;
    }

    @Override
    public String toString() {
        return "Wiadomosc{" +
                "link='" + link + '\'' +
                ", tresc='" + tresc + '\'' +
                ", tytul='" + tytul + '\'' +
                ", typ=" + typ +
                ", kotwica=" + kotwica +
                '}';
    }
}
