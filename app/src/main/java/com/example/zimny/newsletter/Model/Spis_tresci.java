package com.example.zimny.newsletter.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by ideo7 on 14.06.2017.
 */

public class Spis_tresci {
    @SerializedName("tytul")
    private String tytul;
    @SerializedName("pozycje")
    private ArrayList<Pozycja> pozycje;

    private ArrayList<Tresc> spi_tresci;

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

    public ArrayList<Tresc> getSpi_tresci() {
        return spi_tresci;
    }

    public void setSpi_tresci(ArrayList<Tresc> spi_tresci) {
        this.spi_tresci = spi_tresci;
    }

    public Spis_tresci() {
    }

    public Spis_tresci(String tytul, ArrayList<Pozycja> pozycje) {
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
