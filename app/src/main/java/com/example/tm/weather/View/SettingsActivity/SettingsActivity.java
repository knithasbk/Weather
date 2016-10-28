package com.example.tm.weather.View.SettingsActivity;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.util.Log;

import com.example.tm.weather.R;

/**
 * Created by TM on 28/10/2016.
 */

public class SettingsActivity extends PreferenceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("TEST_TRACE", "Start onCreate at SettingsActivity class ");
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction().replace(android.R.id.content, new MyPreferenceFragment()).commit();
        Log.i("TEST_TRACE", "End onCreate at SettingsActivity class ");
    }

    public static class MyPreferenceFragment extends PreferenceFragment {
        @Override
        public void onCreate(final Bundle savedInstanceState) {
            Log.i("TEST_TRACE", "Start onCreate at MyPreferenceFragment class ");
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.settings_preference);
            Log.i("TEST_TRACE", "End onCreate at MyPreferenceFragment class ");
        }

    }
}


