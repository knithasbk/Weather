package com.example.tm.weather.Model.SQLiteDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.tm.weather.Model.LocationDataItems;
import com.example.tm.weather.Model.WeatherDataItems;
import com.example.tm.weather.Model.WeatherFinalStaticData;

import java.util.ArrayList;

/**
 * Created by TM on 28/10/2016.
 */

public class SQLiteDatabaseProvider extends SQLiteOpenHelper {

    private String[] weatherColums = {
            WeatherFinalStaticData.WeatherEntry.WEATHER_ID,
            WeatherFinalStaticData.WeatherEntry.COLUMN_DATE_OF_WEEK,
            WeatherFinalStaticData.WeatherEntry.COLUMN_DATE_OF_MONTH,
            WeatherFinalStaticData.WeatherEntry.COLUMN_HUMIDITY,
            WeatherFinalStaticData.WeatherEntry.COLUMN_MAX_TEMP,
            WeatherFinalStaticData.WeatherEntry.COLUMN_MIN_TEMP,
            WeatherFinalStaticData.WeatherEntry.COLUMN_IMGATE_ID,
            WeatherFinalStaticData.WeatherEntry.COLUMN_PRESSURE,
            WeatherFinalStaticData.WeatherEntry.COLUMN_WIN_SPEED,
            WeatherFinalStaticData.WeatherEntry.COLUMN_STATUS_DESCRIPTION,
            WeatherFinalStaticData.WeatherEntry.COLUMN_STATUS_MAIN,
            WeatherFinalStaticData.WeatherEntry.COLUMN_LOC_ID_SYNC
    };

    private String[] locationColums = {
            WeatherFinalStaticData.LocationEntry.ID,
            WeatherFinalStaticData.LocationEntry.COLUMN_CITY_ID,
            WeatherFinalStaticData.LocationEntry.COLUMN_CITY_LAT,
            WeatherFinalStaticData.LocationEntry.COLUMN_CITY_LON,
            WeatherFinalStaticData.LocationEntry.COLUMN_CITY_NAME,
            WeatherFinalStaticData.LocationEntry.COLUMN_COUNTRY_NAME
    };

    public SQLiteDatabaseProvider(Context context) {
        super(context, WeatherFinalStaticData.WeatherEntry.DATABASE_NAME, null, WeatherFinalStaticData.WeatherEntry.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String CREATE_LOCATION_DATABASE = "create table " + WeatherFinalStaticData.LocationEntry.DATABASE_NAME + " ( "
                + WeatherFinalStaticData.LocationEntry.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + WeatherFinalStaticData.LocationEntry.COLUMN_CITY_ID + " INTEGER NOT NULL, "
                + WeatherFinalStaticData.LocationEntry.COLUMN_CITY_LAT + " REAL NOT NULL, "
                + WeatherFinalStaticData.LocationEntry.COLUMN_CITY_LON + " REAL NOT NULL, "
                + WeatherFinalStaticData.LocationEntry.COLUMN_CITY_NAME + " TEXT, "
                + WeatherFinalStaticData.LocationEntry.COLUMN_COUNTRY_NAME + " TEXT "
                + " ); ";

        final String CREATE_WEATHER_DATABASE = "create table " + WeatherFinalStaticData.WeatherEntry.DATABASE_NAME + " ( "
                + WeatherFinalStaticData.WeatherEntry.WEATHER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + WeatherFinalStaticData.WeatherEntry.COLUMN_DATE_OF_WEEK + " TEXT NOT NULL, "
                + WeatherFinalStaticData.WeatherEntry.COLUMN_DATE_OF_MONTH + " TEXT NOT NULL, "
                + WeatherFinalStaticData.WeatherEntry.COLUMN_HUMIDITY + " REAL , "
                + WeatherFinalStaticData.WeatherEntry.COLUMN_MAX_TEMP + " REAL , "
                + WeatherFinalStaticData.WeatherEntry.COLUMN_MIN_TEMP + " REAL , "
                + WeatherFinalStaticData.WeatherEntry.COLUMN_IMGATE_ID + " INTEGER , "
                + WeatherFinalStaticData.WeatherEntry.COLUMN_PRESSURE + " REAL, "
                + WeatherFinalStaticData.WeatherEntry.COLUMN_WIN_SPEED + " REAL NOT NULL, "
                + WeatherFinalStaticData.WeatherEntry.COLUMN_STATUS_DESCRIPTION + " TEXT NOT NULL, "
                + WeatherFinalStaticData.WeatherEntry.COLUMN_STATUS_MAIN + " TEXT NOT NULL, "
                + WeatherFinalStaticData.WeatherEntry.COLUMN_LOC_ID_SYNC + " INTEGER, "
                + "FOREIGN KEY ( " + WeatherFinalStaticData.WeatherEntry.COLUMN_LOC_ID_SYNC + " ) REFERENCES " + WeatherFinalStaticData.LocationEntry.DATABASE_NAME +
                "( " + WeatherFinalStaticData.LocationEntry.ID + " ), UNIQUE (" + WeatherFinalStaticData.WeatherEntry.COLUMN_LOC_ID_SYNC + ") ON CONFLICT REPLACE);";


        db.execSQL(CREATE_WEATHER_DATABASE);
        Log.d("TEST_TRACE", "onCreate() at WeatherCreateDatabaseHelper : Creater Location");
        db.execSQL(CREATE_LOCATION_DATABASE);
        Log.d("TEST_TRACE", "onCreate() at WeatherCreateDatabaseHelper : Creater Weather");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.e("EXECUTE", " Delete old DB version " + oldVersion + "create a new DB  version " + newVersion);
        db.execSQL("DROP TABLE IF EXISTS " + WeatherFinalStaticData.WeatherEntry.DATABASE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + WeatherFinalStaticData.LocationEntry.DATABASE_NAME);
        onCreate(db);
    }

    /**
     * open database
     */

    public void addWeatherRow(WeatherDataItems weatherDataItems) {
        SQLiteDatabase database = this.getWritableDatabase();
        Log.d("TEST_TRACE", "Start addWeatherRow() at WeatherDataSource() class");
        ContentValues contentValues = new ContentValues();
        contentValues.put(WeatherFinalStaticData.WeatherEntry.COLUMN_DATE_OF_WEEK, weatherDataItems.getmDayofWeek());
        contentValues.put(WeatherFinalStaticData.WeatherEntry.COLUMN_DATE_OF_MONTH, weatherDataItems.getmDayofMonth());
        contentValues.put(WeatherFinalStaticData.WeatherEntry.COLUMN_HUMIDITY, weatherDataItems.getmDateHumidity());
        contentValues.put(WeatherFinalStaticData.WeatherEntry.COLUMN_MAX_TEMP, weatherDataItems.getmMaxTemperure());
        contentValues.put(WeatherFinalStaticData.WeatherEntry.COLUMN_MIN_TEMP, weatherDataItems.getmMinTemperure());
        contentValues.put(WeatherFinalStaticData.WeatherEntry.COLUMN_IMGATE_ID, weatherDataItems.getmImageItem());
        contentValues.put(WeatherFinalStaticData.WeatherEntry.COLUMN_PRESSURE, weatherDataItems.getmDatePressure());
        contentValues.put(WeatherFinalStaticData.WeatherEntry.COLUMN_WIN_SPEED, weatherDataItems.getmDateWindSpeed());
        contentValues.put(WeatherFinalStaticData.WeatherEntry.COLUMN_STATUS_DESCRIPTION, weatherDataItems.getmDateStatusDescription());
        contentValues.put(WeatherFinalStaticData.WeatherEntry.COLUMN_STATUS_MAIN, weatherDataItems.getmDateStatusMain());
        database.insert(WeatherFinalStaticData.WeatherEntry.DATABASE_NAME, null, contentValues);
        Log.d("TEST_TRACE", "End addWeatherRow() at WeatherDataSource()class");
        database.close();

    }

    public void addLocationRow(LocationDataItems locationDataItems) {
        Log.d("TEST_TRACE", "End addLocationRow() at WeatherDataSource()class");
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        /*Insert cityId row*/
        contentValues.put(WeatherFinalStaticData.LocationEntry.COLUMN_CITY_ID, locationDataItems.getCityId());
        contentValues.put(WeatherFinalStaticData.LocationEntry.COLUMN_CITY_LAT, locationDataItems.getmCityLat());
        contentValues.put(WeatherFinalStaticData.LocationEntry.COLUMN_CITY_LON, locationDataItems.getmCityLon());
        contentValues.put(WeatherFinalStaticData.LocationEntry.COLUMN_CITY_NAME, locationDataItems.getmCityName());
        contentValues.put(WeatherFinalStaticData.LocationEntry.COLUMN_COUNTRY_NAME, locationDataItems.getmContryName());
        database.insert(WeatherFinalStaticData.LocationEntry.DATABASE_NAME, null, contentValues);
        Log.d("TEST_TRACE", "End addLocationRow() at WeatherDataSource()class");
        database.close();
    }

    public LocationDataItems getWeatherLocationDetails(int CityId) {
        LocationDataItems weatherLocationData = new LocationDataItems();
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.query(WeatherFinalStaticData.LocationEntry.DATABASE_NAME, locationColums, WeatherFinalStaticData.LocationEntry.COLUMN_CITY_ID + "=?",
                new String[]{String.valueOf(CityId)}, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            weatherLocationData = new LocationDataItems(
                    cursor.getInt(WeatherFinalStaticData.LocationColumnID.COLUMN_ID),
                    cursor.getInt(WeatherFinalStaticData.LocationColumnID.COLUMN_CITY_ID),
                    cursor.getString(WeatherFinalStaticData.LocationColumnID.COLUMN_CITY_LAT),
                    cursor.getString(WeatherFinalStaticData.LocationColumnID.COLUMN_CITY_LON),
                    cursor.getDouble(WeatherFinalStaticData.LocationColumnID.COLUMN_CITY_NAME),
                    cursor.getDouble(WeatherFinalStaticData.LocationColumnID.COLUMN_COUNTRY_NAME));
        }
        database.close();
        return weatherLocationData;
    }

    public WeatherDataItems getWeatherDetails(int id) {
        WeatherDataItems weatherListData = new WeatherDataItems(); // Open database connection.
        SQLiteDatabase database = this.getWritableDatabase();

        Cursor cursor = database.query(WeatherFinalStaticData.WeatherEntry.DATABASE_NAME, weatherColums, WeatherFinalStaticData.WeatherEntry.WEATHER_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            weatherListData = new WeatherDataItems(
                    Integer.parseInt(cursor.getString(WeatherFinalStaticData.WeatherColumnID.WEATHER_ID_COLUMN_ID)),
                    cursor.getString(WeatherFinalStaticData.WeatherColumnID.COLUMN_ID_DATE_OF_WEEK),
                    cursor.getString(WeatherFinalStaticData.WeatherColumnID.COLUMN_ID_DATE_OF_MONTH),
                    cursor.getDouble(WeatherFinalStaticData.WeatherColumnID.COLUMN_ID_MAX_TEMP),
                    cursor.getDouble(WeatherFinalStaticData.WeatherColumnID.COLUMN_ID_MIN_TEMP),
                    cursor.getDouble(WeatherFinalStaticData.WeatherColumnID.COLUMN_ID_PRESSURE),
                    cursor.getDouble(WeatherFinalStaticData.WeatherColumnID.COLUMN_ID_HUMIDITY),
                    cursor.getString(WeatherFinalStaticData.WeatherColumnID.COLUMN_ID_STATUS_MAIN),
                    cursor.getString(WeatherFinalStaticData.WeatherColumnID.COLUMN_ID_STATUS_DESCRIPTION),
                    cursor.getDouble(WeatherFinalStaticData.WeatherColumnID.COLUMN_ID_WIN_SPEED),
                    cursor.getInt(WeatherFinalStaticData.WeatherColumnID.COLUMN_ID_IMGATE_ID),
                    cursor.getInt(WeatherFinalStaticData.WeatherColumnID.COLUMN_ID_LOCATION));
        }

        database.close();
        return weatherListData;
    }

    public int getNumberWeatherItem() {
        SQLiteDatabase database = this.getWritableDatabase();
        ArrayList<WeatherDataItems> weatherListDataArrayList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + WeatherFinalStaticData.WeatherEntry.DATABASE_NAME;
        Cursor cursor = database.rawQuery(selectQuery, null);
        database.close();
        return cursor.getCount();

    }

    public ArrayList<WeatherDataItems> getAllWeartherData() {

        SQLiteDatabase database = this.getWritableDatabase();
        ArrayList<WeatherDataItems> weatherListDataArrayList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + WeatherFinalStaticData.WeatherEntry.DATABASE_NAME;
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor != null) {
            cursor.moveToFirst();
            do {
                WeatherDataItems weatherListData = new WeatherDataItems(Integer.parseInt(cursor.getString(WeatherFinalStaticData.WeatherColumnID.WEATHER_ID_COLUMN_ID)),
                        cursor.getString(WeatherFinalStaticData.WeatherColumnID.COLUMN_ID_DATE_OF_WEEK),
                        cursor.getString(WeatherFinalStaticData.WeatherColumnID.COLUMN_ID_DATE_OF_MONTH),
                        cursor.getDouble(WeatherFinalStaticData.WeatherColumnID.COLUMN_ID_MAX_TEMP),
                        cursor.getDouble(WeatherFinalStaticData.WeatherColumnID.COLUMN_ID_MIN_TEMP),
                        cursor.getDouble(WeatherFinalStaticData.WeatherColumnID.COLUMN_ID_PRESSURE),
                        cursor.getDouble(WeatherFinalStaticData.WeatherColumnID.COLUMN_ID_HUMIDITY),
                        cursor.getString(WeatherFinalStaticData.WeatherColumnID.COLUMN_ID_STATUS_MAIN),
                        cursor.getString(WeatherFinalStaticData.WeatherColumnID.COLUMN_ID_STATUS_DESCRIPTION),
                        cursor.getDouble(WeatherFinalStaticData.WeatherColumnID.COLUMN_ID_WIN_SPEED),
                        cursor.getInt(WeatherFinalStaticData.WeatherColumnID.COLUMN_ID_IMGATE_ID),
                        cursor.getInt(WeatherFinalStaticData.WeatherColumnID.COLUMN_ID_LOCATION));
                weatherListDataArrayList.add(weatherListData);
            } while (cursor.moveToNext());
        }
        database.close();
        return weatherListDataArrayList;

    }


    public int getLocationId() {
        int location = 1;
        SQLiteDatabase database = this.getWritableDatabase();
        String selectQuery = "SELECT * FROM " + WeatherFinalStaticData.LocationEntry.DATABASE_NAME;
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor != null) {
            cursor.moveToFirst();
            location = cursor.getInt(WeatherFinalStaticData.LocationColumnID.COLUMN_CITY_ID);
        }
        database.close();
        return location;

    }


}

