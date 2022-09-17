package com.denis.test.api.auth;

import com.denis.test.api.model.TokenDto;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface AuthApi {
    @FormUrlEncoded
    @POST("oauth/token?grant_type=password")
    Single<TokenDto> login(
            @Field("username") String username,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("oauth/token?grant_type=refresh_token")
    Call<TokenDto> refreshSync(@Field("refresh_token") String token);
}
