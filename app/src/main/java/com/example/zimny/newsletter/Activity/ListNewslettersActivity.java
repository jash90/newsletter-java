package com.example.zimny.newsletter.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.example.zimny.newsletter.Api.ServiceGenerator;
import com.example.zimny.newsletter.Model.Newsletter;
import com.example.zimny.newsletter.Api.BeinsuredClient;
import com.example.zimny.newsletter.Model.Newsletters;
import com.example.zimny.newsletter.R;

import java.util.ArrayList;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ListNewslettersActivity extends AppCompatActivity {


    private ArrayList<Newsletter> newsletterArrayList;
    private String status;
    private Integer pages;
    private RecyclerView rvNewsletter;
    private NewslettersAdapter adapter;
    private String login_token;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_myaccount:
                    Intent panel = new Intent(ListNewslettersActivity.this,UserPanelActivity.class);
                    panel.putExtra("login_token",login_token);
                    startActivity(panel);
                    return true;
                case R.id.navigation_newsletter:

                    return true;
                case R.id.navigation_logout:
                        Intent intent = new Intent(ListNewslettersActivity.this,MainActivity.class);
                        startActivity(intent);
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
        setContentView(R.layout.activity_main2);
        Intent intent = getIntent();
        login_token = intent.getStringExtra("login_token");
        rvNewsletter= (RecyclerView) findViewById(R.id.newslettersRecycler);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.icon_beinsured);
        toolbar.setLogo(R.drawable.icon_menu);
        toolbar.setTitle("");
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        newsletterArrayList = new ArrayList<>();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rvNewsletter.setLayoutManager(mLayoutManager);
        rvNewsletter.setItemAnimator(new DefaultItemAnimator());
        if (android.os.Build.VERSION.SDK_INT >= 21)
            toolbar.setOverflowIcon(getResources().getDrawable(R.drawable.ic_black_hamburger, getBaseContext().getTheme()));
        else
            toolbar.setOverflowIcon(getResources().getDrawable(R.drawable.ic_black_hamburger));
        getListNewsletter(login_token);
        navigation.setSelectedItemId(R.id.navigation_newsletter);
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
    public void getListNewsletter(final String login_token) {
        try {
            Log.d("dddd",login_token);

            BeinsuredClient beinsuredClient = ServiceGenerator.createService(BeinsuredClient.class,login_token);
            Call<Newsletters> call = beinsuredClient.getListNewsletter();
            call.enqueue(new Callback<Newsletters>() {
                @Override
                public void onResponse(Call<Newsletters> call, Response<Newsletters> response) {
                    if (response.isSuccessful()) {
                        Newsletters newsletters = response.body();
                        newsletterArrayList = newsletters.getData();
                        pages = newsletters.getPages();
                        status = newsletters.getStatus();
                        Log.d("ddd", newsletterArrayList.toString());
                        adapter = new NewslettersAdapter(newsletterArrayList, login_token);
                        rvNewsletter.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onFailure(Call<Newsletters> call, Throwable t) {
                    Log.d("error",t.getLocalizedMessage());
                }
            });
        }
        catch (Exception ex)
        {
            Log.d("error",ex.getLocalizedMessage());
        }
       // adapter.notifyDataSetChanged();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return true;
    }
}