package com.denis.test.api.setting.auth;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

@FunctionalInterface
public interface AuthProvider extends Supplier<Map<String, String>> {
    String AUTHORIZATION = "Authorization";

    default Map<String, String> authorization(String value) {
        return new HashMap<String, String>() {
            {
                put(AUTHORIZATION, value);
            }
        };
    }
}
