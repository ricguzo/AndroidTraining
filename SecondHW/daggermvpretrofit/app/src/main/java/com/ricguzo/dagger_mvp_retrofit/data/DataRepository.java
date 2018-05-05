package com.ricguzo.dagger_mvp_retrofit.data;


import com.ricguzo.dagger_mvp_retrofit.data.entities.Repository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

@Singleton
public class DataRepository {

  private GitHubService gitHubService;

  @Inject
  public DataRepository(GitHubService gitHubService) {
    this.gitHubService = gitHubService;
  }

  public Single<List<Repository>> getRepositories() {
    return gitHubService.retrieveRepositories();
  }
}
