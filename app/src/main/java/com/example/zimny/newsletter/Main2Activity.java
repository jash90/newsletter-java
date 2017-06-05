package com.example.zimny.newsletter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


import cz.msebera.android.httpclient.Header;

public class Main2Activity extends AppCompatActivity {


    private List<Newsletter> newsletters;
    private Integer pages;
    private RecyclerView rvNewsletter;
    private NewsletterAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent intent = getIntent();
        String login_token = intent.getStringExtra("login_token");
        rvNewsletter= (RecyclerView) findViewById(R.id.newsletterRecycler);
        newsletters = new ArrayList<>();

        adapter = new NewsletterAdapter(newsletters);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rvNewsletter.setLayoutManager(mLayoutManager);
        rvNewsletter.setItemAnimator(new DefaultItemAnimator());
        rvNewsletter.setAdapter(adapter);
        getnewsletter(login_token);

    }

    public void getnewsletter(String login_token) {
        RequestParams requestParams = new RequestParams();
        requestParams.add("apiKey", "2esde2#derdsr#RD");
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("Authtoken", login_token);
        client.get("http://www.beinsured.t.test.ideo.pl/api/v1/1/pl/DefaultProfil/getListaNewsleter?", requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {

                    JSONObject jsonObject = new JSONObject(new String(responseBody));

                    String message = jsonObject.getString("status");
                    //
                   // text.setText(new String(responseBody));
                   // text.setText("");
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    ArrayList<Newsletter> newsletterArrayList = new ArrayList<Newsletter>();
                    for (int i=0;i<jsonArray.length();i++)
                    {
                        Newsletter n = new Newsletter();
                        n.setId(jsonArray.getJSONObject(i).getInt("id"));
                        n.setName(jsonArray.getJSONObject(i).getString("tytul"));
                        n.setDate_send(Timestamp.valueOf(jsonArray.getJSONObject(i).getString("data_wyslania")));
                        n.setTime_send(Time.valueOf(jsonArray.getJSONObject(i).getString("czas_wyslania")+":00"));
                        Log.d("news",n.toString());
                        newsletters.add(n);

                    }
                    //
                    pages = Integer.parseInt(jsonObject.getString("pages"));
                    //
                    //text.setText(pages);
                    adapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    Log.d("Error", e.getLocalizedMessage());
                }


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                try {
                    JSONObject jsonObject = new JSONObject(new String(responseBody));
                } catch (JSONException e) {
                    Log.d("Error", e.getLocalizedMessage());

                    Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }

            }

        });
        adapter.notifyDataSetChanged();
    }

}