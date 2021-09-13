package com.ivmak.youth.core.di;

import androidx.room.Room;

import com.ivmak.youth.App;
import com.ivmak.youth.core.db.AppDatabase;
import com.ivmak.youth.core.db.MasterDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RoomModule {

    private AppDatabase database;

    public RoomModule(App mApplication) {

        database = Room.databaseBuilder(mApplication, AppDatabase.class, "database")
                .allowMainThreadQueries()
                .build();
    }

    @Singleton
    @Provides
    AppDatabase providesDatabase() {
        return database;
    }

    @Singleton
    @Provides
    MasterDao providesMasterDao(AppDatabase database) {
        return database.getMasterDAO();
    }
}
