package com.example.zimny.newsletter.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by ideo7 on 07.06.2017.
 */

class Spis_tresci {
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

    public Spis_tresci() {
    }

    public Spis_tresci(String tytul, ArrayList<Pozycja> pozycje) {
        this.tytul = tytul;
        this.pozycje = pozycje;
    }

    @Override
    public String toString() {
        return "Spis_tresci{" +
                "tytul='" + tytul + '\'' +
                ", pozycje=" + pozycje +
                '}';
    }
}
