package com.denis.test_java.api.auth;

import java.util.Map;

import okhttp3.Credentials;

public class BasicAuthProvider implements AuthProvider {

    private final com.denis.test_java.model.Credentials credentials;

    public BasicAuthProvider(com.denis.test_java.model.Credentials credentials) {
        this.credentials = credentials;
    }

    @Override
    public Map<String, String> get() {
        return authorization(Credentials.basic(credentials.getLogin(), credentials.getPassword()));
    }
}
