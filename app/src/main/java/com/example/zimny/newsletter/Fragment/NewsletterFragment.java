package com.example.zimny.newsletter.Fragment;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.zimny.newsletter.Activity.Attributes;
import com.example.zimny.newsletter.Activity.MainActivity;
import com.example.zimny.newsletter.Activity.NewsletterAdapter;
import com.example.zimny.newsletter.Api.ServiceGenerator;
import com.example.zimny.newsletter.Model.Element;
import com.example.zimny.newsletter.Model.NewsletterContent;
import com.example.zimny.newsletter.Api.BeinsuredClient;
import com.example.zimny.newsletter.R;

import java.sql.Timestamp;
import java.util.ArrayList;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class NewsletterFragment extends Fragment {


    private ArrayList<Element> elements;
    private RecyclerView rvNewsletter;
    private NewsletterAdapter adapter;
    private int id_newsletter;
    private Menu menu;
    private Toolbar toolbar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_main3, container,false);
        //id_newsletter = intent.getIntExtra("id_newsletter", -1);
        rvNewsletter = (RecyclerView) v.findViewById(R.id.newsletterRecycler);
        toolbar = (Toolbar) v.findViewById(R.id.toolbar);
        menu = toolbar.getMenu();
        toolbar.setOverflowIcon(ContextCompat.getDrawable(getContext(),R.drawable.ic_black_hamburger));
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                for (int i = 0; i < elements.size(); i++)
                    if (item.getTitle() == elements.get(i).getTytul()) {
                        rvNewsletter.scrollToPosition(i);
                    }
                        return true;


        }});
        //  toolbar.setNavigationIcon(R.drawable.icon_beinsured);
        //    menu.findItem(Menu.FIRST).setIcon(R.drawable.ic_black_hamburger);
        //toolbar.setOverflowIcon(ContextCompat.getDrawable(this, R.drawable.ic_black_hamburger));
        //menu = (Menu) findViewById(R.menu.option_menu);
        /*if (android.os.Build.VERSION.SDK_INT >= 21)
            toolbar.setOverflowIcon(getResources().getDrawable(R.drawable.ic_black_hamburger, getBaseContext().getTheme()));
        else
            toolbar.setOverflowIcon(getResources().getDrawable(R.drawable.ic_black_hamburger));*/
        //toolbar.setTitle("");
        //getSupportActionBar().setDisplayShowTitleEnabled(false);
        //BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        //navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        elements = new ArrayList<>();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        rvNewsletter.setLayoutManager(mLayoutManager);
        rvNewsletter.setItemAnimator(new DefaultItemAnimator());
        Log.d("dddd", "id_newsletter " + String.valueOf(Attributes.getId_newsletter()));
        Log.d("dddd", "login_token " + Attributes.getLogin_token());
        if (Attributes.getId_newsletter() != -1)
            getNewsletter( Attributes.getId_newsletter());
        else
            Log.d("dddd", "błąd");
        String s = "";
        for (Element element : elements)
            s += element.toString() + '\n';
        Log.d("dd", s);
        return v;
    }



    private void getNewsletter(int id_newsletter) {
        try {
            Log.d("dddd", String.valueOf(id_newsletter));
            //Log.d("dddd", String.valueOf(A));
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            Timestamp token_time = Timestamp.valueOf(Attributes.getLogin_token_exp());
   //    if (timestamp.after(token_time))
        //{
                Attributes.refreshtoken();
     //   }
   //      else {
                BeinsuredClient beinsuredClient = ServiceGenerator.createServiceAuthtoken(BeinsuredClient.class, "beinsured","beinsu12");
                Call<NewsletterContent> call = beinsuredClient.getNewsletter(id_newsletter);
                call.enqueue(new Callback<NewsletterContent>() {
                    @Override
                    public void onResponse(Call<NewsletterContent> call, Response<NewsletterContent> response) {
                        NewsletterContent newsletterContent = response.body();
                        if (newsletterContent!=null)
                        {Log.d("ddd", newsletterContent.toString());}
                        if (response.isSuccessful()) {
                            try {
                                elements = newsletterContent.getData().getZawartosc();
                                adapter = new NewsletterAdapter(elements);
                                rvNewsletter.setAdapter(adapter);
                                adapter.notifyDataSetChanged();
                                int i = 0;
                                while (i < elements.size()) {
                                    if (elements.get(i).getTyp() != 0 && elements.get(i).getKotwica() != 0 && elements.get(i).getTyp() != 3) {
                                        menu.add(elements.get(i).getTytul());
                                    }

                                    i++;
                                }

                            } catch (Exception ex) {
                                Log.d("ddd", ex.getLocalizedMessage());
                            }
                        } else
                            Log.d("else", "Błąd");
                    }

                    @Override
                    public void onFailure(Call<NewsletterContent> call, Throwable t) {
                        Log.d("onFailure", t.getLocalizedMessage());
                    }
                });
         //   }
        } catch (Exception ex) {
            Log.d("try", ex.getLocalizedMessage());
        }
    }

}