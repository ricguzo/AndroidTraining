package com.ricguzo.firsthw.di;

import com.ricguzo.firsthw.data.DataRepository;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by ricgu on 28/04/2018.
 */
@Singleton
@Component(modules = MainModule.class)
public interface MainComponent {
    DataRepository dataRepository();
}
