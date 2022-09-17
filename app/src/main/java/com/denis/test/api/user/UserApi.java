package com.denis.test.api.user;

import com.denis.test.api.model.UserDto;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;

public interface UserApi {

    @GET("user")
    Single<UserDto> getUser();
}
