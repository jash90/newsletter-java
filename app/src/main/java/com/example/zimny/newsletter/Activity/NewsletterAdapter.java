package com.example.zimny.newsletter.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zimny.newsletter.Api.BeinsuredClient;
import com.example.zimny.newsletter.Api.ServiceGenerator;
import com.example.zimny.newsletter.Model.Element;
import com.example.zimny.newsletter.Model.Komentarz;
import com.example.zimny.newsletter.Model.NewsletterContent;
import com.example.zimny.newsletter.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        public Button komentarz;

        public AktualnoscHolder(View itemView) {
            super(itemView);
            tytul_aktualnosci = (TextView) itemView.findViewById(R.id.tytul_aktualnosci);
            autor_aktualnosci = (TextView) itemView.findViewById(R.id.autor_aktualnosci);
            publikator_aktualnosci = (TextView) itemView.findViewById(R.id.publikator_aktualnosci);
            obrazek_aktualnosci = (ImageView) itemView.findViewById(R.id.obrazek_aktualnosci);
            tresc_aktualnosci = (WebView) itemView.findViewById(R.id.tresc_aktualnosci);
            komentarz = (Button) itemView.findViewById(R.id.przycisk_aktualnosci);
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
                        SekcjaHolder sekcjaHolder = (SekcjaHolder) holder;
                        if (element.getTytul() != null) {
                            sekcjaHolder.tytul.setText(element.getTytul());
                        }
                    } catch (Exception ex) {
                        Log.d("dddd", ex.getLocalizedMessage());
                    }
                }
                break;
                case 1: {
                    try {
                        WiadomoscHolder wiadomoscHolder = (WiadomoscHolder) holder;
                        if (element.getTytul() != null) {
                            wiadomoscHolder.tytul.setText(element.getTytul());
                        } else {
                            wiadomoscHolder.tytul.setVisibility(View.GONE);
                        }
                        if (element.getTresc() != null) {
                            wiadomoscHolder.tresc.loadData(element.getTresc(), "text/html; charset=UTF-8", null);
                            wiadomoscHolder.tresc.setVerticalScrollBarEnabled(false);
                        } else {
                            wiadomoscHolder.tresc.setVisibility(View.GONE);
                        }
                        if (element.getLink() != null && !element.getLink().isEmpty()) {
                            wiadomoscHolder.tytul.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent();
                                    intent.setAction(Intent.ACTION_VIEW);
                                    intent.addCategory(Intent.CATEGORY_BROWSABLE);
                                    intent.setData(Uri.parse(element.getLink()));
                                    v.getContext().startActivity(intent);
                                }
                            });
                        }
                    } catch (Exception ex) {
                        Log.d("dddd", ex.getLocalizedMessage());
                    }
                }
                break;
                case 2: {
                    try {
                        AktualnoscHolder aktualnoscHolder = (AktualnoscHolder) holder;
                        aktualnoscHolder.komentarz.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                final Dialog dialog = new Dialog(v.getContext());
                                dialog.setContentView(R.layout.save_dialog);
                                final EditText komentarzEditText = (EditText) dialog.findViewById(R.id.komentarz);
                                Button anuluj = (Button) dialog.findViewById(R.id.anuluj);
                                anuluj.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        dialog.dismiss();
                                    }
                                });
                                Button dodaj = (Button) dialog.findViewById(R.id.dodaj);
                                dodaj.setOnClickListener(new View.OnClickListener() {

                                    @Override
                                    public void onClick(final View v) {
                                        try {
                                            if (!komentarzEditText.getText().toString().isEmpty()) {
                                                BeinsuredClient beinsuredClient = ServiceGenerator.createService(BeinsuredClient.class,  Attributes.getLogin_token());
                                                Call<Komentarz> call = beinsuredClient.addcomment("2esde2#derdsr#RD", element.getId_aktualnosci(), komentarzEditText.getText().toString());
                                                call.enqueue(new Callback<Komentarz>() {
                                                                 @Override
                                                                 public void onResponse(Call<Komentarz> call, Response<Komentarz> response) {
                                                                     Toast.makeText(v.getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                                                     dialog.dismiss();
                                                                 }

                                                                 @Override
                                                                 public void onFailure(Call<Komentarz> call, Throwable t) {
                                                                     Log.d("ddd", t.getLocalizedMessage());
                                                                 }
                                                             }

                                                );
                                            } else {
                                                Toast.makeText(v.getContext(), "Komentarz jest za krótki.", Toast.LENGTH_SHORT).show();
                                            }
                                        } catch (Exception ex) {
                                            Log.e("ddd", ex.getLocalizedMessage());
                                        }
                                    }
                                });
                                dialog.show();
                            }
                        });
                        if (element.getTresc() != null) {
                            aktualnoscHolder.tresc_aktualnosci.loadData(element.getTresc(), "text/html; charset=UTF-8", null);
                            aktualnoscHolder.tresc_aktualnosci.setVerticalScrollBarEnabled(false);
                        } else {
                            aktualnoscHolder.tresc_aktualnosci.setVisibility(View.GONE);
                        }

                        if (element.getImage() != null) {

                            Picasso.with(aktualnoscHolder.obrazek_aktualnosci.getContext())
                                    .load(element.getImage().getLink())
                                    .resize(element.getImage().getWidth(), element.getImage().getHeight())
                                    .into(aktualnoscHolder.obrazek_aktualnosci);
                        } else {
                            aktualnoscHolder.obrazek_aktualnosci.setVisibility(View.GONE);
                        }
                        if (element.getAutor() != null && !element.getAutor().isEmpty()) {
                            aktualnoscHolder.autor_aktualnosci.setText("Autor : " + element.getAutor());
                        } else
                            aktualnoscHolder.autor_aktualnosci.setVisibility(View.GONE);
                        if (element.getPublikator() != null && !element.getPublikator().isEmpty()) {
                            aktualnoscHolder.publikator_aktualnosci.setText("Publikator : " + element.getPublikator());
                        } else {
                            aktualnoscHolder.publikator_aktualnosci.setVisibility(View.GONE);
                        }
                        if (element.getTytul() != null) {
                            aktualnoscHolder.tytul_aktualnosci.setText(element.getTytul());
                            Log.d("tytul", element.getTytul() + " " + element.getTresc().substring(0, 20));
                        } else {
                            aktualnoscHolder.tytul_aktualnosci.setVisibility(View.GONE);
                        }
                        if (element.getLink() != null && !element.getLink().isEmpty()) {
                            aktualnoscHolder.tytul_aktualnosci.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent();
                                    intent.setAction(Intent.ACTION_VIEW);
                                    intent.addCategory(Intent.CATEGORY_BROWSABLE);
                                    intent.setData(Uri.parse(element.getLink()));
                                    v.getContext().startActivity(intent);
                                }
                            });
                        }

                    } catch (Exception ex) {
                        Log.e("error", ex.getLocalizedMessage());
                    }


                }
                break;
                case 3: {
                    try {
                        BanerHolder banerHolder = (BanerHolder) holder;
                        if (element.getTresc() != null && !element.getTresc().isEmpty()) {
                            banerHolder.tresc.setText(element.getTresc());
                        } else {
                            banerHolder.tresc.setVisibility(View.GONE);
                        }
                        if (element.getImage() != null) {
                            Picasso.with(banerHolder.obrazek.getContext())
                                    .load(element.getImage().getLink())
                                    .resize(element.getImage().getWidth(), element.getImage().getHeight())
                                    .into(banerHolder.obrazek);
                        } else {
                            banerHolder.obrazek.setVisibility(View.GONE);
                        }
                        if (element.getBaner_link() != null && !element.getBaner_link().isEmpty()) {
                            banerHolder.obrazek.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent();
                                    intent.setAction(Intent.ACTION_VIEW);
                                    intent.addCategory(Intent.CATEGORY_BROWSABLE);
                                    intent.setData(Uri.parse(element.getBaner_link()));
                                    v.getContext().startActivity(intent);
                                }
                            });
                        }
                    } catch (Exception ex) {
                        Log.d("error", ex.getLocalizedMessage());
                    }
                }
                break;
            }
            Log.d("", element.toString());
        }

    }

    @Override
    public int getItemCount() {
        return elements.size();
    }
}
