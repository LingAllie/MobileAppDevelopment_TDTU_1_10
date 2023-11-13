package com.tnl.lab08_ex2.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestAPI {

    private static Retrofit retrofit;

    public static ProductAPI productAPI () {
        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://127.0.0.1:3000/")
                    .addConverterFactory(GsonConverterFactory.create())//important
                    .build();
        }
        return retrofit.create(ProductAPI.class);
    }
}
