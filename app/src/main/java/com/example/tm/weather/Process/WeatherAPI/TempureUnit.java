package com.example.tm.weather.Process.WeatherAPI;

/**
 * Created by TM on 29/10/2016.
 */

public class TempureUnit {
    double temp;
    String unitAnotation;

    public TempureUnit() {
    }

    public TempureUnit(double temp, String unitAnotation) {
        this.temp = temp;
        this.unitAnotation = unitAnotation;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public String getUnitAnotation() {
        return unitAnotation;
    }

    public void setUnitAnotation(String unitAnotation) {
        this.unitAnotation = unitAnotation;
    }
}
