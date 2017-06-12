package com.example.zimny.newsletter.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by ideo7 on 07.06.2017.
 */

public class Data {
    @SerializedName("spis_tresci")
    private Spis_tresci spis_tresci;
    @SerializedName("zawartosc")
    private ArrayList<Element> zawartosc;

    public Spis_tresci getSpis_tresci() {
        return spis_tresci;
    }

    public void setSpis_tresci(Spis_tresci spis_tresci) {
        this.spis_tresci = spis_tresci;
    }

    public ArrayList<Element> getZawartosc() {
        return zawartosc;
    }

    public void setZawartosc(ArrayList<Element> zawartosc) {
        this.zawartosc = zawartosc;
    }

    public Data() {
    }

    public Data(Spis_tresci spis_tresci, ArrayList<Element> zawartosc) {
        this.spis_tresci = spis_tresci;
        this.zawartosc = zawartosc;
    }

    @Override
    public String toString() {
        return "Data{" +
                "spis_tresci=" + spis_tresci +
                ", zawartosc=" + zawartosc +
                '}';
    }
}
