package com.denis.test_java.di;

import android.app.Application;

import com.denis.test_java.base.TokenSharedPrefs;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@InstallIn(SingletonComponent.class)
@Module
public class SharedPrefsModule {

    @Singleton
    @Provides
    TokenSharedPrefs provideSharedPrefs(Application context) {
        return new TokenSharedPrefs(context);
    }
}
