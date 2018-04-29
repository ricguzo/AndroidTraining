package com.ricguzo.firsthw.di;

import com.ricguzo.firsthw.data.WeatherService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ricgu on 28/04/2018.
 */

@Module
public class MainModule {

    @Singleton
    @Provides
    public WeatherService provideWeatherService(){
        return new WeatherService.Factory().create();
    }
}
