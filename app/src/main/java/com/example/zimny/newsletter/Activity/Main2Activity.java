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
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.zimny.newsletter.Class.Newsletter;
import com.example.zimny.newsletter.Retrofit.BeinsuredClient;
import com.example.zimny.newsletter.Class.Newsletters;
import com.example.zimny.newsletter.R;

import java.io.IOException;
import java.util.ArrayList;


import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Main2Activity extends AppCompatActivity {


    private ArrayList<Newsletter> newsletterArrayList;
    private String status;
    private Integer pages;
    private RecyclerView rvNewsletter;
    private NewslettersAdapter adapter;
    private ImageButton imageButton;
    private static String login_token;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_myaccount:

                    return true;
                case R.id.navigation_newsletter:

                    return true;
                case R.id.navigation_logout:

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
        imageButton = (ImageButton)findViewById(R.id.buttonImage);
        imageButton.setColorFilter(R.color.black);
        getListNewsletter(login_token);
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
    public void getListNewsletter(final String login_token) {
        try {
            Log.d("dddd",login_token);
            OkHttpClient.Builder okbuilder = new OkHttpClient.Builder();
            okbuilder.addInterceptor(new Interceptor() {
                @Override
                public okhttp3.Response intercept(Chain chain) throws IOException {
                    Request request = chain.request();
                    Request.Builder newrequest = request.newBuilder().addHeader("Authtoken",login_token);
                    return chain.proceed(newrequest.build());
                }
            });

            Retrofit.Builder builder = new Retrofit.Builder()
                    .baseUrl("http://www.beinsured.t.test.ideo.pl/api/v1/1/pl/")
                    .client(okbuilder.build())
                    .addConverterFactory(GsonConverterFactory.create());
            Retrofit retrofit = builder.build();
            BeinsuredClient beinsuredClient = retrofit.create(BeinsuredClient.class);
            Call<Newsletters> call = beinsuredClient.getListNewsletter();
            call.enqueue(new Callback<Newsletters>() {
                @Override
                public void onResponse(Call<Newsletters> call, Response<Newsletters> response) {

                    Newsletters newsletters = response.body();
                    newsletterArrayList= newsletters.getData();
                    pages=newsletters.getPages();
                    status =newsletters.getStatus();
                    Log.d("ddd",newsletterArrayList.toString());
                    adapter = new NewslettersAdapter(newsletterArrayList);
                    rvNewsletter.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
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

    public void Click(View view) {
        Toast.makeText(getApplicationContext(),"Click",Toast.LENGTH_SHORT).show();
    }

    public static String getLogin_token() {
        return login_token;
    }

    public static void setLogin_token(String login_token) {
        Main2Activity.login_token = login_token;
    }
}