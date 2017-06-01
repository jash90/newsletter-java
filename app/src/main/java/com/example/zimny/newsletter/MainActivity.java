package com.example.zimny.newsletter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.concurrent.ExecutionException;

import com.loopj.android.http.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;


public class MainActivity extends AppCompatActivity {


    private User user;
    private EditText loginEditText;
    private EditText passwordEditText;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:

                    return true;
                case R.id.navigation_dashboard:

                    return true;
                case R.id.navigation_notifications:

                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginEditText = (EditText) findViewById(R.id.login);
        passwordEditText = (EditText) findViewById(R.id.password);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
    public void login()
    {
        RequestParams requestParams = new RequestParams();
        requestParams.add("apiKey", "2esde2#derdsr#RD");
        requestParams.add("login", "t.chrobak");
        requestParams.add("password", "1234qwer");
        AsyncHttpClient client = new AsyncHttpClient();
        client.setBasicAuth("beinsured","beinsu12");
        client.post("http://www.beinsured.t.test.ideo.pl/api/v1/1/pl/RestAuth/signIn/", requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    user = new User();
                    JSONObject jsonObject = new JSONObject(new String(responseBody));
                    user.setLogin(jsonObject.getString("login"));
                    user.setFull_name(jsonObject.getString("full_name"));
                    user.setEmail(jsonObject.getString("email"));
                    user.setLogin_token(jsonObject.getString("login_token"));
                    user.setRefresh_token(jsonObject.getString("refresh_token"));
                    user.setLogin_token_exp(Timestamp.valueOf(jsonObject.getString("login_token_exp")));
                    user.setRefresh_token_exp(Timestamp.valueOf(jsonObject.getString("refresh_token_exp")));
//                    mTextMessage.setText(user.toString());
                    String message = jsonObject.getString("message");
                    Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    Log.d("Error",e.getLocalizedMessage());
                }


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }

        });



    }

    public void Zaloguj(View view) {
        RequestParams requestParams = new RequestParams();
        requestParams.add("apiKey", "2esde2#derdsr#RD");
        requestParams.add("login", loginEditText.getText().toString());
        requestParams.add("password", passwordEditText.getText().toString());
        AsyncHttpClient client = new AsyncHttpClient();
        client.setBasicAuth("beinsured","beinsu12");
        client.post("http://www.beinsured.t.test.ideo.pl/api/v1/1/pl/RestAuth/signIn/", requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    user = new User();
                    JSONObject jsonObject = new JSONObject(new String(responseBody));
                    user.setLogin(jsonObject.getString("login"));
                    user.setFull_name(jsonObject.getString("full_name"));
                    user.setEmail(jsonObject.getString("email"));
                    user.setLogin_token(jsonObject.getString("login_token"));
                    user.setRefresh_token(jsonObject.getString("refresh_token"));
                    user.setLogin_token_exp(Timestamp.valueOf(jsonObject.getString("login_token_exp")));
                    user.setRefresh_token_exp(Timestamp.valueOf(jsonObject.getString("refresh_token_exp")));
                  //  mTextMessage.setText(user.toString());
                    String message = jsonObject.getString("message");
                    Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    Log.d("Error",e.getLocalizedMessage());
                }


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }

        });

    }
}
