package com.ricguzo.firsthw.ui;

import com.ricguzo.firsthw.data.DataRepository;
import com.ricguzo.firsthw.data.WeatherResult;
import com.ricguzo.firsthw.data.entitites.List;
import com.ricguzo.firsthw.data.entitites.Response;
import com.ricguzo.firsthw.di.scopes.PerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ricgu on 28/04/2018.
 */

@PerView
public class HomePresenter implements HomeContract.Presenter{

    private DataRepository dataRepository;
    private HomeContract.View view;
    private Map<String,WeatherResult> resultMap = new HashMap<>();

    @Inject
    public HomePresenter(DataRepository dataRepository){ this.dataRepository = dataRepository; }

    @Override
    public void loadData(double lat, double lon) {
        final Disposable disposable = dataRepository.retrieveWeather(19.4326077,-99.13320799999997)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onSuccess, this::onError);

    }

    private void onError(Throwable throwable) {
        view.showError(throwable.getMessage());
    }

    private void onSuccess(Response response) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        for (List result:response.getList()) {
            Date date = new Date((long) result.getDt()*1000);
            String dateKey = sdf.format(date);
            if(resultMap.containsKey(dateKey)){
                WeatherResult mapWeatherResult = resultMap.get(dateKey);
                if(mapWeatherResult.getTempMax()< result.getMain().getTempMax()){
                    mapWeatherResult.setTempMax(result.getMain().getTempMax());
                }

                if(mapWeatherResult.getTempMin()> result.getMain().getTempMin()){
                    mapWeatherResult.setTempMin(result.getMain().getTempMin());
                }

                resultMap.put(dateKey,mapWeatherResult);

            }else{
                WeatherResult weatherResult = new WeatherResult(result.getDt(),dateKey,result.getMain().getTempMin(),
                        result.getMain().getTempMax());
                resultMap.put(dateKey,weatherResult);
            }

        }

        view.showResults(response.getCity().getName(),resultMap);
    }

    @Override
    public void attachView(HomeContract.View view) {
        this.view = view;
    }

    @Override
    public void dettachView() {
        this.view = null;
    }
}
