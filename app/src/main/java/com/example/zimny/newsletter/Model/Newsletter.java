package com.example.zimny.newsletter.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ideo7 on 05.06.2017.
 */

public class Newsletter {
    @SerializedName("id")
    private int id;
    @SerializedName("tytul")
    private String tytul;
    @SerializedName("data_wyslania")
    private String data_wyslania;
    @SerializedName("czas_wyslania")
    private String czas_wyslania;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTytul() {
        return tytul;
    }

    public void setTytul(String tytul) {
        this.tytul = tytul;
    }

    public String getData_wyslania() {
        return data_wyslania;
    }

    public void setData_wyslania(String data_wyslania) {
        this.data_wyslania = data_wyslania;
    }

    public String getCzas_wyslania() {
        return czas_wyslania;
    }

    public void setCzas_wyslania(String czas_wyslania) {
        this.czas_wyslania = czas_wyslania;
    }

    public Newsletter() {
    }

    public Newsletter(int id, String tytul, String data_wyslania, String czas_wyslania) {
        this.id = id;
        this.tytul = tytul;
        this.data_wyslania = data_wyslania;
        this.czas_wyslania = czas_wyslania;
    }

    @Override
    public String toString() {
        return "Newsletter{" +
                "id=" + id +
                ", tytul='" + tytul + '\'' +
                ", data_wyslania='" + data_wyslania + '\'' +
                ", czas_wyslania='" + czas_wyslania + '\'' +
                '}';
    }
}
