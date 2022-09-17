package com.denis.test.api.auth;

import com.denis.test.api.model.TokenDto;
import com.denis.test.base.TokenSharedPrefs;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Response;


public class AuthRepositoryImpl implements AuthRepository {

    private final TokenSharedPrefs sharedPrefs;
    private final AuthApi authApi;

    @Inject
    public AuthRepositoryImpl(
            AuthApi authApi,
            TokenSharedPrefs sharedPrefs
    ) {
        this.authApi = authApi;
        this.sharedPrefs = sharedPrefs;
    }

    @Override
    public Single<TokenDto> login(String login, String password) {
        return authApi.login("password", login, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(tokenDto -> {
                    sharedPrefs.save(tokenDto);
                    return tokenDto;
                });
    }

    @Override
    public Map<String, String> get() {
        TokenDto tokenDto = sharedPrefs.authToken();
        if (tokenDto == null
        || tokenDto.getAccessToken() == null
        || tokenDto.getAccessToken().isEmpty()) {
            return Collections.emptyMap();
        }
        return authorization("Bearer " + tokenDto.getAccessToken());
    }

    @Override
    public TokenDto refreshTokenSync() {
        TokenDto tokenDto = sharedPrefs.authToken();
        if (tokenDto != null) {
            try {
                Response<TokenDto> response = authApi.refreshSync("refresh_token" ,tokenDto.getRefreshToken()).execute();
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        sharedPrefs.save(response.body());
                    }
                } else {
                    sharedPrefs.delete();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sharedPrefs.authToken();
    }
}
