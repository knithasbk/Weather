package com.example.tm.weather.View.MainAcivity.MainTabFragments.RecycleFragment;

import android.content.Context;
import android.content.Intent;
import android.database.CursorIndexOutOfBoundsException;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tm.weather.Model.ArrayDataTest;
import com.example.tm.weather.Model.SQLiteDatabase.SQLiteDatabaseProvider;
import com.example.tm.weather.Model.SettingsPreferenceProvider;
import com.example.tm.weather.Model.WeatherDataItems;
import com.example.tm.weather.Process.SyncDataFromWeatherAPI;
import com.example.tm.weather.R;
import com.example.tm.weather.View.Details.DetailsActivity;
import com.example.tm.weather.View.MainAcivity.MainTabFragments.RecycleFragment.RecycleViewAdapter.RecyclerViewClickListener;
import com.example.tm.weather.View.MainAcivity.MainTabFragments.RecycleFragment.RecycleViewAdapter.WeatherAdapterRecycleView;

import java.util.ArrayList;

/**
 * Created by TM on 28/10/2016.
 */

public class ListWeatherFragment extends Fragment implements RecyclerViewClickListener {
    private WeatherAdapterRecycleView myAdapter;
    SQLiteDatabaseProvider sqLiteDatabaseProvider;

    public ListWeatherFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);


    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_forecast_fragment, null);
        Log.i("TEST_TRACE", "Start OnCreateView function of ListWeatherFragment.class");

    /*  SettingsPreferenceProvider settingsPreferenceProvider = new SettingsPreferenceProvider();
    *   String tempUnit = settingsPreferenceProvider.getWeatherUnit(getActivity());
    *   Log.i("TEST_TRACE", "Start OnCreateView() ListWeatherFragment class: Template Unit: " + tempUnit);
    */
        SQLiteDatabaseProvider sqLiteDatabaseProvider = new SQLiteDatabaseProvider(getActivity());
        ArrayList<WeatherDataItems> weatherDataItemsArrayList = new ArrayList<>();
        ArrayDataTest arrayDataTest;
        try {
            weatherDataItemsArrayList = sqLiteDatabaseProvider.getAllWeartherData();

        } catch (CursorIndexOutOfBoundsException e) {
            arrayDataTest = new ArrayDataTest();
            weatherDataItemsArrayList = arrayDataTest.buildDataTest();


        } catch (Exception e) {
            System.out.println(e + "Null Point when get data from Array Test");
        }

        int newCityId = getNewCityId();
        int oldCityId = getOldCityId();
        if (newCityId != oldCityId)

        {
            SyncDataFromWeatherAPI syncDataFromWeatherAPI = new SyncDataFromWeatherAPI(getActivity(), newCityId);
            syncDataFromWeatherAPI.SyncData();
        }

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.weather_recycler_view);
        myAdapter = new WeatherAdapterRecycleView(weatherDataItemsArrayList, this, getActivity());

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(myAdapter);
        return view;
    }

    @Override
    public void recyclerViewListClicked(View view, int position) {
        Log.i("TEST_TRACE", "Start recyclerViewListClicked function of MainActivity.class");
        Intent intent = new Intent(getActivity(), DetailsActivity.class);
        intent.putExtra("POSITION", position);
        Log.i("TEST_TRACE", "Position at MainActivity.class: " + position);
        startActivity(intent);
        Log.i("TEST_TRACE", "End recyclerViewListClicked function of MainActivity.class");
    }

    public int getOldCityId() {
        int oldCityId = 1269750;
        /*
        * Using settingsData.class
        *
        * */
        Log.i("TEST_TRACE", "Start getOldCityId() from SyncDataFromWeatherAPI class");
        try {
            SQLiteDatabaseProvider sqLiteDatabaseProvider = new SQLiteDatabaseProvider(getActivity());
            oldCityId = sqLiteDatabaseProvider.getLocationId();
            Log.i("TEST_TRACE", "End getOldCityId() from SyncDataFromWeatherAPI class");
        } catch (CursorIndexOutOfBoundsException e) {
            System.out.print(e);
            return oldCityId;
        }
        return oldCityId;
    }

    public int getNewCityId() {
        int newCityId = 1269750;
        /*
        * Using settingsData.class
        *
        * */
        try {

            SettingsPreferenceProvider settingsPreferenceProvider = new SettingsPreferenceProvider();
            newCityId = Integer.parseInt(settingsPreferenceProvider.getLocation(getActivity()));
        } catch (CursorIndexOutOfBoundsException e) {
            System.out.print(e);
            return newCityId;
        }
        return newCityId;

    }
}



