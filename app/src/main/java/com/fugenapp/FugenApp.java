package com.fugenapp;

import android.app.Application;

import com.fugenapp.database.FugenAppDatabase;

public class FugenApp extends Application {

    private static FugenApp instance;
    private FugenAppDatabase database;

    public static FugenApp getInstance() {
        if (instance == null) {
            instance = new FugenApp();
        }
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
