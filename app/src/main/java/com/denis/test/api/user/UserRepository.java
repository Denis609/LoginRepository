package com.denis.test.api.user;

import com.denis.test.api.model.UserDto;

import io.reactivex.rxjava3.core.Single;

public interface UserRepository {
    Single<UserDto> getUser();
}
