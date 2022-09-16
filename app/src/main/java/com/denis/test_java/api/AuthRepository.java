package com.denis.test_java.api;

import com.denis.test_java.model.TokenDto;

import io.reactivex.rxjava3.core.Single;

public interface AuthRepository {
    Single<TokenDto> login(String login, String password);
}
