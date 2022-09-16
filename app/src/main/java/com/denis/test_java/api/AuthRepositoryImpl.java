package com.denis.test_java.api;

import com.denis.test_java.model.TokenDto;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;

public class AuthRepositoryImpl implements AuthRepository{
    private final AuthApi authApi;

    @Inject
    public AuthRepositoryImpl(AuthApi authApi) {
        this.authApi = authApi;
    }

    @Override
    public Single<TokenDto> login(String login, String password) {
        return authApi.login(login, password);
    }
}
