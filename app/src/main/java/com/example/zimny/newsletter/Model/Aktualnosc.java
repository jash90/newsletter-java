package com.example.zimny.newsletter.Model;


import com.google.gson.annotations.SerializedName;

/**
 * Created by ideo7 on 07.06.2017.
 */

public class Aktualnosc {
    @SerializedName("link")
    private String link;
    @SerializedName("tresc")
    private String tresc;
    @SerializedName("image")
    private Image image;
    @SerializedName("id_aktualnosci")
    private String id_aktualnosci;
    @SerializedName("autor")
    private String autor;
    @SerializedName("publikator")
    private String publikator;
    @SerializedName("tytul")
    private String tytul;
    @SerializedName("typ")
    private int typ;
    @SerializedName("kotwica")
    private int kotwica;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTresc() {
        return tresc;
    }

    public void setTresc(String tresc) {
        this.tresc = tresc;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getId_aktualnosci() {
        return id_aktualnosci;
    }

    public void setId_aktualnosci(String id_aktualnosci) {
        this.id_aktualnosci = id_aktualnosci;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getPublikator() {
        return publikator;
    }

    public void setPublikator(String publikator) {
        this.publikator = publikator;
    }

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

    public Aktualnosc() {
    }

    public Aktualnosc(String link, String tresc, Image image, String id_aktualnosci, String autor, String publikator, String tytul, int typ, int kotwica) {
        this.link = link;
        this.tresc = tresc;
        this.image = image;
        this.id_aktualnosci = id_aktualnosci;
        this.autor = autor;
        this.publikator = publikator;
        this.tytul = tytul;
        this.typ = typ;
        this.kotwica = kotwica;
    }

    @Override
    public String toString() {
        return "Aktualnosc{" +
                "link='" + link + '\'' +
                ", tresc='" + tresc + '\'' +
                ", image=" + image +
                ", id_aktualnosci='" + id_aktualnosci + '\'' +
                ", autor='" + autor + '\'' +
                ", publikator='" + publikator + '\'' +
                ", tytul='" + tytul + '\'' +
                ", typ=" + typ +
                ", kotwica=" + kotwica +
                '}';
    }
}
