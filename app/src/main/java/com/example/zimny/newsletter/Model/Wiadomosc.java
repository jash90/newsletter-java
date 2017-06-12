package com.example.zimny.newsletter.Model;

/**
 * Created by ideo7 on 07.06.2017.
 */

public class Wiadomosc extends Sekcja {
private String link;
private String tresc;

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


    public Wiadomosc() {
    }

    public Wiadomosc(int typ, int kotwica, String tytul, String link, String tresc) {
        super(typ, kotwica, tytul);
        this.link = link;
        this.tresc = tresc;
    }

    @Override
    public String toString() {
        return "Wiadomosc{" +
                "link='" + link + '\'' +
                ", tresc='" + tresc + '\'' +
                '}';
    }
}
