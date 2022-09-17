package com.denis.test_java.api.user;

import com.denis.test_java.api.model.UserDto;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;

public class UserRepositoryImpl implements UserRepository {
    private final UserApi userApi;

    @Inject
    public UserRepositoryImpl(UserApi userApi) {
        this.userApi = userApi;
    }

    @Override
    public Single<UserDto> getUser() {
        return userApi.getUser();
    }
}
