package com.example.zimny.newsletter.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import com.example.zimny.newsletter.Api.BeinsuredClient;
import com.example.zimny.newsletter.Api.ServiceGenerator;
import com.example.zimny.newsletter.R;
import com.example.zimny.newsletter.Model.User;
import com.facebook.stetho.Stetho;

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
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor sharedPreferencesEditor;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_myaccount:
                    Toast.makeText(getBaseContext(),"Najpierw musisz się zalogować",Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.navigation_newsletter:
                        Toast.makeText(getBaseContext(),"Najpierw musisz się zalogować",Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.navigation_logout:
                    Toast.makeText(getBaseContext(),"Najpierw musisz się zalogować",Toast.LENGTH_SHORT).show();
                    return true;
            }
            return false;
        }

    };
    private String PREFS_NAME = "NAME";

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
        sharedPreferences = this.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        sharedPreferencesEditor = sharedPreferences.edit();
        Intent intent = getIntent();
        if (intent.getStringExtra("logout")!=null)
        {
        if (intent.getStringExtra("logout").equals("logout"))
        {
            sharedPreferencesEditor.putString("pass","");
            sharedPreferencesEditor.commit();
        }}
        if (!sharedPreferences.getString("login","").isEmpty())
        {
            loginEditText.setText(sharedPreferences.getString("login",""));
        }
        if (!sharedPreferences.getString("pass","").isEmpty())
        {
            passwordEditText.setText(sharedPreferences.getString("pass",""));
        }
        if (!loginEditText.getText().toString().isEmpty() && !passwordEditText.getText().toString().isEmpty())
        {
            Zaloguj(null);
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
    public void Zaloguj(View view) {

        try {
            BeinsuredClient beinsuredClient = ServiceGenerator.createService(BeinsuredClient.class,"beinsured","beinsu12");
            Call<User> call = beinsuredClient.login(loginEditText.getText().toString(), passwordEditText.getText().toString(),"2esde2#derdsr#RD");
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    user = response.body();
                    if (user!=null && user.getStatus().equals("0")) {
                        Log.d("dddd",user.toString());
                        Toast.makeText(MainActivity.this, user.getMessage(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, ListNewslettersActivity.class);
                        Attributes.setLogin_token(user.getLogin_token());
                        Attributes.setLogin_token_exp(user.getLogin_token_exp());
                        Attributes.setRefresh_token(user.getRefresh_token());
                        Attributes.setRefresh_token_exp(user.getRefresh_token_exp());
                        Attributes.setLogin(user.getLogin());
                        Attributes.setPass(passwordEditText.getText().toString());
                        sharedPreferencesEditor.putString("login", loginEditText.getText().toString());
                        sharedPreferencesEditor.putString("pass", passwordEditText.getText().toString());
                        sharedPreferencesEditor.commit(); //
                        startActivity(intent);
                    }
                    else
                        Log.d("dddd","NULL");
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
