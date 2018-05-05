package com.ricguzo.dagger_mvp_retrofit.ui.home;


import com.ricguzo.dagger_mvp_retrofit.data.DataRepository;
import com.ricguzo.dagger_mvp_retrofit.data.entities.Repository;
import com.ricguzo.dagger_mvp_retrofit.di.scopes.PerView;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@PerView
public class HomePresenter implements HomeContract.Presenter {

  private DataRepository dataRepository;
  private HomeContract.View view;

  @Inject
  public HomePresenter(DataRepository dataRepository) {
    this.dataRepository = dataRepository;
  }

  @Override
  public void loadData() {
    final Disposable disposable = dataRepository.getRepositories()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(repositories -> onSuccess(repositories), throwable -> onError(throwable));
  }

  private void onError(Throwable throwable) {
    view.showError(throwable.toString());
  }

  private void onSuccess(List<Repository> repositories) {
    view.showResults(repositories);
  }

  @Override
  public void attachView(HomeContract.View view) {
    this.view = view;
  }

  @Override
  public void detachView() {
    this.view = null;
  }
}
