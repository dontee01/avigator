package com.avigator.avigator.activity.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by JOHN on 20/04/2017.
 */

public class ApiClient {
    public static final String BASE_URL = "http://277f7638.ngrok.io";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {

//            Gson gson = new GsonBuilder()
//                    .setLenient()
//                    .create();
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
