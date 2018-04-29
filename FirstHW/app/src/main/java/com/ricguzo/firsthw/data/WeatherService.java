package com.ricguzo.firsthw.data;

import com.ricguzo.firsthw.data.entitites.Response;

import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ricgu on 28/04/2018.
 */

public interface WeatherService {

    String BASE_URL = "http://api.openweathermap.org/";

    @GET("/data/2.5/forecast")
    Single<Response> retrieveWeather(@Query("lat") double lat, @Query("lon") double lon,
                                     @Query("appid") String appId);

    class Factory{
        public WeatherService create(){
            return new  Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
                    .create(WeatherService.class);
        }
    }
}
