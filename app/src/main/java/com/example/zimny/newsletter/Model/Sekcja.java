package com.example.zimny.newsletter.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ideo7 on 07.06.2017.
 */

public class Sekcja {
    @SerializedName("tytul")
    private String tytul;
    @SerializedName("typ")
    private int typ;
    @SerializedName("kotwica")
    private int kotwica;

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

    public Sekcja() {
    }

    public Sekcja(String tytul, int typ, int kotwica) {
        this.tytul = tytul;
        this.typ = typ;
        this.kotwica = kotwica;
    }

    @Override
    public String toString() {
        return "Sekcja{" +
                "tytul='" + tytul + '\'' +
                ", typ=" + typ +
                ", kotwica=" + kotwica +
                '}';
    }
}
