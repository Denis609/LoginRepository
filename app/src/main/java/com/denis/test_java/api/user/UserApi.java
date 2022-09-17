package com.denis.test_java.api.user;

import com.denis.test_java.api.model.UserDto;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;

public interface UserApi {

    @GET("user")
    Single<UserDto> getUser();
}
