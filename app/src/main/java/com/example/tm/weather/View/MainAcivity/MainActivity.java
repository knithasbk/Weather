package com.example.tm.weather.View.MainAcivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.tm.weather.R;
import com.example.tm.weather.View.MainAcivity.MainTabFragments.DiagramFragment.DiagramFragment;
import com.example.tm.weather.View.MainAcivity.MainTabFragments.RecycleFragment.ListWeatherFragment;
import com.example.tm.weather.View.SettingsActivity.SettingsActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("TEST_TRACE", "Start onCreate at MainActivity.class");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * Handle for Toolbar
         * */
        Toolbar mToolbar = (Toolbar) findViewById(R.id.activity_main_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        /**
         * Handle for TabView
         * */
        handleTabView();

    }

    @Override
    protected void onResume() {
        handleTabView();
        super.onResume();
    }
    public void handleTabView(){
        ViewPageAdapter viewPageAdapter = new ViewPageAdapter(getSupportFragmentManager());
        viewPageAdapter.addFragment(new ListWeatherFragment(), "Weather");
        viewPageAdapter.addFragment(new DiagramFragment(), "Diagram");
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager_main);
        viewPager.setAdapter(viewPageAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
        Log.i("TEST_TRACE", "End onCreate() at MainActivity.class");

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.i("TEST_TRACE", "Start onCreateOptionsMenu function of MainActivity.class");
        getMenuInflater().inflate(R.menu.activity_main, menu);
        Log.i("TEST_TRACE", "  End onCreateOptionsMenu function of MainActivity.class");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.i("TEST_TRACE", "Start onOptionsItemSelected function of MainActivity.class")
        ;
        /** Handle action bar item clicks here. The action bar will
         *  automatically handle clicks on the Home/Up button, so long
         * as you specify a parent activity in AndroidManifest.xml.
         */


        int id = item.getItemId();

        if (id == R.id.activity_main_action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }
        Log.i("TEST_TRACE", "End onOptionsItemSelected function of MainActivity.class");

        return super.onOptionsItemSelected(item);
    }


}

