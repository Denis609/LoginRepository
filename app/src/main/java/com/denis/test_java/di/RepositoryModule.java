package com.denis.test_java.di;

import com.denis.test_java.api.AuthRepository;
import com.denis.test_java.api.AuthRepositoryImpl;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
abstract public class RepositoryModule {

    @Binds
    abstract public AuthRepository bindAuthRepository(
            AuthRepositoryImpl authRepositoryImpl
    );
}
