package com.example.tm.weather.Process.WeatherAPI;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.tm.weather.Model.LocationDataItems;
import com.example.tm.weather.Model.SQLiteDatabase.SQLiteDatabaseProvider;
import com.example.tm.weather.Model.WeatherDataItems;
import com.example.tm.weather.Model.WeatherFinalStaticData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.example.tm.weather.Process.WeatherAPI.ConvertWeatherData.getArtResourceForWeatherCondition;
import static com.example.tm.weather.Process.WeatherAPI.ConvertWeatherData.getCurrentDayOfMonth;
import static com.example.tm.weather.Process.WeatherAPI.ConvertWeatherData.getCurrentDayOfWeek;


/**
 * Created by TM on 08/10/2016.
 */
/* get needed data from JSON String  from WeatherConnection class
    Declare construction by cityId  -> then call execute() function.
    + execute() will


    input : cityId;
    Output : store all data to SQLite database

Send data to
*/
public class GetWeatherDataDetailsFromJSONObjectAndStore {

    GetJSONObjectFromWeatherAPIConnection getJSONObjectFromWeatherAPIConnection;
    SQLiteDatabaseProvider sqLiteDatabaseProvider;
    WeatherDataItems weatherDataItems;
    LocationDataItems locationDataItems;
    WeatherFinalStaticData weatherFinalStaticData;
    int cityId;
    Context context = null;

    public GetWeatherDataDetailsFromJSONObjectAndStore(int cityId, Context context) {
        Log.d("TEXT_TRACE", "Content at ParseWeatherDataDetailsFromJSON");
        this.cityId = cityId;
        this.context = context;
        Log.d("TEXT_TRACE", "Content at ParseWeatherDataDetailsFromJSONObject: " + String.valueOf(context));
    }


    /*
    * Get details data from JSON String then store to SQLite DB
    *
    *
    */
    public void execute() {
        context.deleteDatabase(WeatherFinalStaticData.WeatherEntry.DATABASE_NAME);
        context.deleteDatabase(WeatherFinalStaticData.LocationEntry.DATABASE_NAME);
        Log.i("TEST_TRACE", " Start execute() at  ParseWeatherDataDetailsFromJSONObject ");
        new DơwnloadJSONString().execute(String.valueOf(cityId));
        Log.i("TEST_TRACE", " End execute() at  ParseWeatherDataDetailsFromJSONObject ");

    }

    public void ParseWeatherDataFromJSONObject() {
        JSONObject jsonObjectMain;
        JSONObject jsonObjectCity;


        SQLiteDatabaseProvider sqLiteDatabaseProvider = new SQLiteDatabaseProvider(context);
        getJSONObjectFromWeatherAPIConnection = new GetJSONObjectFromWeatherAPIConnection(cityId);
        jsonObjectMain = getJSONObjectFromWeatherAPIConnection.execute();

        /*
        * 1. Create a Database by call constructor of WeatherCreateDatabaseHelper
        * 2. Get details data from JSONObject
        * 3. Store data to SQLite by use WeatherDataSource
        * */

        Log.i("TEST_TRACE", "Start ParseWeatherDataDetailsFromJSONObject at ParseWeatherDataDetailsFromJSONObject");

        /* Set value for cityId */

        Log.i("TEST_TRACE", String.valueOf(cityId));
        /*Get Location data and store to Database*/
        try {
            jsonObjectCity = jsonObjectMain.getJSONObject("city");
            String cityName = jsonObjectCity.getString(WeatherFinalStaticData.CITY_NAME);
            String countryName = jsonObjectCity.getString(WeatherFinalStaticData.COUNTRY_NAME);
            JSONObject jsonObjectCoord = jsonObjectCity.getJSONObject("coord");
            double cityLon = jsonObjectCoord.getDouble(WeatherFinalStaticData.COORD_LON);
            double cityLat = jsonObjectCoord.getDouble(WeatherFinalStaticData.COORD_LAT);
            locationDataItems = new LocationDataItems(cityId, cityName, countryName, cityLon, cityLat);


            sqLiteDatabaseProvider.addLocationRow(locationDataItems);
            /*Get Weather data and store to Database*/
            JSONArray jsonArray = jsonObjectMain.getJSONArray(WeatherFinalStaticData.JSON_WEATHER_LIST_PARAMETERS);
            /*Get weather item data and add to WeatherItemData*/

            for (int i = 0; i < jsonArray.length(); i++) {
                Log.d("TEST_TRACE", "ParseWeatherDataDetailsFromJSONObject : " + String.valueOf(i));
                JSONObject jsonObjectWeatherDetails = jsonArray.getJSONObject(i);

                double mDateWindSpeed = jsonObjectWeatherDetails.getDouble(WeatherFinalStaticData.JSON_WIND_SPEED);
                double mDateHumidity = jsonObjectWeatherDetails.getDouble(WeatherFinalStaticData.JSON_HUMIDITY_DAY);
                double mDatePressure = jsonObjectWeatherDetails.getDouble(WeatherFinalStaticData.JSON_PRESSURE_DATE);
                // Get data from weather key
                JSONObject jsonArrayTemp = jsonObjectWeatherDetails.getJSONObject(WeatherFinalStaticData.JSON_TEMPRATURE_DAY);
                double mMaxTemperure = jsonArrayTemp.getDouble(WeatherFinalStaticData.JSON_WEATHER_LIST_PARAMETERS_MAX);
                double mMinTemperure = jsonArrayTemp.getDouble(WeatherFinalStaticData.JSON_WEATHER_LIST_PARAMETERS_MIN);
                //   JSONObject jsonArrayTempObject = jsonArrayTemp.getJSONObject(0);

                JSONArray jsonArray1 = jsonObjectWeatherDetails.getJSONArray(WeatherFinalStaticData.JSON_WEATHER_LIST_STATUS);
                JSONObject jsonObjectWeatherStatus = jsonArray1.getJSONObject(0);
                String mDateStatusMain = jsonObjectWeatherStatus.getString(WeatherFinalStaticData.JSON_WEATHER_LIST_STATUS_MAIN);
                String mDateStatusDescription = jsonObjectWeatherStatus.getString(WeatherFinalStaticData.JSON_WEATHER_LIST_STATUS_DESCRIPTION);
                int mImageItemAPI = Integer.parseInt(jsonObjectWeatherStatus.getString(WeatherFinalStaticData.JSON_WEATHER_LIST_STATUS_ICON));
                int mImageItem = getArtResourceForWeatherCondition(mImageItemAPI);

                Log.i("TEST_TRACE", "1-0");
                String dayOfWeek = getCurrentDayOfWeek(i);
                String dayOfMonth = getCurrentDayOfMonth(i);
                weatherDataItems = new WeatherDataItems(dayOfWeek, dayOfMonth, mMaxTemperure, mMinTemperure, mDatePressure,
                        mDateHumidity, mDateStatusMain, mDateStatusDescription, mDateWindSpeed, mImageItem, cityId);

                Log.i("TEST_TRACE", "");
            /*Add weather item data to SQLite Database */
                /*BUG*/
                sqLiteDatabaseProvider.addWeatherRow(weatherDataItems);
                Log.d("TEST_TRACE", "1weatherCreateDatabaseHelper - 2");

                /* Function for unitest
                System.out.println("ID" + weatherListData.getId());
                System.out.println("DAY OF WEEK " + weatherListData.getmDayofWeek());
                System.out.println("DAY OF MONTH" + weatherListData.getmDayofMonth());
                System.out.println("MAX TEMP "+ weatherListData.getmMaxTemperure());
                System.out.println("MIN TEMP" + weatherListData.getmMinTemperure());
                System.out.println("PRESSURE" + weatherListData.getmDatePressure());
                System.out.println("HUMIDITY " + weatherListData.getmDateHumidity());
                System.out.println("STATUS MAIN " + weatherListData.getmDateStatusMain());
                System.out.println("STATUS DESCRIPTION"+ weatherListData.getmDateStatusDescription());
                System.out.println("WIND SPEED"+weatherListData.getmDateWindSpeed());
                System.out.println("IMAGE ITEM "+weatherListData.getmImageItem());
                /*Finish function*/
            }

        } catch (NullPointerException e) {
            System.out.print(e + " Array is NOT valid ");
        } catch (JSONException e) {
            System.out.println(e);
        }
        Log.i("TEST_TRACE", "End ParseWeatherDataFromJSONObject at ParseWeatherDataDetailsFromJSONObject");

    }

    private class DơwnloadJSONString extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... cityId) {
            Log.i("TEST_TRACE", "Start doInBackground - DơwnloadJSONString.class - GetJSONObjectFromWeatherAPIConnection ");
            ///execute(cityId[0]);
            ParseWeatherDataFromJSONObject();
            Log.i("TEST_TRACE", "End doInBackground - DơwnloadJSONString.class - GetJSONObjectFromWeatherAPIConnection ");
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String jsonString) {
            Log.d("TEST_TRACE", "Start onPostExecute - DơwnloadJSONString.class - GetJSONObjectFromWeatherAPIConnection ");
            Log.d("TEST_TRACE", "Start onPostExecute - DơwnloadJSONString.class - GetJSONObjectFromWeatherAPIConnection ");

        }

        /*public void execute(String cityId) {
            ParseWeatherDataDetailsFromJSONObject parseWeatherDataDetailsFromJSONObject;
            parseWeatherDataDetailsFromJSONObject = new ParseWeatherDataDetailsFromJSONObject(Integer.parseInt(cityId));
            parseWeatherDataDetailsFromJSONObject.ParseWeatherDataDetailsFromJSONObject();
        */
    }
}

