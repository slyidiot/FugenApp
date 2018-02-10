package com.fugenapp;

import android.app.Application;
import android.content.Context;

import com.fugenapp.database.FugenAppDatabase;

public class FugenApp extends Application {

    public static final int FLAGSHIP_EVENTS = 1;
    public static final int EYE_CATCHERS = 2;
    public static final int TECHNICAL_EVENTS = 3;

    private static FugenApp instance;
    private static Context context;
    private FugenAppDatabase database;

    public static FugenApp getInstance() {
        if (instance == null) {
            instance = new FugenApp();
        }
        return instance;
    }

    public static Context getAppContext() {
        return FugenApp.context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        context = getApplicationContext();
    }
}
