package com.ricguzo.dagger_mvp_retrofit.data;

import com.google.gson.Gson;
import com.ricguzo.dagger_mvp_retrofit.data.entities.Repository;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface GitHubService {

  String BASE_URL = "https://api.github.com/";

  @GET("/users/octocat/repos")
  Single<List<Repository>> retrieveRepositories();

  class Factory {
    public GitHubService create() {
      return new Retrofit.Builder()
          .baseUrl(BASE_URL)
          .addConverterFactory(GsonConverterFactory.create())
          .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
          .build()
          .create(GitHubService.class);
    }
  }
}
