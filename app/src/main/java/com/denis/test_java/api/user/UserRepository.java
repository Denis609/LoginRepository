package com.denis.test_java.api.user;

import com.denis.test_java.api.model.UserDto;

import io.reactivex.rxjava3.core.Single;

public interface UserRepository {
    Single<UserDto> getUser();
}
