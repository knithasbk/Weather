package com.example.tm.weather.Process;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.tm.weather.Process.WeatherAPI.GetWeatherDataDetailsFromJSONObjectAndStore;


/**
 * Created by TM on 19/10/2016.
 */

public class SyncDataFromWeatherAPI {
    int oldCityId = 1269750;
    int newCityId = 703363;
    Context context;

    public SyncDataFromWeatherAPI(Context context, int newCityId) {
        this.context = context;
        this.newCityId = newCityId;
    }


    /*Check network information*/
    public boolean isConnected() {
        boolean networkStatus;
        /*
        * Check network connection
        * return status in boolen type
        * True if have a connection
        * */
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        networkStatus = activeNetwork != null
                && activeNetwork.isConnectedOrConnecting();
        return networkStatus;
    }

    /*Refresh data if have any change and in time scheduled  */
    public void refreshData(int newCityId) {

        GetWeatherDataDetailsFromJSONObjectAndStore getWeatherDataDetailsFromJSONObjectAndStore = new GetWeatherDataDetailsFromJSONObjectAndStore(newCityId, context);
        getWeatherDataDetailsFromJSONObjectAndStore.execute();
            /*
            * Get data from network
            * Delelete SQLite Database then re-OnCreat();
            * */
    }

    public void SyncData() {
        if (isConnected()) {
            refreshData(newCityId);
        }
         /*   context.deleteDatabase(WeatherData.LocationEntry.DATABASE_NAME);
            context.deleteDatabase(WeatherData.WeatherEntry.DATABASE_NAME);
           */
    }


}




