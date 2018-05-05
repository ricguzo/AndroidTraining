package com.ricguzo.dagger_mvp_retrofit.di;


import com.ricguzo.dagger_mvp_retrofit.data.DataRepository;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = MainModule.class)
public interface MainComponent {
  DataRepository dataRepository();
}
