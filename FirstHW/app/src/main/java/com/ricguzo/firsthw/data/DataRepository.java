package com.ricguzo.firsthw.data;

import com.ricguzo.firsthw.data.entitites.Response;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

/**
 * Created by ricgu on 28/04/2018.
 */

@Singleton
public class DataRepository {

    private String APP_ID = "60896347b180b72f2e6f1cbace080b6a";

    private WeatherService weatherService;

    @Inject
    public DataRepository(WeatherService weatherService){ this.weatherService = weatherService; }

    public Single<Response> retrieveWeather(double lat, double lon){
        return  weatherService.retrieveWeather(lat,lon,APP_ID);
    }
}
