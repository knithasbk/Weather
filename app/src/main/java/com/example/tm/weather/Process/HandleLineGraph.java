package com.example.tm.weather.Process;

import android.content.Context;

import com.echo.holographlibrary.Line;
import com.echo.holographlibrary.LinePoint;
import com.example.tm.weather.Model.SQLiteDatabase.SQLiteDatabaseProvider;
import com.example.tm.weather.Model.WeatherDataItems;

import java.util.ArrayList;

/**
 * Created by TM on 03/11/2016.
 */

public class HandleLineGraph {
    public HandleLineGraph() {
    }


    public ArrayList<Line> handle(String[] collections, Context context) {
        ArrayList<Line> lines = new ArrayList<>();
        /**
         * Get Weather Data from databse
         * */


        SQLiteDatabaseProvider sqLiteDatabaseProvider = new SQLiteDatabaseProvider(context);
        ArrayList<WeatherDataItems> weatherDataItemsArrayList = new ArrayList<>();
        weatherDataItemsArrayList = sqLiteDatabaseProvider.getAllWeartherData();

        /**
         * TODO: Get type of line, which will be showed from arrayLine
         * Where             *
         *  4 : tempLine;
         *  3 : hunidityLine;
         *  7 : PressureLine;
         *  8 : winSpeedLine;
         *  No any type is selected, return 0;
         * */
        /**
         * get Line value from database
         * Output:
         *  - String : DayOfTheMonth;
         *  - String : value, base on line type number.
         * */

        /**
         * HashMap<String, String> listColor = new HashMap<>();
         * listColor.put("4", "#d414b7");
         * listColor.put("3", "#d71f0e");
         * listColor.put("7", "#4cffaa00");
         * listColor.put("8", "#783300ff");
         *   dayOfMonth = weatherDataItemsArrayList.get(i).getmDayofMonth();
         */

        for (String lineType : collections) {
            Line line = new Line();
            for (int i = 0; i < 16; i++) {
                double value;
                LinePoint point = new LinePoint();
                String dayOfMonth;
                switch (lineType) {
                    case "4":
                        value = weatherDataItemsArrayList.get(i).getmMaxTemperure();
                        break;
                    case "3":
                        value = weatherDataItemsArrayList.get(i).getmDateHumidity();
                        break;
                    case "7":
                        value = weatherDataItemsArrayList.get(i).getmDatePressure() / 100;
                        break;
                    case "8":
                        value = weatherDataItemsArrayList.get(i).getmDateWindSpeed();
                        break;
                    default:
                        return null;
                }
                point.setX(i);
                float valueFloat = (float) value;
                point.setY(valueFloat);
                line.addPoint(point);
            }
            lines.add(line);
        }
        return lines;
    }
}
