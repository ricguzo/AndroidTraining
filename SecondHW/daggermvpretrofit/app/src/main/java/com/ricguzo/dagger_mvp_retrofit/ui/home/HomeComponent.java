package com.ricguzo.dagger_mvp_retrofit.ui.home;


import com.ricguzo.dagger_mvp_retrofit.di.MainComponent;
import com.ricguzo.dagger_mvp_retrofit.di.scopes.PerView;

import dagger.Component;

@PerView
@Component(dependencies = MainComponent.class)
public interface HomeComponent {
  void inject(HomeActivity homeActivity);
}
