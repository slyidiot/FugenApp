package com.fugenapp.helpers;

import android.content.Context;
import android.content.SharedPreferences;

import com.fugenapp.R;

public class PreferenceHelper {

    public static final String APP_PREFERENCES = "fugenapp_preferences";
    public static final String CURRENT_THEME = "current_theme_id";
    private static PreferenceHelper instance;
    private SharedPreferences appPreferences;

    public PreferenceHelper(Context context) {
        appPreferences = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
    }

    public static PreferenceHelper getInstance(Context context) {
        if (instance == null) {
            instance = new PreferenceHelper(context);
        }
        return instance;
    }

    public int getCurrentThemeID() {
        return appPreferences.getInt(CURRENT_THEME, R.style.Default);
    }

}
