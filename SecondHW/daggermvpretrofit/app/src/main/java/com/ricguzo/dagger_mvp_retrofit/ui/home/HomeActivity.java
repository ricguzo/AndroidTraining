package com.ricguzo.dagger_mvp_retrofit.ui.home;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


import com.ricguzo.dagger_mvp_retrofit.MyApp;
import com.ricguzo.dagger_mvp_retrofit.R;
import com.ricguzo.dagger_mvp_retrofit.data.entities.Repository;
import com.ricguzo.dagger_mvp_retrofit.di.MainComponent;

import java.util.List;

import javax.inject.Inject;

public class HomeActivity extends AppCompatActivity implements HomeContract.View {

  private static final String TAG = "HomeActivityTAG_";

  @Inject
  HomePresenter homePresenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    injectDependencies();

    homePresenter.attachView(this);
    homePresenter.loadData();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    homePresenter.detachView();
  }

  @Override
  public void showResults(List<Repository> repositories) {
    Log.d(TAG, "showResults: " + repositories);
  }


  @Override
  public void showError(String error) {
    Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
  }

  private void injectDependencies() {
    MainComponent mainComponent = ((MyApp) getApplication()).getMainComponent();

    DaggerHomeComponent.builder()
        .mainComponent(mainComponent)
        .build()
        .inject(this);
  }


}
