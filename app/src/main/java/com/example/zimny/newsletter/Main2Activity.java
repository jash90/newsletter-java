package com.example.zimny.newsletter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


import cz.msebera.android.httpclient.Header;

public class Main2Activity extends AppCompatActivity {

    private TextView text;
    private ArrayList<Newsletter> newsletters;
    private Integer pages;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        text =(TextView)findViewById(R.id.textViewTest);
        newsletters = new ArrayList<>();
        Intent intent = getIntent();
        String login_token = intent.getStringExtra("login_token");
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
                    text.setText(new String(responseBody));
                    text.setText("");
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    for (int i=0;i<jsonArray.length();i++)
                    {
                        Newsletter n = new Newsletter();
                        n.setId(jsonArray.getJSONObject(i).getInt("id"));
                        n.setName(jsonArray.getJSONObject(i).getString("tytul"));
                        n.setDate_send(Timestamp.valueOf(jsonArray.getJSONObject(i).getString("data_wyslania")));
                        n.setTime_send(Time.valueOf(jsonArray.getJSONObject(i).getString("czas_wyslania")+":00"));

                        newsletters.add(n);
                    }
                    pages = Integer.parseInt(jsonObject.getString("pages"));
                    //text.setText(pages);


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
    }
    public void getnewsletters(String login_token) {

    }
}