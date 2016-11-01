package com.example.tm.weather.View.MainAcivity.MainTabFragments.RecycleFragment.RecycleViewAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tm.weather.Model.SettingsPreferenceProvider;
import com.example.tm.weather.Model.WeatherDataItems;
import com.example.tm.weather.Process.WeatherAPI.ConvertWeatherData;
import com.example.tm.weather.Process.WeatherAPI.TempureUnit;
import com.example.tm.weather.R;

import java.util.ArrayList;

/*Get data from data - WeatherData and flate to view  */
public class WeatherAdapterRecycleView extends RecyclerView.Adapter<WeatherAdapterRecycleView.MyRecycleViewHolder> {
    SettingsPreferenceProvider settingsPreferenceProvider;
    private ArrayList<WeatherDataItems> weatherDataItemsArrayList = null;
    private static RecyclerViewClickListener itemListener;
    Context context;
    ConvertWeatherData convertWeatherData;

    public WeatherAdapterRecycleView(ArrayList<WeatherDataItems> weatherDataItemsArrayList, RecyclerViewClickListener itemListener, Context context) {
        this.weatherDataItemsArrayList = weatherDataItemsArrayList;
        this.itemListener = itemListener;
        this.context = context;
    }


    // call view item
    public static class MyRecycleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView mTextViewItemTimeDayofWeek;
        TextView mTextViewItemTemperature;
        TextView mTextViewItemDayStatus;
        TextView mTextViewItemDayOfMonth;
        ImageView mImageViewItem;


        //Constructor, without void or type of variables
        public MyRecycleViewHolder(View view) {
            super(view);
            mTextViewItemTimeDayofWeek = (TextView) view.findViewById(R.id.text_view_day_of_week_item);
            mTextViewItemDayStatus = (TextView) view.findViewById(R.id.text_view_day_status_item);
            mTextViewItemTemperature = (TextView) view.findViewById(R.id.text_view_max_temperature_item);
            mTextViewItemDayOfMonth = (TextView) view.findViewById(R.id.text_view_day_of_month_item);
            mImageViewItem = (ImageView) view.findViewById(R.id.image_view_item);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            itemListener.recyclerViewListClicked(view, this.getLayoutPosition());

        }
    }

    /**Constructor for declare the class, initial class
    * call onCreateViewHolder return a instance of ViewHolder for  onBindViewHolder
    */
     @Override
    public MyRecycleViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_main_recycle_item_today_view, viewGroup, false);
        MyRecycleViewHolder holder = new MyRecycleViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyRecycleViewHolder holder, int position) {
        convertWeatherData = new ConvertWeatherData();
        settingsPreferenceProvider = new SettingsPreferenceProvider();
        WeatherDataItems weatherDataItems = weatherDataItemsArrayList.get(position);
        TempureUnit tempureUnitMax = convertWeatherData.ConvertTempUnit(weatherDataItems.getmMaxTemperure(), context);


        holder.mTextViewItemDayStatus.setText(weatherDataItems.getmDateStatusMain());
        holder.mTextViewItemTimeDayofWeek.setText(weatherDataItems.getmDayofWeek());
        holder.mTextViewItemTemperature.setText(String.valueOf(tempureUnitMax.getTemp()) + " " + tempureUnitMax.getUnitAnotation());
        holder.mTextViewItemDayOfMonth.setText(weatherDataItems.getmDayofMonth());
        holder.mImageViewItem.setImageResource(weatherDataItems.getmImageItem());
    }


    @Override
    public int getItemCount() {
        return weatherDataItemsArrayList.size();
    }
}




