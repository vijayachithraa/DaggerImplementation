package com.example.weatherapp.dependencyInjection;

import com.example.weatherapp.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

    @Module
    public abstract class ActivityBuilderModule {
        @ContributesAndroidInjector
        abstract MainActivity providesMainActivity();
    }
}
