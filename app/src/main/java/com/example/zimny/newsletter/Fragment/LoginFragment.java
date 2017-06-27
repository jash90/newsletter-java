package com.example.zimny.newsletter.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import com.example.zimny.newsletter.Activity.Attributes;
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


public class LoginFragment extends Fragment {


    protected User user;
    private EditText loginEditText;
    private EditText passwordEditText;
    private TextView linkTextView;
    private Button zalogujButton;
    private String PREFS_NAME = "NAME";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor sharedPreferencesEditor;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_main1, container,false);
        loginEditText = (EditText) v.findViewById(R.id.login);
        passwordEditText = (EditText) v.findViewById(R.id.password);
        linkTextView = (TextView) v.findViewById(R.id.link);
        linkTextView.setMovementMethod(LinkMovementMethod.getInstance());
        zalogujButton = (Button) v.findViewById(R.id.zaloguj);
        zalogujButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Zaloguj(v);
            }
        });
        sharedPreferences = getContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        sharedPreferencesEditor = sharedPreferences.edit();
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
        return v;
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
                        //Toast.makeText(, user.getMessage(), Toast.LENGTH_SHORT).show();
                        //Intent intent = new Intent(MainActivity.this, ListNewslettersFragment.class);
                        Attributes.setLogin_token(user.getLogin_token());
                        Attributes.setLogin_token_exp(user.getLogin_token_exp());
                        Attributes.setRefresh_token(user.getRefresh_token());
                        Attributes.setRefresh_token_exp(user.getRefresh_token_exp());
                        Attributes.setLogin(user.getLogin());
                        Attributes.setPass(passwordEditText.getText().toString());
                        SharedPreferences.Editor sharedPreferencesEditor =getContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).edit();
                        sharedPreferencesEditor.putString("login", loginEditText.getText().toString());
                        sharedPreferencesEditor.putString("pass", passwordEditText.getText().toString());
                        sharedPreferencesEditor.commit(); //
                        //startActivity(intent);
                        FragmentManager fragmentManager =getFragmentManager();
                        FragmentTransaction fragmentTransaction =fragmentManager.beginTransaction();
                        ListNewslettersFragment listNewslettersFragment = new ListNewslettersFragment();
                        fragmentTransaction.replace(R.id.content, listNewslettersFragment);
                        fragmentTransaction.addToBackStack("Fragment");
                        fragmentTransaction.commit();
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
