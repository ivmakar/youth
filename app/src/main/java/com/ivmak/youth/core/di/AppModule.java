package com.ivmak.youth.core.di;

import android.content.Context;

import com.ivmak.youth.App;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    App mApp;

    public AppModule(App application) {
        mApp = application;
    }


    @Provides
    @Singleton
    App providesApplication() {
        return mApp;
    }

    @Provides
    Context providesContext(App app) {
        return app.getApplicationContext();
    }

}
