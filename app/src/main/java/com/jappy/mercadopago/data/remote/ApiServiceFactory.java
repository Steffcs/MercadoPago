package com.jappy.mercadopago.data.remote;

import com.jappy.mercadopago.BuildConfig;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiServiceFactory {

    public static <T> T build(OkHttpClient client, Class<T> serviceClass) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.MERCADOPAGO_BASE_URL_DEV)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(serviceClass);
    }
}
