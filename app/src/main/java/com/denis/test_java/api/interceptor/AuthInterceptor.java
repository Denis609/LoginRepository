package com.denis.test_java.api.interceptor;

import androidx.annotation.NonNull;

import com.denis.test_java.api.auth.AuthProvider;
import com.denis.test_java.api.auth.OAuthProvider;
import com.denis.test_java.model.TokenDto;

import java.io.IOException;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthInterceptor implements Interceptor {
    private final AuthProvider authProvider ;
    private final OAuthProvider oAuthProvider;

    public AuthInterceptor(AuthProvider authProvider) {
        this.authProvider = authProvider;
        this.oAuthProvider = (OAuthProvider) authProvider;
    }

    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        for (Map.Entry<String, String> item : authProvider.get().entrySet()) {
            builder.header(item.getKey(), item.getValue());
        }
        Response response = chain.proceed(builder.build());
        if (oAuthProvider == null) {
            return response;
        }
        if (response.code() == 401) {
            response.close();
            TokenDto refreshToken = oAuthProvider.refreshTokenSync();
            if (refreshToken != null) {
                for (Map.Entry<String, String> item : authProvider.get().entrySet()) {
                    builder.header(item.getKey(), item.getValue());
                }
            }
            return chain.proceed(builder.build());
        }
        return response;
    }
}
