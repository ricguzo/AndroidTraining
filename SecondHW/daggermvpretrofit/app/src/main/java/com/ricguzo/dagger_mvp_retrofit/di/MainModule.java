package com.ricguzo.dagger_mvp_retrofit.di;


import com.ricguzo.dagger_mvp_retrofit.data.GitHubService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

  @Singleton
  @Provides
  public GitHubService provideGitHubService() {
    return new GitHubService.Factory().create();
  }
}
