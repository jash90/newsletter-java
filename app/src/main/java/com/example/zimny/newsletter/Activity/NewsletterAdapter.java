package com.example.zimny.newsletter.Activity;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zimny.newsletter.Model.Aktualnosc;
import com.example.zimny.newsletter.Model.Baner;
import com.example.zimny.newsletter.Model.Element;
import com.example.zimny.newsletter.Model.Sekcja;
import com.example.zimny.newsletter.Model.Wiadomosc;
import com.example.zimny.newsletter.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by ideo7 on 12.06.2017.
 */

public class NewsletterAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList<Element> elements = new ArrayList<>();

    public NewsletterAdapter(ArrayList<Element> elements) {
        this.elements = elements;
    }

    class BanerHolder extends RecyclerView.ViewHolder {
        public TextView tresc;
        public ImageView obrazek;

        public BanerHolder(View itemView) {
            super(itemView);
            tresc = (TextView) itemView.findViewById(R.id.tresc_banera);
            obrazek = (ImageView) itemView.findViewById(R.id.obrazek_banera);
        }
    }

    class AktualnoscHolder extends RecyclerView.ViewHolder {
        public TextView tytul_aktualnosci, autor_aktualnosci, publikator_aktualnosci;
        public ImageView obrazek_aktualnosci;
        public WebView tresc_aktualnosci;

        public AktualnoscHolder(View itemView) {
            super(itemView);
            tytul_aktualnosci = (TextView) itemView.findViewById(R.id.tytul_aktualnosci);
            autor_aktualnosci = (TextView) itemView.findViewById(R.id.autor_aktualnosci);
            publikator_aktualnosci = (TextView) itemView.findViewById(R.id.publikator_aktualnosci);
            obrazek_aktualnosci = (ImageView) itemView.findViewById(R.id.obrazek_aktualnosci);
            tresc_aktualnosci = (WebView) itemView.findViewById(R.id.tresc_aktualnosci);
        }
    }

    class WiadomoscHolder extends RecyclerView.ViewHolder {
        public TextView tytul;
        public WebView tresc;

        public WiadomoscHolder(View itemView) {
            super(itemView);
            tytul = (TextView) itemView.findViewById(R.id.tytul_wiadomosci);
            tresc = (WebView) itemView.findViewById(R.id.tresc_wiadomosci);
        }
    }

    class SekcjaHolder extends RecyclerView.ViewHolder {
        public TextView tytul;

        public SekcjaHolder(View itemView) {
            super(itemView);
            tytul = (TextView) itemView.findViewById(R.id.tytul_sekcji);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return elements.get(position).getTyp();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 0: {
                View itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.row_sekcja, parent, false);
                return new SekcjaHolder(itemView);
            }
            case 1: {
                View itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.row_wiadomosc, parent, false);
                return new WiadomoscHolder(itemView);
            }
            case 2: {
                View itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.row_aktualnosc, parent, false);
                return new AktualnoscHolder(itemView);
            }
            case 3: {
                View itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.row_baner, parent, false);
                return new BanerHolder(itemView);
            }
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Element element = elements.get(position);
        if (element != null) {
            switch (element.getTyp()) {
                case 0: {
                    try {
                        Sekcja sekcja = element.toSekcja();

                        SekcjaHolder sekcjaHolder = (SekcjaHolder) holder;
                        if (sekcjaHolder.tytul != null)
                            sekcjaHolder.tytul.setText(sekcja.getTytul());
                    }
                    catch (Exception ex)
                    {
                        Log.d("dddd",ex.getLocalizedMessage());
                    }
                }
                case 1: {
                    try{
                    Wiadomosc wiadomosc = element.toWiadomosc();
                    WiadomoscHolder wiadomoscHolder = (WiadomoscHolder) holder;
                    if (wiadomoscHolder.tytul!=null)
                    wiadomoscHolder.tytul.setText(wiadomosc.getTytul());
                    if (wiadomoscHolder.tresc!=null)
                    wiadomoscHolder.tresc.loadData(wiadomosc.getTresc(), "text/html", null);
                }
                    catch (Exception ex)
                {
                    Log.d("dddd",ex.getLocalizedMessage());
                }
                }
                case 2: {
                    try {
                    Aktualnosc aktualnosc = element.toAktualnosc();
                    AktualnoscHolder aktualnoscHolder = (AktualnoscHolder) holder;
                    if (aktualnoscHolder.tresc_aktualnosci != null)
                        aktualnoscHolder.tresc_aktualnosci.loadData(aktualnosc.getTresc(), "text/html", null);
                    if (aktualnoscHolder.obrazek_aktualnosci != null) {
                        aktualnoscHolder.obrazek_aktualnosci.getLayoutParams().height = aktualnosc.getImage().getHeight();
                        aktualnoscHolder.obrazek_aktualnosci.getLayoutParams().width = aktualnosc.getImage().getWidth();
                        aktualnoscHolder.obrazek_aktualnosci.requestLayout();
                        Picasso.with(aktualnoscHolder.obrazek_aktualnosci.getContext()).load(aktualnosc.getImage().getLink()).into(aktualnoscHolder.obrazek_aktualnosci);
                    }
                    if (aktualnoscHolder.autor_aktualnosci!=null)
                    aktualnoscHolder.autor_aktualnosci.setText(aktualnosc.getAutor());
                    if (aktualnoscHolder.publikator_aktualnosci!=null)
                    aktualnoscHolder.publikator_aktualnosci.setText(aktualnosc.getPublikator());
                    if (aktualnoscHolder.tytul_aktualnosci!=null)
                    aktualnoscHolder.tytul_aktualnosci.setText(aktualnosc.getTytul());
                    }
                    catch (Exception ex)
                    {
                        Log.d("dddd",ex.getLocalizedMessage());
                    }

                }
                case 3: {
                    try {
                    Baner baner = element.toBaner();
                    BanerHolder banerHolder = (BanerHolder) holder;
                    if (baner.getTresc() != null)
                        banerHolder.tresc.setText(baner.getTresc());
                    if (baner.getImage() != null) {
                        banerHolder.obrazek.getLayoutParams().height = baner.getImage().getHeight();
                        banerHolder.obrazek.getLayoutParams().width = baner.getImage().getWidth();
                        banerHolder.obrazek.requestLayout();
                        Picasso.with(banerHolder.obrazek.getContext()).load(baner.getImage().getLink()).into(banerHolder.obrazek);
                    }
                    }
                    catch (Exception ex)
                    {
                        Log.d("dddd",ex.getLocalizedMessage());
                    }
                }
            }
        }

    }

    @Override
    public int getItemCount() {
        return elements.size();
    }
}
