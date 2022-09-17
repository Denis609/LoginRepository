package com.denis.test.di;

import com.denis.test.api.auth.AuthApi;
import com.denis.test.api.auth.AuthRepository;
import com.denis.test.api.auth.AuthRepositoryImpl;
import com.denis.test.api.user.UserApi;
import com.denis.test.api.user.UserRepository;
import com.denis.test.api.user.UserRepositoryImpl;
import com.denis.test.base.TokenSharedPrefs;

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
