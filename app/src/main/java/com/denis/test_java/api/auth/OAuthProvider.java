package com.denis.test_java.api.auth;

import com.denis.test_java.model.TokenDto;

import java.util.Collections;
import java.util.Map;

public interface OAuthProvider extends AuthProvider {

    default TokenDto refreshTokenSync() {
        return null;
    }

    @Override
    default Map<String, String> get() {
        return Collections.emptyMap();
    }
}
