package com.denis.test_java.di;

import com.denis.test_java.api.AuthApi;
import com.denis.test_java.api.AuthRepository;
import com.denis.test_java.api.AuthRepositoryImpl;
import com.denis.test_java.api.interceptor.AuthInterceptor;
import com.denis.test_java.api.auth.BasicAuthProvider;
import com.denis.test_java.model.Credentials;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Binds;
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

    private static final String BASE_URL = "http://smart.eltex-co.ru:8271/api/v1";
    private static final Credentials CREDENTIALS = new Credentials("android-client", "password");

    @Provides
    @Singleton
    AuthApi provideRetrofitApi(Retrofit retrofit) {
        return retrofit.create(AuthApi.class);
    }

    @Provides
    @Singleton
    HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(
            HttpLoggingInterceptor httpLoggingInterceptor
    ) {
        return new OkHttpClient.Builder()
                .connectTimeout(15L, TimeUnit.SECONDS)
                .readTimeout(60L, TimeUnit.SECONDS)
                .writeTimeout(60L, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(new AuthInterceptor(new BasicAuthProvider(CREDENTIALS)))
                .addInterceptor(chain -> {
                    Request.Builder builder = chain.request().newBuilder();
                    builder.header("Accept", "application/json");
                    return chain.proceed(builder.build());
                }).build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }
}
