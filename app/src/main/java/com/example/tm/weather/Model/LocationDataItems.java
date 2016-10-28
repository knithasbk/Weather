package com.example.tm.weather.Model;

/**
 * Created by TM on 18/10/2016.
 */

public class LocationDataItems {
    int id;
    int CityId;
    String mCityName;
    Double mCityLon;
    Double mCityLat;
    String mContryName;

    public LocationDataItems() {

    }

    public LocationDataItems(int CityId, String mCityName, String mContryName, Double mCityLon, Double mCityLat) {
        this.CityId = CityId;
        this.mCityName = mCityName;
        this.mCityLon = mCityLon;
        this.mCityLat = mCityLat;
        this.mContryName = mContryName;
    }

    public LocationDataItems(int id, int  CityId, String mCityName, String mContryName, Double mCityLon, Double mCityLat) {
        this.id = id;
        this.CityId = CityId;
        this.mCityName = mCityName;
        this.mCityLon = mCityLon;
        this.mCityLat = mCityLat;
        this.mContryName = mContryName;
    }
    public int getCityId(){
        return CityId;
    }
    public void setCityId(int CityId){
        this.CityId = CityId;
    }

    public String getmCityName() {
        return mCityName;
    }

    public void setmCityName(String mCityName) {
        this.mCityName = mCityName;
    }

    public Double getmCityLon() {
        return mCityLon;
    }

    public void setmCityLon(Double mCityLon) {
        this.mCityLon = mCityLon;
    }

    public Double getmCityLat() {
        return mCityLat;
    }

    public void setmCityLat(Double mCityLat) {
        this.mCityLat = mCityLat;
    }

    public String getmContryName() {
        return mContryName;
    }

    public void setmContryName(String mContryName) {
        this.mContryName = mContryName;
    }
}
