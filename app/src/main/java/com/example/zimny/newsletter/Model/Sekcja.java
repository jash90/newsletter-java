package com.example.zimny.newsletter.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ideo7 on 07.06.2017.
 */

public class Sekcja extends Element22 {
    @SerializedName("tytul")
    private String tytul;

    public String getTytul() {
        return tytul;
    }

    public void setTytul(String tytul) {
        this.tytul = tytul;
    }

    public Sekcja() {
    }

    public Sekcja(int typ, int kotwica, String tytul) {
        super(typ, kotwica);
        this.tytul = tytul;
    }

    @Override
    public String toString() {
        return "Sekcja{" +
                "tytul='" + tytul + '\'' +
                '}';
    }
}
