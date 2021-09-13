package com.ivmak.youth;

import android.util.Log;

import androidx.lifecycle.LifecycleObserver;

import com.ivmak.youth.core.di.AppModule;
import com.ivmak.youth.core.di.DaggerAppComponent;
import com.ivmak.youth.core.di.RoomModule;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;


public class App extends DaggerApplication implements LifecycleObserver {

    private static final String TAG = "Application";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate()");
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent
                .builder()
                .roomModule(new RoomModule(this))
                .appModule(new AppModule(this))
                .build();

    }

}
