package com.lejeune.david.fahrzeugewahler;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Lucian on 4/7/2017.
 */

public class SharedPrefHelper {

    private static SharedPrefHelper   sharedPreference;
    public static final String PREFS_NAME = "APPDATA";

    public static SharedPrefHelper getInstance()
    {
        if (sharedPreference == null)
        {
            sharedPreference = new SharedPrefHelper();
        }
        return sharedPreference;
    }

    public SharedPrefHelper() {
        super();
    }


    public void save(Context context, String text , String Key) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        //settings = PreferenceManager.getDefaultSharedPreferences(context);
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE); //1
        editor = settings.edit(); //2

        editor.putString(Key, text); //3

        editor.commit(); //4
    }

    public String getValue(Context context , String Key) {
        SharedPreferences settings;
        String text = "";
        //  settings = PreferenceManager.getDefaultSharedPreferences(context);
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        text = settings.getString(Key, "");
        return text;
    }

    public void clearSharedPreference(Context context) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        //settings = PreferenceManager.getDefaultSharedPreferences(context);
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();

        editor.clear();
        editor.apply();
    }

    public void removeValue(Context context , String value) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();

        editor.remove(value);
        editor.apply();
    }
}
