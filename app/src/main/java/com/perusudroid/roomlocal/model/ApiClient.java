package com.perusudroid.roomlocal.model;


import com.perusudroid.roomlocal.common.BaseProject;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by perusu on 13/5/17.
 */

public class ApiClient {

    private static Retrofit retrofit = null;

    public static ApiInterface getInterface(){
        return getClient().create(ApiInterface.class);
    }

    private static Retrofit getClient() {

        if (retrofit == null) {

            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BaseProject.getInstance().getBaseUrl())
                    .client(httpClient.build())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();

        }
        return retrofit;
    }

}
