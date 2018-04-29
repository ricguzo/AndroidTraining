package com.ricguzo.firsthw.ui;

import com.ricguzo.firsthw.data.WeatherResult;
import com.ricguzo.firsthw.data.entitites.Response;

import java.util.Map;

/**
 * Created by ricgu on 28/04/2018.
 */

public interface HomeContract {
    interface View{

        void showError(String error);

        void showResults(String name, Map<String, WeatherResult> resultMap);
    }

    interface Presenter{
        void loadData(double lat, double lon);

        void attachView(View view);

        void dettachView();
    }
}
