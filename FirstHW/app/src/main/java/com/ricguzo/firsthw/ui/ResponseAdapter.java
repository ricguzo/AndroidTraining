package com.ricguzo.firsthw.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ricguzo.firsthw.R;
import com.ricguzo.firsthw.data.WeatherResult;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

/**
 * Created by ricgu on 28/04/2018.
 */

public class ResponseAdapter extends RecyclerView.Adapter<ResponseAdapter.ViewHolder>{

    private ArrayList<WeatherResult> resultList ;

    public ResponseAdapter (ArrayList<WeatherResult> resultList ){
        this.resultList = resultList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.weather_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(resultList.get(position));
    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textViewDay;
        private final TextView textViewMin;
        private final TextView textViewMax;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewDay = itemView.findViewById(R.id.r_day_txt);
            textViewMin = itemView.findViewById(R.id.r_min_txt);
            textViewMax = itemView.findViewById(R.id.r_max_txt);
        }

        public void bind(WeatherResult weatherResult) {
            NumberFormat formatter = new DecimalFormat("#0.00");
            textViewDay.setText(weatherResult.getDate());

            double tempMin = ((weatherResult.getTempMin()*9)/5) - 459.67;
            textViewMin.setText("Min: "+formatter.format(tempMin)+" °F");

            double tempMax = ((weatherResult.getTempMax()*9)/5) - 459.67;
            textViewMax.setText("Max: "+formatter.format(tempMax)+" °F");
        }
    }
}
