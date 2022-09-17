package com.denis.test.api.auth;

import com.denis.test.api.model.TokenDto;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface AuthApi {
    @FormUrlEncoded
    @POST("oauth/token")
    Single<TokenDto> login(
            @Field("grant_type") String grantType,
            @Field("username") String username,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("oauth/token")
    Call<TokenDto> refreshSync(
            @Field("grant_type") String grantType,
            @Field("refresh_token") String token
    );
}
