package com.example.zimny.newsletter.Fragment;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zimny.newsletter.Activity.Attributes;
import com.example.zimny.newsletter.Activity.NewslettersAdapter;
import com.example.zimny.newsletter.Api.ServiceGenerator;
import com.example.zimny.newsletter.Model.Newsletter;
import com.example.zimny.newsletter.Api.BeinsuredClient;
import com.example.zimny.newsletter.Model.Newsletters;
import com.example.zimny.newsletter.R;

import java.sql.Timestamp;
import java.util.ArrayList;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListNewslettersFragment extends Fragment {


    private ArrayList<Newsletter> newsletterArrayList;
    private String status;
    private Integer pages;
    private RecyclerView rvNewsletter;
    private NewslettersAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_main2, container,false);
        rvNewsletter= (RecyclerView) v.findViewById(R.id.newslettersRecycler);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        //   toolbar.setNavigationIcon(R.drawable.icon_beinsured);
        //toolbar.setTitle("");
        //getSupportActionBar().setDisplayShowTitleEnabled(false);
        newsletterArrayList = new ArrayList<>();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        rvNewsletter.setLayoutManager(mLayoutManager);
        rvNewsletter.setItemAnimator(new DefaultItemAnimator());
        getListNewsletter();
        return v;
    }
    public void getListNewsletter() {
        try {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            Timestamp token_time = Timestamp.valueOf(Attributes.getLogin_token_exp());
            if (timestamp.after(token_time))
            {
                Attributes.refreshtoken();
            }
            else {
                BeinsuredClient beinsuredClient = ServiceGenerator.createServiceAuthtoken(BeinsuredClient.class, "beinsured","beinsu12");
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
                            adapter = new NewslettersAdapter(newsletterArrayList);
                            rvNewsletter.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                        }
                        else
                            Log.d("error", "error");
                    }

                    @Override
                    public void onFailure(Call<Newsletters> call, Throwable t) {
                        Log.d("error", t.getLocalizedMessage());
                    }
                });
            }
        }
        catch (Exception ex)
        {
            Log.d("error",ex.getLocalizedMessage());
        }
       // adapter.notifyDataSetChanged();
    }


}