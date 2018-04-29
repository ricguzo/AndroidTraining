package com.ricguzo.firsthw;

import android.app.Application;

import com.ricguzo.firsthw.di.DaggerMainComponent;
import com.ricguzo.firsthw.di.MainComponent;
import com.ricguzo.firsthw.di.MainModule;

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
