package com.example.zimny.newsletter.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ideo7 on 07.06.2017.
 */

public class Pozycja {
    @SerializedName("tytul")
    private String tytul;
    @SerializedName("kotwica")
    private int kotwica;

    public String getTytul() {
        return tytul;
    }

    public void setTytul(String tytul) {
        this.tytul = tytul;
    }

    public int getKotwica() {
        return kotwica;
    }

    public void setKotwica(int kotwica) {
        this.kotwica = kotwica;
    }

    public Pozycja() {
    }

    public Pozycja(String tytul, int kotwica) {
        this.tytul = tytul;
        this.kotwica = kotwica;
    }

    @Override
    public String toString() {
        return "Pozycja{" +
                "tytul='" + tytul + '\'' +
                ", kotwica=" + kotwica +
                '}';
    }
}
