package com.example.zimny.newsletter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.text.method.LinkMovementMethod;
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
import org.w3c.dom.Text;

import cz.msebera.android.httpclient.Header;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class MainActivity extends AppCompatActivity {


    private User user;
    private EditText loginEditText;
    private EditText passwordEditText;
    private TextView linkTextView;
    private TextView text;
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
        setContentView(R.layout.activity_main);
        loginEditText = (EditText) findViewById(R.id.login);
        passwordEditText = (EditText) findViewById(R.id.password);
        linkTextView = (TextView) findViewById(R.id.link);
        linkTextView.setMovementMethod(LinkMovementMethod.getInstance());
        text = (TextView) findViewById(R.id.textView) ;
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        login();
      //  getnewsletter(user.getLogin_token());
    }
    public void login()
    {
        OkHttpClient.Builder okbuilder = new OkHttpClient.Builder();

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://www.beinsured.t.test.ideo.pl/api/v1/1/pl/RestAuth")
                .client(okbuilder.build())
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        BeinsuredClient beinsuredClient = retrofit.create(BeinsuredClient.class);
        Call<User> call = beinsuredClient.login(new Guest("t.chrobak","1234qwer"));
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });





    }
    public void getnewsletter(String login_token)
    {
        RequestParams requestParams = new RequestParams();
        requestParams.add("apiKey", "2esde2#derdsr#RD");
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("Authorization",login_token);
        client.get("http://www.beinsured.t.test.ideo.pl/api/v1/1/pl/DefaultProfil/getListaNewsleter", requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {

                    JSONObject jsonObject = new JSONObject(new String(responseBody));

                    String message = jsonObject.getString("status");
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
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
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
                    Intent intent = new Intent(getBaseContext(),Main2Activity.class);
                    intent.putExtra("login_token",user.getLogin_token());
                    startActivity(intent);
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
