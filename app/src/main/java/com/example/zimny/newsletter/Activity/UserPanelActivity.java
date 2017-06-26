package com.example.zimny.newsletter.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.zimny.newsletter.Api.BeinsuredClient;
import com.example.zimny.newsletter.Api.ServiceGenerator;
import com.example.zimny.newsletter.Model.Pakiet;
import com.example.zimny.newsletter.R;

import java.sql.Timestamp;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class UserPanelActivity extends AppCompatActivity {

    private TextView typ, okres, wazny,ilosc_dostepow,wykorzystano;
    private LinearLayout ilosc, wykorzy;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_myaccount:

                    return true;
                case R.id.navigation_newsletter:
                    Intent newsletters = new Intent(UserPanelActivity.this,ListNewslettersActivity.class);
                    startActivity(newsletters);
                    return true;
                case R.id.navigation_logout:
                    Intent intent = new Intent(UserPanelActivity.this,MainActivity.class);
                    intent.putExtra("logout","logout");
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
        setContentView(R.layout.activity_main4);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //toolbar.setNavigationIcon(R.drawable.icon_beinsured);
      //  toolbar.setLogo(R.drawable.icon_menu);
        toolbar.setTitle("");
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        typ = (TextView)findViewById(R.id.typ);
        okres = (TextView) findViewById(R.id.okres);
        wazny = (TextView) findViewById(R.id.wazny_abonament);
        ilosc_dostepow = (TextView) findViewById(R.id.ilosc_dostepow);
        wykorzystano = (TextView)findViewById(R.id.wykorzystano);
        ilosc = (LinearLayout)findViewById(R.id.ilosc_layout);
        wykorzy = (LinearLayout)findViewById(R.id.wykorzy_layout);
        getPakiet();

    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
    public void getPakiet() {
        try {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            Timestamp token_time = Timestamp.valueOf(Attributes.getLogin_token_exp());
            if (timestamp.after(token_time))
            {
                Attributes.refreshtoken();
            }
            else {
            BeinsuredClient beinsuredClient = ServiceGenerator.createService(BeinsuredClient.class,Attributes.getLogin_token());
            Call<Pakiet> call = beinsuredClient.getPakiet();
            call.enqueue(new Callback<Pakiet>() {
                @Override
                public void onResponse(Call<Pakiet> call, Response<Pakiet> response) {

                    Pakiet pakiet = response.body();
                    typ.setText(" "+pakiet.getTyp_abonamentu());
                    okres.setText(" "+pakiet.getOkres());
                    wazny.setText(" "+pakiet.getData_konca());
                    if (pakiet.equals("Firmowy")) {
                        ilosc_dostepow.setText(String.valueOf(" "+pakiet.getMax_ilosc_dostepow()));
                        wykorzystano.setText(String.valueOf(" "+pakiet.getWykorzystano_ilosc_dostepow()));
                    }else {
                        ilosc.setVisibility(View.GONE);
                        wykorzy.setVisibility(View.GONE);
                    }//    Toast.makeText(getBaseContext(),response.body().toString(),Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<Pakiet> call, Throwable t) {
                   Log.d("error ",t.getLocalizedMessage());
                }
            });
        }}
        catch (Exception ex)
        {
            Log.d("error",ex.getLocalizedMessage());
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return true;
    }

}