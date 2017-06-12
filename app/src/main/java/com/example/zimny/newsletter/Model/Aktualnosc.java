package com.example.zimny.newsletter.Model;


import com.google.gson.annotations.SerializedName;

/**
 * Created by ideo7 on 07.06.2017.
 */

public class Aktualnosc extends Sekcja {
    @SerializedName("link")
    private String link;
    @SerializedName("tresc")
    private String tresc;
    @SerializedName("image")
    private Image image;
    @SerializedName("id_aktualnosci")
    private int id_aktualnosci;
    @SerializedName("autor")
    private String autor;
    @SerializedName("publikator")
    private String publikator;

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

    public Aktualnosc() {
    }

    public Aktualnosc(String tytul, int typ, int kotwica, String link, String tresc, Image image, int id_aktualnosci, String autor, String publikator) {
        super(typ,kotwica,tytul);
        this.link = link;
        this.tresc = tresc;
        this.image = image;
        this.id_aktualnosci = id_aktualnosci;
        this.autor = autor;
        this.publikator = publikator;
    }

    @Override
    public String toString() {
        return "Aktualnosc{" +
                "link='" + link + '\'' +
                ", tresc='" + tresc + '\'' +
                ", image=" + image +
                ", id_aktualnosci=" + id_aktualnosci +
                ", autor='" + autor + '\'' +
                ", publikator='" + publikator + '\'' +
                '}';
    }
}
