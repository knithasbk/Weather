package com.example.tm.weather.Model;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.Set;

/**
 * Created by TM on 28/10/2016.
 */

public class SettingsPreferenceProvider {

    /**
     * Created by TM on 25/10/2016.
     */
    public SettingsPreferenceProvider() {
    }

    ;


    public String getLocation(Context context) {
        SharedPreferences spfs = PreferenceManager.getDefaultSharedPreferences(context);
        return spfs.getString("location_multi", "1568738");

    }

    public String getWeatherUnit(Context context) {
        SharedPreferences spfs = PreferenceManager.getDefaultSharedPreferences(context);
        return spfs.getString("weatherUnit", "metric");
    }

    public boolean isNotified(Context context) {
        SharedPreferences spfs = PreferenceManager.getDefaultSharedPreferences(context);
        return spfs.getBoolean("weatherUnit", false);
    }

    public String[] getMultiSelected(Context context) {
        String[] collectionString = new String[]{};
        SharedPreferences spfs = PreferenceManager.getDefaultSharedPreferences(context);
        Set<String> colections = spfs.getStringSet("diagram", null);
        if (colections != null) {
            collectionString = colections.toArray(new String[]{});
        }
        return collectionString;

    }
}

