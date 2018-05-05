package com.ricguzo.dagger_mvp_retrofit.ui.home;



import com.ricguzo.dagger_mvp_retrofit.data.entities.Repository;

import java.util.List;

public interface HomeContract {
  interface View {
    void showResults(List<Repository> repositories);

    void showError(String error);
  }

  interface Presenter {
    void loadData();

    void attachView(View view);

    void detachView();
  }
}
