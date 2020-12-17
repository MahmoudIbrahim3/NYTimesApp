package com.nytimesapp.di.module;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.room.Room;

import com.nytimesapp.data.local.AppDatabase;
import com.nytimesapp.data.local.dao.ArticleDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DbModule {

    @Provides
    @Singleton
    AppDatabase provideDatabase(@NonNull Application application) {
        return Room.databaseBuilder(application,
                AppDatabase.class, "NYTimes.db")
                .allowMainThreadQueries().build();
    }

    @Provides
    @Singleton
    ArticleDao provideTaxDao(@NonNull AppDatabase appDatabase) {
        return appDatabase.articleDao();
    }
}
