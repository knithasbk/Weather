package com.example.tm.weather.View.Details;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tm.weather.Model.SQLiteDatabase.SQLiteDatabaseProvider;
import com.example.tm.weather.Model.WeatherDataItems;
import com.example.tm.weather.Process.WeatherAPI.ConvertWeatherData;
import com.example.tm.weather.Process.WeatherAPI.TempureUnit;
import com.example.tm.weather.R;

import java.util.ArrayList;

/**
 * Created by TM on 28/10/2016.
 */

/**
 * Created by TM on 18/10/2016.
 */

public class DetailsFragment extends Fragment {

    int position;
    ArrayList<WeatherDataItems> weatherDataItemsArrayList = new ArrayList<>();
    SQLiteDatabaseProvider sqLiteDatabaseProvider;
    ConvertWeatherData convertWeatherData;

    public DetailsFragment() {
        Log.i("TEST_TRACE", "DetailsForecastFragment() constructor at DetailsForecastFragment.class ");
    }

    public static DetailsFragment getInstance(int position) {
        Bundle args = new Bundle();
        DetailsFragment detailsFragment = new DetailsFragment();
        Log.d("TEST_TRACE", "Position DetailsForecastFragment at Constructor: " + String.valueOf(position));
        args.putInt("POSITION", position);
        detailsFragment.setArguments(args);
        return detailsFragment;
    }

    public void setPosition(int position) {
        this.position = position;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("TEST_TRACE", "Start onCreateView in DetailsFragment.class ");
        Context context = getActivity().getApplicationContext();
        sqLiteDatabaseProvider = new SQLiteDatabaseProvider(context);

        //   position = args.getInt("POSITION");

        Log.i("TEST_TRACE", "Position DetailsForecastFragment onCreateView " + String.valueOf(position));

        //arrayDataTest = new ArrayDataTest();
        //weatherListDataArrayList = arrayDataTest.buildDataTest();

        weatherDataItemsArrayList = sqLiteDatabaseProvider.getAllWeartherData();
        View view = inflater.inflate(R.layout.details_fragment, container, false);
        GetWeatherRecycleListDataConvertToDeatailView(view, weatherDataItemsArrayList.get(position));
        Log.i("TEST_TRACE", "End OnCreateView in DetailsForecastFragment.class");
        return view;
    }

    public void GetWeatherRecycleListDataConvertToDeatailView(View view, WeatherDataItems weatherDataItems) {
        convertWeatherData = new ConvertWeatherData();
        Log.i("TEST_TRACE", " Start GetWeatherRecycleListDataConvertToDeatailView function on DetailsForecastAdapter.class");
        //  view = new View();
        TextView mtvDayOfWeek = (TextView) view.findViewById(R.id.detail_day_of_week);
        TextView mtvDayOfMonth = (TextView) view.findViewById(R.id.detail_day_of_month);
        TextView mtvHumidity = (TextView) view.findViewById(R.id.detail_humidity);
        ImageView mtvImage = (ImageView) view.findViewById(R.id.detail_image);
        TextView mtvMaxTemp = (TextView) view.findViewById(R.id.detail_max_temp);
        TextView mtvMinTemp = (TextView) view.findViewById(R.id.detail_min_temp);
        TextView mtvPressue = (TextView) view.findViewById(R.id.detail_pressue);
        TextView mtvWeatherStatus = (TextView) view.findViewById(R.id.detail_weather_status);
        TextView mtvWindSpeed = (TextView) view.findViewById(R.id.details_wind_speed);
        Log.i("TEST_TRACE", weatherDataItems.getmDayofMonth());
        mtvDayOfWeek.setText(weatherDataItems.getmDayofWeek());


        TempureUnit tempureUnitMax = convertWeatherData.ConvertTempUnit(weatherDataItems.getmMaxTemperure(), getActivity().getApplicationContext());
        TempureUnit tempureUnitMin = convertWeatherData.ConvertTempUnit(weatherDataItems.getmMinTemperure(), getActivity().getApplicationContext());

        mtvDayOfMonth.setText(weatherDataItems.getmDayofMonth());
        mtvHumidity.setText("Humnidity: " + String.valueOf(weatherDataItems.getmDateHumidity()) + "%");
        mtvWindSpeed.setText("Wind Speed: " + String.valueOf(weatherDataItems.getmDateWindSpeed()));
        mtvImage.setImageResource(weatherDataItems.getmImageItem());
        mtvMaxTemp.setText(String.valueOf(tempureUnitMax.getTemp()) + " " + tempureUnitMax.getUnitAnotation());
        mtvMinTemp.setText(String.valueOf(tempureUnitMin.getTemp()) + " " + tempureUnitMin.getUnitAnotation());
        mtvPressue.setText("Pressure: " + String.valueOf(weatherDataItems.getmDatePressure()));
        mtvWeatherStatus.setText(weatherDataItems.getmDateStatusDescription());
        Log.i("TEST_TRACE", " Finish GetWeatherRecycleListDataConvertToDeatailView function on DetailsForecastAdapter.class");

    }
}
