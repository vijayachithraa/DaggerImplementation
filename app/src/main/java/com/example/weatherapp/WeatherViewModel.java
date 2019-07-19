package com.example.weatherapp;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.databinding.ObservableField;

import com.example.weatherapp.Network.WeatherServiceManager;
import com.example.weatherapp.Object.Model;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class WeatherViewModel implements LifecycleObserver {

    public WeatherServiceManager serviceLayer;
    public ObservableField<String> cityText = new ObservableField<>();
    public ObservableField<String> tempText = new ObservableField<>();
    public ObservableField<String> conditionText = new ObservableField<>();
    public ObservableField<String> pressureText = new ObservableField<>();
    public ObservableField<String> humidityText = new ObservableField<>();
    public ObservableField<String> conditionImageUrl = new ObservableField<>();
    public List<String> cities = new ArrayList<>();
    public RecyclerAdapter adapter;
    public List<ItemViewModel> itemViewModels;

    @Inject
    public WeatherViewModel(WeatherServiceManager serviceLayer) {
        this.serviceLayer = serviceLayer;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void init() {
        itemViewModels = new ArrayList<>();
        adapter = new RecyclerAdapter(itemViewModels);
        cities.add("Chennai,in");
        cities.add("London,uk");
        cities.add("Sydney,au");
        getWeatherData(cities);
    }

    private void getWeatherData(List<String> cities) {
        for (String city : cities) {
            serviceLayer.getInfo("metric", city).subscribe(this::populateView, this::onfailure);
        }
    }

    private void onfailure(Throwable throwable) {
    }

    private void populateView(Model model) {
        if (model != null) {
            cityText.set(model.getName());
            tempText.set(model.getMain().getTemp().concat("°C"));
            conditionText.set(model.getWeather().get(0).getMain());
            pressureText.set("Pressure: ".concat(model.getMain().getPressure()).concat(" bar"));
            humidityText.set("Humidity: ".concat(model.getMain().getHumidity()).concat("%"));
            conditionImageUrl.set("http://api.openweathermap.org/img/w/" + model.getWeather().get(0).getIcon() + ".png");

            ItemViewModel itemViewModel = new ItemViewModel();
            itemViewModel.setTemperature(tempText.get());
            itemViewModels.add(itemViewModel);
            adapter.notifyDataSetChanged();
        }
    }
}