package com.denis.test.api.setting.auth;

import com.denis.test.api.model.Credential;

import java.util.Map;

import okhttp3.Credentials;

public class BasicAuthProvider implements AuthProvider {

    private final Credential credential;

    public BasicAuthProvider(Credential credential) {
        this.credential = credential;
    }

    @Override
    public Map<String, String> get() {
        return authorization(Credentials.basic(credential.getLogin(), credential.getPassword()));
    }
}
