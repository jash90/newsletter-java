package com.example.zimny.newsletter.Activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zimny.newsletter.Api.BeinsuredClient;
import com.example.zimny.newsletter.Api.ServiceGenerator;
import com.example.zimny.newsletter.Fragment.ListNewslettersFragment;
import com.example.zimny.newsletter.Fragment.LoginFragment;
import com.example.zimny.newsletter.Fragment.UserPanelFragment;
import com.example.zimny.newsletter.R;
import com.example.zimny.newsletter.Model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class MainActivity extends AppCompatActivity {


    protected User user;
    private EditText loginEditText;
    private EditText passwordEditText;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor sharedPreferencesEditor;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            if (!Attributes.getLogin().isEmpty() && !Attributes.getPass().isEmpty())
            {
            switch (item.getItemId()) {
                case R.id.navigation_myaccount:
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    UserPanelFragment userPanelFragment = new UserPanelFragment();
                    fragmentTransaction.replace(R.id.content, userPanelFragment);
                    fragmentTransaction.addToBackStack("Fragment");
                    fragmentTransaction.commit();
                    return true;
                case R.id.navigation_newsletter:
                    FragmentManager fragmentManager2 = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction2 = fragmentManager2.beginTransaction();
                    ListNewslettersFragment listNewslettersFragment = new ListNewslettersFragment();
                    fragmentTransaction2.replace(R.id.content, listNewslettersFragment);
                    fragmentTransaction2.addToBackStack("Fragment");
                    fragmentTransaction2.commit();
                    return true;
                case R.id.navigation_logout:
                    sharedPreferencesEditor.putString("pass","");
                    sharedPreferencesEditor.commit();
                    FragmentManager fragmentManager3 = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction3 = fragmentManager3.beginTransaction();
                    LoginFragment loginFragment = new LoginFragment();
                    fragmentTransaction3.replace(R.id.content, loginFragment);
                    fragmentTransaction3.addToBackStack("Fragment");
                    fragmentTransaction3.commit();
                    return true;
            }

            }
            else
            {
                Toast.makeText(getBaseContext(), "Najpierw musisz się zalogować", Toast.LENGTH_SHORT).show();
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
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        LoginFragment loginFragment = new LoginFragment();
        fragmentTransaction.add(R.id.content, loginFragment);
        fragmentTransaction.addToBackStack("Fragment");
        fragmentTransaction.commit();
        sharedPreferences = this.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        sharedPreferencesEditor = sharedPreferences.edit();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
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
                        //Toast.makeText(, user.getMessage(), Toast.LENGTH_SHORT).show();
                        //Intent intent = new Intent(MainActivity.this, ListNewslettersFragment.class);
                        Attributes.setLogin_token(user.getLogin_token());
                        Attributes.setLogin_token_exp(user.getLogin_token_exp());
                        Attributes.setRefresh_token(user.getRefresh_token());
                        Attributes.setRefresh_token_exp(user.getRefresh_token_exp());
                        Attributes.setLogin(user.getLogin());
                        Attributes.setPass(passwordEditText.getText().toString());
                        sharedPreferencesEditor.putString("login", loginEditText.getText().toString());
                        sharedPreferencesEditor.putString("pass", passwordEditText.getText().toString());
                        sharedPreferencesEditor.commit(); //
                        //startActivity(intent);
                        FragmentManager fragmentManager =getSupportFragmentManager();
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
