package com.example.zimny.newsletter;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by ideo7 on 01.06.2017.
 */

class User {
    private String login;
    private String email;
    private String full_name;
    private String login_token;
    private Timestamp login_token_exp;
    private String refresh_token;
    private Timestamp refresh_token_exp;





    public User() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }


    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getLogin_token() {
        return login_token;
    }

    public void setLogin_token(String login_token) {
        this.login_token = login_token;
    }



    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public Timestamp getLogin_token_exp() {
        return login_token_exp;
    }

    public void setLogin_token_exp(Timestamp login_token_exp) {
        this.login_token_exp = login_token_exp;
    }

    public Timestamp getRefresh_token_exp() {
        return refresh_token_exp;
    }

    public void setRefresh_token_exp(Timestamp refresh_token_exp) {
        this.refresh_token_exp = refresh_token_exp;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", full_name='" + full_name + '\'' +
                '}';
    }
}
