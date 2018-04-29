package com.ricguzo.firsthw.ui;

import com.ricguzo.firsthw.di.MainComponent;
import com.ricguzo.firsthw.di.scopes.PerView;

import dagger.Component;

/**
 * Created by ricgu on 28/04/2018.
 */

@PerView
@Component(dependencies = MainComponent.class)
public interface HomeComponent {
    void inject(HomeActivity homeActivity);
}
