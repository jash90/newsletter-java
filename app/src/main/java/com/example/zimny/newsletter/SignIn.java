package com.example.zimny.newsletter;

import android.os.AsyncTask;
import android.util.Log;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by ideo7 on 01.06.2017.
 */

public class SignIn extends AsyncTask<Void,Void,Void> {


    @Override
    protected Void doInBackground(Void... params) {
        RequestParams requestParams = new RequestParams();
        requestParams.add("apiKey", "2esde2#derdsr#RD");
        requestParams.add("login", "t.chrobak");
        requestParams.add("password", "1234qwer");
        BeinsuredRestClient.post("signIn/", requestParams, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray
                try {


                    Log.d("ok", response.get("status").toString());
                } catch (Exception ex) {
                    Log.d("error", ex.getLocalizedMessage());
                }


            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                try {


                    Log.d("ok", response.get(0).toString());
                } catch (Exception ex) {
                    Log.d("error", ex.getLocalizedMessage());
                }

            }

            public void onFailure(int statusCode, Header[] headers, Throwable t, JSONObject e) {
                // Handle the failure and alert the user to retry
                Log.e("ERROR", e.toString());
            }

        });
        return null;
    }
}
