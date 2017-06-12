package com.example.zimny.newsletter.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ideo7 on 07.06.2017.
 */

public class Element {

    @SerializedName("typ")
    private int typ;
    @SerializedName("kotwica")
    private int kotwica;
    @SerializedName("tresc")
    private String tresc;
    @SerializedName("image")
    private Image image;
    @SerializedName("baner_link")
    private String baner_link;
    @SerializedName("link")
    private String link;
    @SerializedName("tytul")
    private String tytul;
    @SerializedName("id_aktualnosci")
    private int id_aktualnosci;
    @SerializedName("autor")
    private String autor;
    @SerializedName("publikator")
    private String publikator;

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

    public String getBaner_link() {
        return baner_link;
    }

    public void setBaner_link(String baner_link) {
        this.baner_link = baner_link;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTytul() {
        return tytul;
    }

    public void setTytul(String tytul) {
        this.tytul = tytul;
    }

    public int getId_aktualnosci() {
        return id_aktualnosci;
    }

    public void setId_aktualnosci(int id_aktualnosci) {
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

    public Element() {
    }
    public Element(int typ, int kotwica, String tytul) {
        this.typ = typ;
        this.kotwica = kotwica;
        this.tytul = tytul;
    }
    public Element(int typ, int kotwica, String tytul, String link, String tresc) {
        this.typ = typ;
        this.kotwica = kotwica;
        this.tytul = tytul;
        this.link = link;
        this.tresc = tresc;
    }
    public Element(String tytul, int typ, int kotwica, String link, String tresc, Image image, int id_aktualnosci, String autor, String publikator) {
        this.typ = typ;
        this.kotwica = kotwica;
        this.tytul = tytul;
        this.link = link;
        this.tresc = tresc;
        this.image = image;
        this.id_aktualnosci = id_aktualnosci;
        this.autor = autor;
        this.publikator = publikator;
    }
    public Element(int typ, int kotwica, String tresc, Image image, String baner_link) {
        this.typ = typ;
        this.kotwica = kotwica;
        this.tresc = tresc;
        this.image = image;
        this.baner_link = baner_link;
    }
    public Sekcja toSekcja()
    {
        return new Sekcja(typ,kotwica,tytul);
    }
    public Aktualnosc toAktualnosc()
    {
        return new Aktualnosc(tytul,typ,kotwica,link,tresc,image,id_aktualnosci,autor,publikator);
    }
    public Wiadomosc toWiadomosc()
    {
        return new Wiadomosc(typ,kotwica,tytul,link,tresc);
    }
    public Baner toBaner(){
        return new Baner(typ,kotwica,tresc,image,baner_link);
    }

    @Override
    public String toString() {
        return "Element{" +
                "typ=" + typ +
                ", kotwica=" + kotwica +
                ", tresc='" + tresc + '\'' +
                ", image=" + image +
                ", baner_link='" + baner_link + '\'' +
                ", link='" + link + '\'' +
                ", tytul='" + tytul + '\'' +
                ", id_aktualnosci=" + id_aktualnosci +
                ", autor='" + autor + '\'' +
                ", publikator='" + publikator + '\'' +
                '}';
    }
}
