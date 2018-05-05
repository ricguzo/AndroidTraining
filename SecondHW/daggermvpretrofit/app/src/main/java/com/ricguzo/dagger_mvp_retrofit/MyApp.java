package com.ricguzo.dagger_mvp_retrofit;

import android.app.Application;

import com.ricguzo.dagger_mvp_retrofit.di.DaggerMainComponent;
import com.ricguzo.dagger_mvp_retrofit.di.MainComponent;
import com.ricguzo.dagger_mvp_retrofit.di.MainModule;

/**
 * Created by ricgu on 28/04/2018.
 */

public class MyApp extends Application{

    private MainComponent mainComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mainComponent = DaggerMainComponent.builder()
                .mainModule(new MainModule())
                .build();
    }

    public MainComponent getMainComponent(){
        return mainComponent;
    }

}
