package com.example.zimny.newsletter.Activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zimny.newsletter.Api.BeinsuredClient;
import com.example.zimny.newsletter.Api.ServiceGenerator;
import com.example.zimny.newsletter.Fragment.LoginFragment;
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
    private TextView linkTextView;
    private TextView text;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor sharedPreferencesEditor;
    private FragmentManager fragmentManager;
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
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        LoginFragment loginFragment = new LoginFragment();
        fragmentTransaction.add(R.id.content, loginFragment);
        fragmentTransaction.addToBackStack("loginFragment");
        fragmentTransaction.commit();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
//        sharedPreferences = this.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
//        sharedPreferencesEditor = sharedPreferences.edit();
//        Intent intent = getIntent();
//        if (intent.getStringExtra("logout")!=null)
//        {
//        if (intent.getStringExtra("logout").equals("logout"))
//        {
//            sharedPreferencesEditor.putString("pass","");
//            sharedPreferencesEditor.commit();
//        }}
//        if (!sharedPreferences.getString("login","").isEmpty())
//        {
//            loginEditText.setText(sharedPreferences.getString("login",""));
//        }
//        if (!sharedPreferences.getString("pass","").isEmpty())
//        {
//            passwordEditText.setText(sharedPreferences.getString("pass",""));
//        }
//        if (!loginEditText.getText().toString().isEmpty() && !passwordEditText.getText().toString().isEmpty())
//        {
//            Zaloguj(null);
//        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}
