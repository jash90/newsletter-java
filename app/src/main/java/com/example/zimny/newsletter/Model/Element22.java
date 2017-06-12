package com.example.zimny.newsletter.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ideo7 on 07.06.2017.
 */

public class Element22 {

    @SerializedName("typ")
    private int typ;
    @SerializedName("kotwica")
    private int kotwica;

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

    public Element22() {
    }

    public Element22(int typ, int kotwica) {

        this.typ = typ;
        this.kotwica = kotwica;
    }

    @Override
    public String toString() {
        return "Element22{" +
                " typ=" + typ +
                ", kotwica=" + kotwica +
                '}';
    }
}
