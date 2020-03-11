package com.rightside.fisioclin.ws;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SetupREST {

    public static final ApiREST apiREST;


    static {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://api.postmon.com.br/v1/")
            .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

         apiREST = retrofit.create(ApiREST.class);


    }




}
