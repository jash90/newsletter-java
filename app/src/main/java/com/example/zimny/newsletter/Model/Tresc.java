package com.example.zimny.newsletter.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by ideo7 on 07.06.2017.
 */

public class Tresc {
    @SerializedName("tytul")
    private String tytul;
    @SerializedName("pozycje")
    private ArrayList<Pozycja> pozycje;

    public String getTytul() {
        return tytul;
    }

    public void setTytul(String tytul) {
        this.tytul = tytul;
    }

    public ArrayList<Pozycja> getPozycje() {
        return pozycje;
    }

    public void setPozycje(ArrayList<Pozycja> pozycje) {
        this.pozycje = pozycje;
    }

    public Tresc() {
    }

    public Tresc(String tytul, ArrayList<Pozycja> pozycje) {
        this.tytul = tytul;
        this.pozycje = pozycje;
    }

    @Override
    public String toString() {
        return "Tresc{" +
                "tytul='" + tytul + '\'' +
                ", pozycje=" + pozycje +
                '}';
    }
}
