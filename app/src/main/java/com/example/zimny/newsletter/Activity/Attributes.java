package com.example.zimny.newsletter.Activity;

import android.util.Log;

import com.example.zimny.newsletter.Api.BeinsuredClient;
import com.example.zimny.newsletter.Api.ServiceGenerator;
import com.example.zimny.newsletter.Model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ideo7 on 14.06.2017.
 */

public class Attributes {
    public static String login_token;
    public static String login_token_exp;
    public static String refresh_token;
    public static String refresh_token_exp;
    public static String login ="";
    public static String pass ="";
    public static int id_newsletter;

    public static String getLogin_token() {
        return login_token;
    }

    public static void setLogin_token(String login_token) {
        Attributes.login_token = login_token;
    }

    public static String getLogin_token_exp() {
        return login_token_exp;
    }

    public static void setLogin_token_exp(String login_token_exp) {
        Attributes.login_token_exp = login_token_exp;
    }

    public static String getRefresh_token() {
        return refresh_token;
    }

    public static void setRefresh_token(String refresh_token) {
        Attributes.refresh_token = refresh_token;
    }

    public static String getRefresh_token_exp() {
        return refresh_token_exp;
    }

    public static void setRefresh_token_exp(String refresh_token_exp) {
        Attributes.refresh_token_exp = refresh_token_exp;
    }

    public static String getLogin() {
        return login;
    }

    public static void setLogin(String login) {
        Attributes.login = login;
    }

    public static String getPass() {
        return pass;
    }

    public static void setPass(String pass) {
        Attributes.pass = pass;
    }

    public static int getId_newsletter() {
        return id_newsletter;
    }

    public static void setId_newsletter(int id_newsletter) {
        Attributes.id_newsletter = id_newsletter;
    }

    public static void refreshtoken()
    {
        try {

            BeinsuredClient beinsuredClient = ServiceGenerator.createServiceRefreshtoken(BeinsuredClient.class,"beinsured","beinsu12");
            Call<User> call = beinsuredClient.refresh(Attributes.getLogin(),Attributes.getPass(),"2esde2#derdsr#RD");
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    User user = response.body();
                    if (user!=null)
                    {
                        Attributes.setLogin_token(user.getLogin_token());
                        Attributes.setLogin_token_exp(user.getLogin_token_exp());
                        Attributes.setRefresh_token(user.getRefresh_token());
                        Attributes.setRefresh_token_exp(user.getRefresh_token_exp());
                    }
                    else
                        Log.d("error","eror");
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
