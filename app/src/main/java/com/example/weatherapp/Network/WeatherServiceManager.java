package com.example.weatherapp.Network;

import com.example.weatherapp.Object.Model;
import com.example.weatherapp.Object.Weather;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class WeatherServiceManager {

    WeatherService weatherService;

    @Inject
    public WeatherServiceManager(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    public Observable<Model> getInfo(String unit, String cityName){
        return weatherService.getWeather(unit,cityName).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
}

}
