package com.example.zimny.newsletter.Fragment;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.zimny.newsletter.Activity.Attributes;
import com.example.zimny.newsletter.Api.BeinsuredClient;
import com.example.zimny.newsletter.Api.ServiceGenerator;
import com.example.zimny.newsletter.Fragment.ListNewslettersFragment;
import com.example.zimny.newsletter.Model.Pakiet;
import com.example.zimny.newsletter.R;

import java.sql.Timestamp;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class UserPanelFragment extends Fragment {

    private TextView typ, okres, wazny,ilosc_dostepow,wykorzystano;
    private LinearLayout ilosc, wykorzy;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_main4, container,false);
        //toolbar.setNavigationIcon(R.drawable.icon_beinsured);
        //  toolbar.setLogo(R.drawable.icon_menu);
        //toolbar.setTitle("");
        //getSupportActionBar().setDisplayShowTitleEnabled(false);
        //BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        //navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        typ = (TextView) v.findViewById(R.id.typ);
        okres = (TextView) v.findViewById(R.id.okres);
        wazny = (TextView) v.findViewById(R.id.wazny_abonament);
        ilosc_dostepow = (TextView) v.findViewById(R.id.ilosc_dostepow);
        wykorzystano = (TextView) v.findViewById(R.id.wykorzystano);
        ilosc = (LinearLayout) v.findViewById(R.id.ilosc_layout);
        wykorzy = (LinearLayout) v.findViewById(R.id.wykorzy_layout);
        getPakiet();
        return v;
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
            BeinsuredClient beinsuredClient = ServiceGenerator.createServiceAuthtoken(BeinsuredClient.class,"beinsured","beinsu12");
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


}