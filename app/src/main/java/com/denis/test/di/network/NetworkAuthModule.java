package com.denis.test.di.network;

import com.denis.test.Const;
import com.denis.test.api.auth.AuthApi;
import com.denis.test.api.setting.interceptor.AuthInterceptor;
import com.denis.test.api.setting.auth.BasicAuthProvider;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.concurrent.TimeUnit;

import javax.inject.Qualifier;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class NetworkAuthModule {

    @Provides
    @Singleton
    AuthApi provideRetrofitAuthApi(@AuthInterceptorOkHttpClient Retrofit retrofit) {
        return retrofit.create(AuthApi.class);
    }

    @Provides
    @Singleton
    @AuthInterceptorOkHttpClient
    public HttpLoggingInterceptor provideAuthHttpLoggingInterceptor() {
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    @Provides
    @Singleton
    @AuthInterceptorOkHttpClient
    OkHttpClient provideOkHttpClient(
            @AuthInterceptorOkHttpClient HttpLoggingInterceptor httpLoggingInterceptor
    ) {
        return new OkHttpClient.Builder()
                .connectTimeout(15L, TimeUnit.SECONDS)
                .readTimeout(60L, TimeUnit.SECONDS)
                .writeTimeout(60L, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(new AuthInterceptor(new BasicAuthProvider(Const.CREDENTIALS)))
                .addInterceptor(chain -> {
                    Request.Builder builder = chain.request().newBuilder();
                    builder.header("Accept", "application/json");
                    return chain.proceed(builder.build());
                }).build();
    }

    @Provides
    @Singleton
    @AuthInterceptorOkHttpClient
    Retrofit provideAuthRetrofit(@AuthInterceptorOkHttpClient OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(Const.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }

    @Qualifier
    @Retention(RetentionPolicy.RUNTIME)
    private @interface AuthInterceptorOkHttpClient {}
}
