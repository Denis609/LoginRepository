package com.denis.test_java.di.network;

import com.denis.test_java.Const;
import com.denis.test_java.api.auth.AuthRepository;
import com.denis.test_java.api.user.UserApi;
import com.denis.test_java.api.setting.interceptor.AuthInterceptor;

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
public class NetworkUserModule {

    @Provides
    @Singleton
    UserApi provideRetrofitUserApi(@UserInterceptorOkHttpClient Retrofit retrofit) {
        return retrofit.create(UserApi.class);
    }

    @Provides
    @Singleton
    @UserInterceptorOkHttpClient
    public HttpLoggingInterceptor provideUserHttpLoggingInterceptor() {
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    @Provides
    @Singleton
    @UserInterceptorOkHttpClient
    OkHttpClient provideOkHttpClient(
            @UserInterceptorOkHttpClient HttpLoggingInterceptor httpLoggingInterceptor,
            AuthRepository authRepository
    ) {
        return new OkHttpClient.Builder()
                .connectTimeout(15L, TimeUnit.SECONDS)
                .readTimeout(60L, TimeUnit.SECONDS)
                .writeTimeout(60L, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(new AuthInterceptor(authRepository))
                .addInterceptor(chain -> {
                    Request.Builder builder = chain.request().newBuilder();
                    builder.header("Accept", "application/json");
                    return chain.proceed(builder.build());
                }).build();
    }

    @Provides
    @Singleton
    @UserInterceptorOkHttpClient
    Retrofit provideUserRetrofit(@UserInterceptorOkHttpClient OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(Const.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }

    @Qualifier
    @Retention(RetentionPolicy.RUNTIME)
    private @interface UserInterceptorOkHttpClient { }
}
