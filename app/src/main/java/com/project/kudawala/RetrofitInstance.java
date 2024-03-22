package com.project.kudawala;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    private static Retrofit retrofit = null;
    private static final String Base_URL = "http://10.0.2.2:8080/";
    private APIInterface apiService;

    public static Retrofit getRetrofit(){
        //OkHttpClient okHttpClient = new OkHttpClient().newBuilder().build();

        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        okHttpClient.connectTimeout(100, TimeUnit.SECONDS);
        okHttpClient.readTimeout(100, TimeUnit.SECONDS);
        okHttpClient.writeTimeout(100, TimeUnit.SECONDS);

        if(retrofit == null){
            retrofit = new Retrofit.Builder().baseUrl(Base_URL).
                    addConverterFactory(GsonConverterFactory.create
                    ()).client(okHttpClient.build()).build();

        }
        return  retrofit;
    }


}
