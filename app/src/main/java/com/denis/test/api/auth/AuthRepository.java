package com.denis.test.api.auth;

import com.denis.test.api.setting.auth.OAuthProvider;
import com.denis.test.api.model.TokenDto;

import io.reactivex.rxjava3.core.Single;

public interface AuthRepository extends OAuthProvider {
    Single<TokenDto> login(String login, String password);
}
