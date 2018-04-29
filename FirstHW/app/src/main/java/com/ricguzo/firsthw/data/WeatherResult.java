package com.ricguzo.firsthw.data;

import android.support.annotation.NonNull;

import java.util.Date;

/**
 * Created by ricgu on 29/04/2018.
 */

public class WeatherResult implements Comparable<WeatherResult>{
    private Integer dateUtc;
    private String date;
    private double tempMin;
    private double tempMax;

    public WeatherResult(Integer dateUtc, String date, double tempMin, double tempMax) {
        this.dateUtc = dateUtc;
        this.date = date;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getTempMin() {
        return tempMin;
    }

    public void setTempMin(double tempMin) {
        this.tempMin = tempMin;
    }

    public double getTempMax() {
        return tempMax;
    }

    public void setTempMax(double tempMax) {
        this.tempMax = tempMax;
    }

    public Integer getDateUtc() {
        return dateUtc;
    }

    public void setDateUtc(Integer dateUtc) {
        this.dateUtc = dateUtc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WeatherResult that = (WeatherResult) o;

        return date != null ? date.equals(that.date) : that.date == null;
    }



    @Override
    public int hashCode() {
        return date != null ? date.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "WeatherResult{" +
                "date='" + date + '\'' +
                ", tempMin=" + tempMin +
                ", tempMax=" + tempMax +
                '}';
    }


    @Override
    public int compareTo(@NonNull WeatherResult weatherResult) {
        if(this.getDateUtc() < weatherResult.getDateUtc())
            return -1;
        if(this.getDateUtc() > weatherResult.getDateUtc())
            return 1;

        return 0;
    }
}
