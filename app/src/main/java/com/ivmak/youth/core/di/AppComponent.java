package com.ivmak.youth.core.di;

import com.ivmak.youth.App;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;


@Singleton
@Component(modules = {
        AppModule.class,
        RoomModule.class,
        ActivitiesModule.class,
        RepositoryModule.class,
        AndroidSupportInjectionModule.class,
        ViewModelModule.class
})
public interface AppComponent extends AndroidInjector<App> {

    @Override
    void inject(App instance);
}
