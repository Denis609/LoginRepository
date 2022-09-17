package com.denis.test.api.setting.auth;

import com.denis.test.api.model.TokenDto;

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
