package com.example.zimny.newsletter.Activity;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by ideo7 on 12.06.2017.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}
