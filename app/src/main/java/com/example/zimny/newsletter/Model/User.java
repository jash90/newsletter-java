package com.example.zimny.newsletter.Model;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("status")
    private String status;
    @SerializedName("message")
    private String message;
    @SerializedName("login")
    private String login;
    @SerializedName("email")
    private String email;
    @SerializedName("full_name")
    private String full_name;
    @SerializedName("login_token")
    private String login_token;
    @SerializedName("login_token_exp")
    private String login_token_exp;
    @SerializedName("refresh_token")
    private String refresh_token;
    @SerializedName("refresh_token_exp")
    private String refresh_token_exp;

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLogin_token_exp() {
        return login_token_exp;
    }

    public void setLogin_token_exp(String login_token_exp) {
        this.login_token_exp = login_token_exp;
    }

    public String getRefresh_token_exp() {
        return refresh_token_exp;
    }

    public void setRefresh_token_exp(String refresh_token_exp) {
        this.refresh_token_exp = refresh_token_exp;
    }

    public User() {
    }

    public User(String status, String message, String login, String email, String full_name, String login_token, String login_token_exp, String refresh_token, String refresh_token_exp) {
        this.status = status;
        this.message = message;
        this.login = login;
        this.email = email;
        this.full_name = full_name;
        this.login_token = login_token;
        this.login_token_exp = login_token_exp;
        this.refresh_token = refresh_token;
        this.refresh_token_exp = refresh_token_exp;
    }

    @Override
    public String toString() {
        return "User{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", full_name='" + full_name + '\'' +
                ", login_token='" + login_token + '\'' +
                ", login_token_exp='" + login_token_exp + '\'' +
                ", refresh_token='" + refresh_token + '\'' +
                ", refresh_token_exp='" + refresh_token_exp + '\'' +
                '}';
    }
}