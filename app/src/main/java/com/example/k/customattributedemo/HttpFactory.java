package com.example.k.customattributedemo;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public class HttpFactory {
    private static OkHttpClient okHttpClient
            = new OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .addInterceptor(new HttpInterceptor())
            .build();

    public static Api getInstance() {
        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(Api.API_IP_HOST_TEST)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(HttpResultConverter.create())
                .build().create(Api.class);
    }
}
