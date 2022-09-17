package com.denis.test_java.di;

import com.denis.test_java.api.auth.AuthApi;
import com.denis.test_java.api.auth.AuthRepository;
import com.denis.test_java.api.auth.AuthRepositoryImpl;
import com.denis.test_java.api.user.UserApi;
import com.denis.test_java.api.user.UserRepository;
import com.denis.test_java.api.user.UserRepositoryImpl;
import com.denis.test_java.base.TokenSharedPrefs;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class RepositoryModule {

    @Singleton
    @Provides
    public AuthRepository provideAuthRepository(AuthApi authApi, TokenSharedPrefs tokenSharedPrefs) {
        return new AuthRepositoryImpl(authApi, tokenSharedPrefs);
    }

    @Singleton
    @Provides
    public UserRepository provideUserRepository(UserApi userApi) {
        return new UserRepositoryImpl(userApi);
    }
}
