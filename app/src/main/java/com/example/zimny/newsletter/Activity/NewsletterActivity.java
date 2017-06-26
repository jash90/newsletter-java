package com.example.zimny.newsletter.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageButton;

import com.example.zimny.newsletter.Api.ServiceGenerator;
import com.example.zimny.newsletter.Model.Element;
import com.example.zimny.newsletter.Model.NewsletterContent;
import com.example.zimny.newsletter.Api.BeinsuredClient;
import com.example.zimny.newsletter.Model.Pozycja;
import com.example.zimny.newsletter.Model.Tresc;
import com.example.zimny.newsletter.R;

import java.sql.Timestamp;
import java.util.ArrayList;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class NewsletterActivity extends AppCompatActivity {


    private ArrayList<Element> elements;
    private RecyclerView rvNewsletter;
    private NewsletterAdapter adapter;
    private int id_newsletter;
    private Menu menu;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_myaccount:
                    Intent panel = new Intent(NewsletterActivity.this, UserPanelActivity.class);;
                    startActivity(panel);
                    return true;
                case R.id.navigation_newsletter:
                    Intent newsletters = new Intent(NewsletterActivity.this, ListNewslettersActivity.class);
                    startActivity(newsletters);
                    return true;
                case R.id.navigation_logout:
                    Intent logout = new Intent(NewsletterActivity.this, MainActivity.class);
                    logout.putExtra("logout","logout");
                    startActivity(logout);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/OpenSans-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
        setContentView(R.layout.activity_main3);
        Intent intent = getIntent();
        id_newsletter = intent.getIntExtra("id_newsletter", -1);
        rvNewsletter = (RecyclerView) findViewById(R.id.newsletterRecycler);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
      //  toolbar.setNavigationIcon(R.drawable.icon_beinsured);
    //    menu.findItem(Menu.FIRST).setIcon(R.drawable.ic_black_hamburger);
        //toolbar.setOverflowIcon(ContextCompat.getDrawable(this, R.drawable.ic_black_hamburger));
        //menu = (Menu) findViewById(R.menu.option_menu);
        /*if (android.os.Build.VERSION.SDK_INT >= 21)
            toolbar.setOverflowIcon(getResources().getDrawable(R.drawable.ic_black_hamburger, getBaseContext().getTheme()));
        else
            toolbar.setOverflowIcon(getResources().getDrawable(R.drawable.ic_black_hamburger));*/
        toolbar.setTitle("");
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        elements = new ArrayList<>();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rvNewsletter.setLayoutManager(mLayoutManager);
        rvNewsletter.setItemAnimator(new DefaultItemAnimator());
        Log.d("dddd", "id_newsletter " + String.valueOf(id_newsletter));
        Log.d("dddd", "login_token " + Attributes.getLogin_token());
        if (id_newsletter != -1)
            getNewsletter( id_newsletter);
        else
            Log.d("dddd", "błąd");
        String s = "";
        for (Element element : elements)
            s += element.toString() + '\n';
        Log.d("dd", s);
    }

    private void getNewsletter(int id_newsletter) {
        try {
            Log.d("dddd", String.valueOf(id_newsletter));
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            Timestamp token_time = Timestamp.valueOf(Attributes.getLogin_token_exp());
            if (timestamp.after(token_time))
            {
                Attributes.refreshtoken();
            }
            else {
                BeinsuredClient beinsuredClient = ServiceGenerator.createService(BeinsuredClient.class, Attributes.getLogin_token());
                Call<NewsletterContent> call = beinsuredClient.getNewsletter(id_newsletter);
                call.enqueue(new Callback<NewsletterContent>() {
                    @Override
                    public void onResponse(Call<NewsletterContent> call, Response<NewsletterContent> response) {
                        NewsletterContent newsletterContent = response.body();
                        Log.d("ddd", newsletterContent.toString());
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
            }
        } catch (Exception ex) {
            Log.d("try", ex.getLocalizedMessage());
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
      super.onCreateOptionsMenu(menu);
    //  getMenuInflater().inflate(R.menu.option_menu, menu);
      this.menu = menu;

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        for (int i = 0; i < elements.size(); i++)
            if (item.getTitle() == elements.get(i).getTytul()) {
                rvNewsletter.scrollToPosition(i);
                return true;
            }
        return super.onOptionsItemSelected(item);

    }
}