package com.example.zimny.newsletter.Activity;

import android.content.Context;
import android.content.Intent;
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

import com.example.zimny.newsletter.Retrofit.BeinsuredClient;
import com.example.zimny.newsletter.R;
import com.example.zimny.newsletter.Class.User;

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


public class MainActivity extends AppCompatActivity {


    protected User user;
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


    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
    public void Zaloguj(View view) {

        try {
            OkHttpClient.Builder okbuilder = new OkHttpClient.Builder();
            okbuilder.addInterceptor(new Interceptor() {
                @Override
                public okhttp3.Response intercept(Chain chain) throws IOException {
                    Request request = chain.request();
                    Request.Builder newrequest = request.newBuilder();
                    return chain.proceed(newrequest.build());
                }
            });

            Retrofit.Builder builder = new Retrofit.Builder()
                    .baseUrl("http://www.beinsured.t.test.ideo.pl/api/v1/1/pl/")
                    .client(okbuilder.build())
                    .addConverterFactory(GsonConverterFactory.create());
            Retrofit retrofit = builder.build();
            BeinsuredClient beinsuredClient = retrofit.create(BeinsuredClient.class);
            Call<User> call = beinsuredClient.login(loginEditText.getText().toString(), passwordEditText.getText().toString(),"2esde2#derdsr#RD");
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    user = response.body();
                    Log.d("dddd",user.toString());
                    if (user!=null) {
                        Toast.makeText(MainActivity.this, user.toString(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                        intent.putExtra("login_token", user.getLogin_token());
                        startActivity(intent);
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Log.d("error",t.getLocalizedMessage());
                }
            });


        }
        catch (Exception ex)
        {
            Log.d("error",ex.getLocalizedMessage());
        }

    }
}
