package com.example.weatherapp.Network;

import com.example.weatherapp.Object.Model;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface WeatherService {

    @Headers({"X-RapidAPI-Host: community-open-weather-map.p.rapidapi.com",
              "X-RapidAPI-Key: 08aa8a2995msh3494129b67c7601p1d69bdjsn3599b96f027b"})

    @GET("/weather")
    Observable<Model> getWeather(@Query("units") String unit,
                                 @Query("q") String location);
}
