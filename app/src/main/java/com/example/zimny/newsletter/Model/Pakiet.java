package com.example.zimny.newsletter.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ideo7 on 14.06.2017.
 */

public class Pakiet {
   @SerializedName("status")
   private String status;
   @SerializedName("rodzaj_konta")
   private int rodzaj_konta;
   @SerializedName("typ_abonamentu")
   private String typ_abonamentu;
   @SerializedName("okres")
   private String okres;
   @SerializedName("data_konca")
   private String data_konca;
   @SerializedName("max_ilosc_dostepow")
   private String max_ilosc_dostepow;
   @SerializedName("wykorzystano_ilosc_dostepow")
   private String wykorzystano_ilosc_dostepow;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getRodzaj_konta() {
        return rodzaj_konta;
    }

    public void setRodzaj_konta(int rodzaj_konta) {
        this.rodzaj_konta = rodzaj_konta;
    }

    public String getTyp_abonamentu() {
        return typ_abonamentu;
    }

    public void setTyp_abonamentu(String typ_abonamentu) {
        this.typ_abonamentu = typ_abonamentu;
    }

    public String getOkres() {
        return okres;
    }

    public void setOkres(String okres) {
        this.okres = okres;
    }

    public String getData_konca() {
        return data_konca;
    }

    public void setData_konca(String data_konca) {
        this.data_konca = data_konca;
    }

    public String getMax_ilosc_dostepow() {
        return max_ilosc_dostepow;
    }

    public void setMax_ilosc_dostepow(String max_ilosc_dostepow) {
        this.max_ilosc_dostepow = max_ilosc_dostepow;
    }

    public String getWykorzystano_ilosc_dostepow() {
        return wykorzystano_ilosc_dostepow;
    }

    public void setWykorzystano_ilosc_dostepow(String wykorzystano_ilosc_dostepow) {
        this.wykorzystano_ilosc_dostepow = wykorzystano_ilosc_dostepow;
    }

    public Pakiet() {
    }

    public Pakiet(String status, int rodzaj_konta, String typ_abonamentu, String okres, String data_konca, String max_ilosc_dostepow, String wykorzystano_ilosc_dostepow) {
        this.status = status;
        this.rodzaj_konta = rodzaj_konta;
        this.typ_abonamentu = typ_abonamentu;
        this.okres = okres;
        this.data_konca = data_konca;
        this.max_ilosc_dostepow = max_ilosc_dostepow;
        this.wykorzystano_ilosc_dostepow = wykorzystano_ilosc_dostepow;
    }

    @Override
    public String toString() {
        return "Pakiet{" +
                "status='" + status + '\'' +
                ", rodzaj_konta=" + rodzaj_konta +
                ", typ_abonamentu='" + typ_abonamentu + '\'' +
                ", okres='" + okres + '\'' +
                ", data_konca='" + data_konca + '\'' +
                ", max_ilosc_dostepow=" + max_ilosc_dostepow +
                ", wykorzystano_ilosc_dostepow=" + wykorzystano_ilosc_dostepow +
                '}';
    }
}
