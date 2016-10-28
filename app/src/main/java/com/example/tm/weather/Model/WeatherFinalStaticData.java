package com.example.tm.weather.Model;

/**
 * Created by TM on 10/10/2016.
 */

public class WeatherFinalStaticData {
    public  WeatherFinalStaticData(){}

    final public int DATE_NUMBER = 16;
    final public static String BASIC_URL = "http://api.openweathermap.org/data/2.5/forecast/daily?id=";
    final public static String APPID = "&APPID=";
    final public static String DATE_QUERY = "&cnt=";
    final public static String USER_ID = "1f9ed645ac85d117e32bdc1492a7cef6";

    // JSON Item list
    final public static String CITY_NAME = "name";
    final public static String CITY_ID = "id";
    final public static String COORD = "coord";
    final public static String COORD_LON = "lon";
    final public static String COORD_LAT = "lat";
    final public static String COUNTRY_NAME = "country";


    // JSON Weather parameters
    final public static String JSON_WEATHER_LIST_PARAMETERS = "list";
    final public static String JSON_WEATHER_LIST_PARAMETERS_MAX = "max";
    final public static String JSON_WEATHER_LIST_PARAMETERS_MIN = "min";
    final public static String JSON_TEMPRATURE_DAY = "temp";
    final public static String JSON_PRESSURE_DATE = "pressure";
    final public static String JSON_HUMIDITY_DAY = "humidity";
    final public static String JSON_WEATHER_LIST_STATUS = "weather";
    final public static String JSON_WIND_SPEED = "speed";
    final public static String JSON_WEATHER_LIST_STATUS_MAIN = "main";
    final public static String JSON_WEATHER_LIST_STATUS_DESCRIPTION = "description";
    final public static String JSON_WEATHER_LIST_STATUS_ICON = "id";


    public static final class LocationEntry {
        public static final String DATABASE_NAME = "location_db";
        public static final String ID = "_id";
        public static final String COLUMN_CITY_ID = "city_id";
        public static final String COLUMN_CITY_LON = "city_lon";
        public static final String COLUMN_CITY_LAT = "city_lat";
        public static final String COLUMN_CITY_NAME = "city_name";
        public static final String COLUMN_COUNTRY_NAME = "country_name";
    }

    // SQLite DATABASE Parameters Weather

    public static final class WeatherEntry {
        public static final String WEATHER_ID = "_id";
        public static final String COLUMN_DATE = "weather_date";
        public static final String DATABASE_NAME = "weather_db";
        public static final String COLUMN_DATE_OF_WEEK = "day_of_week";
        public static final String COLUMN_DATE_OF_MONTH = "day_of_month";
        public static final String COLUMN_MAX_TEMP = "max_temp";
        public static final String COLUMN_MIN_TEMP = "min_temp";
        public static final String COLUMN_PRESSURE = "pressure";
        public static final String COLUMN_HUMIDITY = "humidity";
        public static final String COLUMN_STATUS_MAIN = "status_main";
        public static final String COLUMN_STATUS_DESCRIPTION = "status_description";
        public static final String COLUMN_WIN_SPEED = "wind_speed";
        public static final String COLUMN_IMGATE_ID = "image_id";
        public static final int DATABASE_VERSION = 4;
        public static final String COLUMN_LOC_ID_SYNC = "location_id";

    }

    public static final class WeatherColumnID {
        public static final int WEATHER_ID_COLUMN_ID = 0;
        public static final int COLUMN_ID_DATE_OF_WEEK = 1;
        public static final int COLUMN_ID_DATE_OF_MONTH = 2;
        public static final int COLUMN_ID_HUMIDITY = 3;
        public static final int COLUMN_ID_MAX_TEMP = 4;
        public static final int COLUMN_ID_MIN_TEMP = 5;
        public static final int COLUMN_ID_IMGATE_ID = 6;
        public static final int COLUMN_ID_PRESSURE = 7;
        public static final int COLUMN_ID_WIN_SPEED = 8;
        public static final int COLUMN_ID_STATUS_DESCRIPTION = 9;
        public static final int COLUMN_ID_STATUS_MAIN = 10;
        public static final int COLUMN_ID_LOCATION = 11;

    }

    public static final class LocationColumnID {
        public static final int COLUMN_ID = 0;
        public static final int COLUMN_CITY_ID = 1;
        public static final int COLUMN_CITY_LAT = 2;
        public static final int COLUMN_CITY_LON = 3;
        public static final int COLUMN_CITY_NAME =4 ;
        public static final int COLUMN_COUNTRY_NAME = 5;

    }


}
