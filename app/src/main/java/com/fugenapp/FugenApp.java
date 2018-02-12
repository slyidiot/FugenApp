package com.fugenapp;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.TypedValue;

import com.fugenapp.database.FugenAppDatabase;

public class FugenApp extends Application {

    public static final int FLAGSHIP_EVENTS = 1;
    public static final int EYE_CATCHERS = 2;
    public static final int TECHNICAL_EVENTS = 3;
    public static final int FUN_EVENTS = 4;
    public static final int SEARCH_FRAG = 5;

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

    public static int getResIdFromAttribute(final Activity activity, final int attr) {
        if (attr == 0)
            return 0;
        final TypedValue typedvalueattr = new TypedValue();
        activity.getTheme().resolveAttribute(attr, typedvalueattr, true);
        return typedvalueattr.resourceId;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        context = getApplicationContext();
    }
}
