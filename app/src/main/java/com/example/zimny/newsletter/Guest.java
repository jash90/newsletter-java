package com.example.zimny.newsletter;

/**
 * Created by ideo7 on 07.06.2017.
 */

class Guest {
    private String login;
    private String pass;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Guest(String login, String pass) {
        this.login = login;
        this.pass = pass;
    }
}
