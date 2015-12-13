package com.mobileproto.hireddit.hireddit.sharedPreference;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SharedPreference {

    public static final String PREFS_NAME = "HI_REDDIT_PREFS";

    public SharedPreference() {
        super();
    }

    public void save(Context context, String key, Boolean text) {
        SharedPreferences settings;
        Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();

        editor.putBoolean(key, text);

        editor.apply();
    }

    public Boolean getValue(Context context, String key) {
        SharedPreferences settings;
        Boolean text;

        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        text = settings.getBoolean(key, false);
        return text;
    }

    public void clearSharedPreference(Context context) {
        SharedPreferences settings;
        Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();

        editor.clear();
        editor.apply();
    }

    public void removeValue(Context context, String key) {
        SharedPreferences settings;
        Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();

        editor.remove(key);
        editor.apply();
    }
}