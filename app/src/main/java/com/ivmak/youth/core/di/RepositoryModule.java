package com.ivmak.youth.core.di;

import com.ivmak.youth.core.db.MasterDao;
import com.ivmak.youth.core.repo.Repository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    @Singleton
    @Provides
    Repository repository(
            MasterDao masterDao
    ) {
        return new Repository(masterDao);
    }

}

