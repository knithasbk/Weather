package com.example.tm.weather.Process.WeatherAPI;

import android.content.Context;

import com.example.tm.weather.Model.SettingsPreferenceProvider;
import com.example.tm.weather.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by TM on 19/10/2016.
 */

public class ConvertWeatherData {
    SettingsPreferenceProvider settingsPreferenceProvider;


    public static int getIconResourceForWeatherCondition(int weatherId) {
        // Based on weather code data found at:
        // http://bugs.openweathermap.org/projects/api/wiki/Weather_Condition_Codes
        if (weatherId >= 200 && weatherId <= 232) {
            return R.drawable.ic_storm;
        } else if (weatherId >= 300 && weatherId <= 321) {
            return R.drawable.ic_light_rain;
        } else if (weatherId >= 500 && weatherId <= 504) {
            return R.drawable.ic_rain;
        } else if (weatherId == 511) {
            return R.drawable.ic_snow;
        } else if (weatherId >= 520 && weatherId <= 531) {
            return R.drawable.ic_rain;
        } else if (weatherId >= 600 && weatherId <= 622) {
            return R.drawable.ic_snow;
        } else if (weatherId >= 701 && weatherId <= 761) {
            return R.drawable.ic_fog;
        } else if (weatherId == 761 || weatherId == 781) {
            return R.drawable.ic_storm;
        } else if (weatherId == 800) {
            return R.drawable.ic_clear;
        } else if (weatherId == 801) {
            return R.drawable.ic_light_clouds;
        } else if (weatherId >= 802 && weatherId <= 804) {
            return R.drawable.ic_cloudy;
        }
        return -1;
    }

    /**
     * Helper method to provide the art resource id according to the weather condition id returned
     * by the OpenWeatherMap call.
     *
     * @param weatherId from OpenWeatherMap API response
     * @return resource id for the corresponding icon. -1 if no relation is found.
     */
    public static int getArtResourceForWeatherCondition(int weatherId) {
        // Based on weather code data found at:
        // http://bugs.openweathermap.org/projects/api/wiki/Weather_Condition_Codes
        if (weatherId >= 200 && weatherId <= 232) {
            return R.drawable.art_storm;
        } else if (weatherId >= 300 && weatherId <= 321) {
            return R.drawable.art_light_rain;
        } else if (weatherId >= 500 && weatherId <= 504) {
            return R.drawable.art_rain;
        } else if (weatherId == 511) {
            return R.drawable.art_snow;
        } else if (weatherId >= 520 && weatherId <= 531) {
            return R.drawable.art_rain;
        } else if (weatherId >= 600 && weatherId <= 622) {
            return R.drawable.art_snow;
        } else if (weatherId >= 701 && weatherId <= 761) {
            return R.drawable.art_fog;
        } else if (weatherId == 761 || weatherId == 781) {
            return R.drawable.art_storm;
        } else if (weatherId == 800) {
            return R.drawable.art_clear;
        } else if (weatherId == 801) {
            return R.drawable.art_light_clouds;
        } else if (weatherId >= 802 && weatherId <= 804) {
            return R.drawable.art_clouds;
        }
        return -1;
    }


    public static String getCurrentDayOfWeek(int i) {
        String dayOfWeek = null;
        SimpleDateFormat sdfOfWeek = new SimpleDateFormat("EEEE");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, i);
        dayOfWeek = sdfOfWeek.format(c.getTime());
        return dayOfWeek;
    }

    public static String getCurrentDayOfMonth(int i) {
        String dayOfMonth = null;
        SimpleDateFormat sdfOfMonth = new SimpleDateFormat("dd MMMM");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, i);
        dayOfMonth = sdfOfMonth.format(c.getTime());
        return dayOfMonth;

    }

    public static double convertKelvinToCelsius(double kelvinTemp) {
        return kelvinTemp - 273.15;
    }

    public static double convertKelvintoToFahrenheit(double kelvinTemp) {
        return kelvinTemp * 9 / 5 - 459.67;
    }

    public double ConvertTempUnit(Double kelvinTemp, Context context) {
        settingsPreferenceProvider = new SettingsPreferenceProvider();
        double temp = 10;
        String tempUnit = settingsPreferenceProvider.getWeatherUnit(context);
        if (tempUnit == "metric") {
            temp = convertKelvinToCelsius(kelvinTemp);
        } else if (tempUnit == "imperial") {
            temp = convertKelvintoToFahrenheit(kelvinTemp);
        }
        return  kelvinTemp;
        /*
        * TODO: handle for convert Temp unit
        * */
       //  return temp;

    }


}
