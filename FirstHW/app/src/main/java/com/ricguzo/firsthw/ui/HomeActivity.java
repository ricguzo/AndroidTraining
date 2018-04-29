package com.ricguzo.firsthw.ui;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.ricguzo.firsthw.MyApp;
import com.ricguzo.firsthw.R;
import com.ricguzo.firsthw.data.WeatherResult;
import com.ricguzo.firsthw.data.entitites.List;
import com.ricguzo.firsthw.data.entitites.Response;
import com.ricguzo.firsthw.di.MainComponent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

public class HomeActivity extends AppCompatActivity implements HomeContract.View {

    private static final String TAG = "WeatherTAG_";
    private ArrayList<WeatherResult> resultList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ResponseAdapter responseAdapter = new ResponseAdapter(resultList);
    private TextView textView;

    @Inject
    HomePresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        double longitude = 19.4978;
        double latitude =  -99.1269;


        injectDependencies();

        mainPresenter.attachView(this);
        mainPresenter.loadData(latitude,longitude);

        textView = findViewById(R.id.a_main_city);

        recyclerView = findViewById(R.id.a_main_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(responseAdapter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainPresenter.dettachView();
    }

    @Override
    public void showError(String error) {
        Log.d(TAG, "showError: "+error );
    }

    @Override
    public void showResults(String name, Map<String, WeatherResult> resultMap) {
        textView.setText(name+" Weather");

        resultList.clear();
        resultList.addAll(resultMap.values());
        Collections.sort(resultList);
        responseAdapter.notifyDataSetChanged();
        Log.d(TAG, "showResults: " +resultList.size());
        Log.d(TAG, "showResults: " +resultList);

    }


    private void injectDependencies() {
        MainComponent mainComponent = ((MyApp) getApplication()).getMainComponent();

        DaggerHomeComponent.builder()
                .mainComponent(mainComponent)
                .build()
                .inject(this);
    }

}
